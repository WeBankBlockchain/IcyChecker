/*
 * Copyright (C) 2013-2015 Matthias Heizmann (heizmann@informatik.uni-freiburg.de)
 * Copyright (C) 2009-2015 University of Freiburg
 *
 * This file is part of the ULTIMATE Automata Library.
 *
 * The ULTIMATE Automata Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ULTIMATE Automata Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE Automata Library. If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE Automata Library, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the ULTIMATE Automata Library grant you additional permission
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.automata.nestedword.operations;

import java.util.Set;

import de.uni_freiburg.informatik.ultimate.automata.AutomataLibraryServices;
import de.uni_freiburg.informatik.ultimate.automata.AutomataOperationCanceledException;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.DoubleDeckerAutomaton;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.DoubleDeckerAutomatonFilteredStates;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.IDoubleDeckerAutomaton;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.INwaOutgoingLetterAndTransitionProvider;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.TransitionConsistencyCheck;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.oldapi.ReachableStatesCopy;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.reachablestates.NestedWordAutomatonReachableStates;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.reachablestates.NestedWordAutomatonReachableStates.DoubleDeckerReachability;
import de.uni_freiburg.informatik.ultimate.core.lib.exceptions.RunningTaskInfo;

/**
 * Creates a nested word automaton where dead end states have been removed.
 *
 * @author Matthias Heizmann (heizmann@informatik.uni-freiburg.de)
 * @param <LETTER>
 *            letter type
 * @param <STATE>
 *            state type
 */
public final class RemoveDeadEnds<LETTER, STATE> extends StateRemoval<LETTER, STATE> {
	private final NestedWordAutomatonReachableStates<LETTER, STATE> mReach;
	private final IDoubleDeckerAutomaton<LETTER, STATE> mResult;

	/**
	 * Given an INestedWordAutomaton nwa return a nested word automaton that has the same states, but all states that
	 * are not reachable or dead ends are omitted. (A dead end is a state from which no accepting state can be reached).
	 * Each state of the result also occurred in the input. Only the auxiliary empty stack state of the result is
	 * different.
	 *
	 * @param services
	 *            Ultimate services
	 * @param operand
	 *            operand
	 * @throws AutomataOperationCanceledException
	 *             if timeout exceeds
	 */
	public RemoveDeadEnds(final AutomataLibraryServices services,
			final INwaOutgoingLetterAndTransitionProvider<LETTER, STATE> operand) throws AutomataOperationCanceledException {
		super(services, operand);

		try {
			mReach = new NestedWordAutomatonReachableStates<>(mServices, mOperand);
			mReach.computeDeadEnds();
			mResult = new DoubleDeckerAutomatonFilteredStates<>(mServices, mReach, mReach.getWithOutDeadEnds());
		} catch (final AutomataOperationCanceledException oce) {
			final String taskDescription = "removing dead ends from automaton with " + mOperand.size() + " states.";
			oce.addRunningTaskInfo(new RunningTaskInfo(getClass(), taskDescription));
			throw oce;
		}

		printExitMessage();
		assert (new TransitionConsistencyCheck<>(mResult)).consistentForAll();
	}

	@Override
	public IDoubleDeckerAutomaton<LETTER, STATE> getResult() {
		return mResult;
	}

	@Override
	protected NestedWordAutomatonReachableStates<LETTER, STATE> getReach() {
		return mReach;
	}

	/**
	 * @return Size of the input automaton. If input was an automaton for on-demand construction, this is the size after
	 *         the on-demand construction.
	 */
	public int getInputSize() {
		return mReach.size();
	}

	@Override
	protected void modifyReachableStatesCopyForCheckResult(final ReachableStatesCopy<LETTER, STATE> rsc)
			throws AutomataOperationCanceledException {
		rsc.removeDeadEnds();
	}

	@Override
	protected boolean checkDownStates(final STATE state, final DoubleDeckerAutomaton<LETTER, STATE> reachableStatesCopy,
			final NestedWordAutomatonReachableStates<LETTER, STATE> reach) {
		final Set<STATE> rCSdownStates = reachableStatesCopy.getDownStates(state);
		final Set<STATE> rCAdownStates = reach.getWithOutDeadEnds().getDownStates(state,
				DoubleDeckerReachability.REACHABLE_AFTER_REMOVAL_OF_PRECIOUS_NOT_REACHERS);
		final boolean correct = rCSdownStates.containsAll(rCAdownStates);
		assert correct;
		// After enhanced non-live/dead end removal the following does not hold.
		// correct = correct && ResultChecker.isSubset(rCSdownStates, rCAdownStates);
		// assert correct;
		return correct;
	}

	@Override
	protected boolean checkResultFurther(final IDoubleDeckerAutomaton<LETTER, STATE> reachableStatesCopy)
			throws AutomataOperationCanceledException {
		return checkAllStatesAreInReachableStatesCopy(reachableStatesCopy);
	}
}
