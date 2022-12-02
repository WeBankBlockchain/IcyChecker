/*
 * Copyright (C) 2013-2015 Jochen Hoenicke (hoenicke@informatik.uni-freiburg.de)
 * Copyright (C) 2015 University of Freiburg
 *
 * This file is part of the ULTIMATE PEAtoBoogie plug-in.
 *
 * The ULTIMATE PEAtoBoogie plug-in is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ULTIMATE PEAtoBoogie plug-in is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE PEAtoBoogie plug-in. If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE PEAtoBoogie plug-in, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the ULTIMATE PEAtoBoogie plug-in grant you additional permission
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.pea2boogie.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_freiburg.informatik.ultimate.boogie.BoogieLocation;
import de.uni_freiburg.informatik.ultimate.boogie.ast.BinaryExpression;
import de.uni_freiburg.informatik.ultimate.boogie.ast.BinaryExpression.Operator;
import de.uni_freiburg.informatik.ultimate.boogie.ast.Expression;
import de.uni_freiburg.informatik.ultimate.boogie.ast.IdentifierExpression;
import de.uni_freiburg.informatik.ultimate.boogie.ast.IntegerLiteral;
import de.uni_freiburg.informatik.ultimate.lib.pea.CDD;
import de.uni_freiburg.informatik.ultimate.lib.pea.Phase;
import de.uni_freiburg.informatik.ultimate.lib.pea.PhaseEventAutomata;
import de.uni_freiburg.informatik.ultimate.lib.pea.Transition;
import de.uni_freiburg.informatik.ultimate.pea2boogie.translator.CDDTranslator;
import de.uni_freiburg.informatik.ultimate.pea2boogie.translator.ReqSymboltable;
import de.uni_freiburg.informatik.ultimate.util.datastructures.CrossProducts;

public class CddConditionGenerator {
	private final Collection<String> mPrimedVars;
	private final Expression mResult;

	public CddConditionGenerator(final Collection<String> primedVars, final PhaseEventAutomata[] automata,
			final BoogieLocation bl, final CDD primedInvariant) {
		mPrimedVars = primedVars;
		mResult = nonDLCGenerator(automata, bl, primedInvariant);
	}

	public Expression getResult() {
		return mResult;
	}

	private Expression nonDLCGenerator(final PhaseEventAutomata[] automata, final BoogieLocation bl,
			final CDD primedInvariant) {
		final int[][] phases = createPhasePairs(automata);

		final List<int[]> phasePermutations = CrossProducts.crossProduct(phases);
		final List<Expression> conditions = new ArrayList<>();
		for (final int[] vector : phasePermutations) {
			assert (vector.length == automata.length);
			CDD cddOuter = CDD.TRUE;
			final List<Expression> impliesLHS = new ArrayList<>();
			final Map<CDD, Map<CDD, CDD>> andCache = new HashMap<>();
			for (int j = 0; j < vector.length; j++) {
				CDD cddInner = CDD.FALSE;
				final PhaseEventAutomata automaton = automata[j];
				final Phase phase = automaton.getPhases()[vector[j]];
				final List<Transition> transitions = phase.getTransitions();
				for (int k = 0; k < transitions.size(); k++) {
					cddInner = cddInner.or((genGuardANDPrimedStInv(transitions.get(k), andCache)
							.and(genStrictInv(transitions.get(k)), andCache)));
				}

				cddOuter = cddOuter.and(cddInner, andCache);

				final String pcName = ReqSymboltable.getPcName(automaton);
				impliesLHS.add(genPCCompEQ(pcName, vector[j], bl));
			}

			// TODO: ccdOther.and primed state invariants from invariant patterns
			// TODO: make the next line work for Boogie and/or smt
			// 1 collect all primed variables
			// (2 ensure that there are no events)
			// 3 exists primed, events . formula
			// 4 eliminate quantifiers
			// 5 profit
			// 6 if not true, build implication
			final CDD cdd =
					new VarRemoval().excludeEventsAndPrimedVars(cddOuter.and(primedInvariant, andCache), mPrimedVars);
			if (cdd == CDD.TRUE) {
				continue;
			}
			final Expression impliesRHS = new CDDTranslator().toBoogie(cdd, bl);
			final Expression implies = new BinaryExpression(bl, BinaryExpression.Operator.LOGICIMPLIES,
					buildBinaryExpression(bl, BinaryExpression.Operator.LOGICAND, impliesLHS), impliesRHS);
			conditions.add(implies);
		}
		if (conditions.isEmpty()) {
			return null;
		}
		return buildBinaryExpression(bl, BinaryExpression.Operator.LOGICAND, conditions);
	}

	private static int[][] createPhasePairs(final PhaseEventAutomata[] automata) {
		final int[][] phases = new int[automata.length][];
		for (int i = 0; i < automata.length; i++) {
			final PhaseEventAutomata automaton = automata[i];
			final int phaseCount = automaton.getPhases().length;
			phases[i] = new int[phaseCount];
			for (int j = 0; j < phaseCount; j++) {
				phases[i][j] = j;
			}
		}
		return phases;
	}

	private static Expression buildBinaryExpression(final BoogieLocation bl, final Operator op,
			final List<Expression> conditions) {
		assert (!conditions.isEmpty());
		int offset = conditions.size() - 1;
		Expression result = conditions.get(offset);
		while (offset > 0) {
			offset--;
			result = new BinaryExpression(bl, op, conditions.get(offset), result);
		}
		return result;
	}

	private static CDD genStrictInv(final Transition transition) {
		final Phase phase = transition.getDest();
		final String[] resetVars = transition.getResets();
		final CDD cdd = new StrictInvariant().genStrictInv(phase.getClockInvariant(), resetVars);
		return cdd;
	}

	private static CDD genGuardANDPrimedStInv(final Transition transition, final Map<CDD, Map<CDD, CDD>> andCache) {
		final CDD guard = transition.getGuard();
		final Phase phase = transition.getDest();
		final CDD primedStInv = phase.getStateInvariant().prime();
		final CDD cdd = guard.and(primedStInv, andCache);
		// cdd = new VarRemoval().varRemoval(cdd, this.translator.primedVars, this.translator.eventVars);
		return cdd;
	}

	private static Expression genPCCompEQ(final String pcName, final int phaseIndex, final BoogieLocation bl) {
		final IdentifierExpression identifier = new IdentifierExpression(bl, pcName);
		final IntegerLiteral intLiteral = new IntegerLiteral(bl, Integer.toString(phaseIndex));
		final BinaryExpression binaryExpr =
				new BinaryExpression(bl, BinaryExpression.Operator.COMPEQ, identifier, intLiteral);
		return binaryExpr;
	}
}
