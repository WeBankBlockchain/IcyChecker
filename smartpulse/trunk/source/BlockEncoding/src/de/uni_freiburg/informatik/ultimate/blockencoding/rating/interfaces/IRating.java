/*
 * Copyright (C) 2013-2015 Stefan Wissert
 * Copyright (C) 2015 University of Freiburg
 * 
 * This file is part of the ULTIMATE BlockEncoding plug-in.
 * 
 * The ULTIMATE BlockEncoding plug-in is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * The ULTIMATE BlockEncoding plug-in is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE BlockEncoding plug-in. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE BlockEncoding plug-in, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP), 
 * containing parts covered by the terms of the Eclipse Public License, the 
 * licensors of the ULTIMATE BlockEncoding plug-in grant you additional permission 
 * to convey the resulting work.
 */
/**
 * 
 */
package de.uni_freiburg.informatik.ultimate.blockencoding.rating.interfaces;

import de.uni_freiburg.informatik.ultimate.blockencoding.rating.RatingValueContainer;

/**
 * Objects of this kind contains the rating of a certain IMinimizedEdge. Since
 * there are several ways how to rate a edge, there are several classes which
 * implement this interface.
 * 
 * @author Stefan Wissert
 * 
 */
public interface IRating extends Comparable<IRating> {

	/**
	 * Returns the rated value, as a container object. This is needed because
	 * for several ratings there can be several ways to store the values.
	 * 
	 * @return a rating value container
	 */
	public RatingValueContainer<?> getRatingValueContainer();

	/**
	 * Sometimes (for statistical reasons) we need to get the rated value, as
	 * integer.
	 * 
	 * @return the rated value as integer
	 */
	public int getRatingValueAsInteger();
}
