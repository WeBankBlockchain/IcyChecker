/*
 * Copyright (C) 2016 Matthias Heizmann (heizmann@informatik.uni-freiburg.de)
 * Copyright (C) 2016 University of Freiburg
 * 
 * This file is part of the ULTIMATE LassoRanker Library.
 * 
 * The ULTIMATE LassoRanker Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * The ULTIMATE LassoRanker Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE LassoRanker Library. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE LassoRanker Library, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP), 
 * containing parts covered by the terms of the Eclipse Public License, the 
 * licensors of the ULTIMATE LassoRanker Library grant you additional permission 
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.lassoranker.nontermination;

import java.io.Serializable;
import java.util.Map;

import de.uni_freiburg.informatik.ultimate.logic.Term;


/**
 * Represents a non-termination argument for a lasso program.
 * 
 * @author Matthias Heizmann (heizmann@informatik.uni-freiburg.de
 */
public class InfiniteFixpointRepetition extends NonTerminationArgument implements Serializable {

	private static final long serialVersionUID = 6114137691934435484L;
	
	private final Map<Term, Term> mValuesAtInit;
	private final Map<Term, Term> mValuesAtHonda;
	public InfiniteFixpointRepetition(final Map<Term, Term> valuesAtInit, final Map<Term, Term> valuesAtHonda) {
		super();
		mValuesAtInit = valuesAtInit;
		mValuesAtHonda = valuesAtHonda;
	}
	/**
	 * @return the valuesAtInit
	 */
	public Map<Term, Term> getValuesAtInit() {
		return mValuesAtInit;
	}
	/**
	 * @return the valuesAtHonda
	 */
	public Map<Term, Term> getValuesAtHonda() {
		return mValuesAtHonda;
	}
	
	
	
	
}
