/*
 * Copyright (C) 2010-2015 Matthias Heizmann (heizmann@informatik.uni-freiburg.de)
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
package de.uni_freiburg.informatik.ultimate.automata.nestedword.buchi;

import de.uni_freiburg.informatik.ultimate.automata.nestedword.NestedRun;

/**
 * Infinite run of a Büchi nested word automaton for a nested word of the form <tt>u.v^ω</tt>.
 * 
 * @author Matthias Heizmann (heizmann@informatik.uni-freiburg.de)
 * @param <LETTER>
 *            letter type
 * @param <STATE>
 *            state type
 */
public class NestedLassoRun<LETTER, STATE> {
	private final NestedRun<LETTER, STATE> mStem;
	private final NestedRun<LETTER, STATE> mLoop;

	/**
	 * Constructor.
	 * 
	 * @param stem
	 *            stem
	 * @param loop
	 *            loop
	 */
	public NestedLassoRun(final NestedRun<LETTER, STATE> stem, final NestedRun<LETTER, STATE> loop) {
		mStem = stem;
		mLoop = loop;
	}

	public NestedRun<LETTER, STATE> getStem() {
		return mStem;
	}

	public NestedRun<LETTER, STATE> getLoop() {
		return mLoop;
	}

	public NestedLassoWord<LETTER> getNestedLassoWord() {
		return new NestedLassoWord<>(getStem().getWord(), getLoop().getWord());
	}

	@Override
	public String toString() {
		return "Stem: " + mStem + " Loop:" + mLoop;
	}
}
