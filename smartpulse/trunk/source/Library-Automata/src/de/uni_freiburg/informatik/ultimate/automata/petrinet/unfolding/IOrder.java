/*
 * Copyright (C) 2012-2015 Julian Jarecki (jareckij@informatik.uni-freiburg.de)
 * Copyright (C) 2014-2015 Matthias Heizmann (heizmann@informatik.uni-freiburg.de)
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
package de.uni_freiburg.informatik.ultimate.automata.petrinet.unfolding;

import java.util.Comparator;

/**
 * Order of Events and Configurations.
 * 
 * @author Julian Jarecki (jareckij@informatik.uni-freiburg.de)
 * @author Matthias Heizmann (heizmann@informatik.uni-freiburg.de)
 * 
 * @param <LETTER>
 *            Type of letters from the alphabet used to label transitions
 * @param <PLACE>
 *            place content type
 */
public interface IOrder<LETTER, PLACE> extends Comparator<Event<LETTER, PLACE>> {

	/** Compares two events by comparing their local configurations. */
	@Override
	default int compare(final Event<LETTER, PLACE> o1, final Event<LETTER, PLACE> o2) {
		if (o1 == o2) {
			return 0;
		}
		final Configuration<LETTER, PLACE> c1 = o1.getLocalConfiguration();
		final Configuration<LETTER, PLACE> c2 = o2.getLocalConfiguration();
		return compare(c1, c2);
	}

	/**
	 * Compares two configurations.
	 * 
	 * @param o1
	 *            first configuration
	 * @param o2
	 *            second configuration
	 * @return the value according to {@link Comparator}
	 */
	int compare(Configuration<LETTER, PLACE> o1, Configuration<LETTER, PLACE> o2);
}
