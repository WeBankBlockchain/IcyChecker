/*
 * Copyright (C) 2015 Daniel Dietsch (dietsch@informatik.uni-freiburg.de)
 * Copyright (C) 2015 University of Freiburg
 * 
 * This file is part of the ULTIMATE WitnessParser plug-in.
 * 
 * The ULTIMATE WitnessParser plug-in is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * The ULTIMATE WitnessParser plug-in is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE WitnessParser plug-in. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE WitnessParser plug-in, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP), 
 * containing parts covered by the terms of the Eclipse Public License, the 
 * licensors of the ULTIMATE WitnessParser plug-in grant you additional permission 
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.witnessparser.graph;

import de.uni_freiburg.informatik.ultimate.core.lib.models.annotation.ModernAnnotations;
import de.uni_freiburg.informatik.ultimate.core.model.models.IElement;
import de.uni_freiburg.informatik.ultimate.core.model.models.ModelUtils;
import de.uni_freiburg.informatik.ultimate.core.model.models.annotation.Visualizable;
import de.uni_freiburg.informatik.ultimate.witnessparser.Activator;

/**
 * 
 * @author Daniel Dietsch (dietsch@informatik.uni-freiburg.de)
 *
 */
public class WitnessGraphAnnotation extends ModernAnnotations {

	public enum WitnessType {
		CORRECTNESS_WITNESS, VIOLATION_WITNESS
	}

	private static final long serialVersionUID = 1L;
	private static final String KEY = Activator.PLUGIN_ID + "_Graph";

	@Visualizable
	private final String mSourceCodeLanguage;

	@Visualizable
	private final WitnessType mType;

	public WitnessGraphAnnotation(final String sourceodelang, final WitnessType type) {
		mSourceCodeLanguage = sourceodelang;
		mType = type;
	}

	public void annotate(IElement node) {
		if (node instanceof WitnessNode) {
			annotate((WitnessNode) node);
		}
	}

	public void annotate(WitnessNode node) {
		node.getPayload().getAnnotations().put(KEY, this);
	}

	public static WitnessGraphAnnotation getAnnotation(final IElement node) {
		return ModelUtils.getAnnotation(node, KEY, a -> (WitnessGraphAnnotation) a);
	}

	public String getSourceCodeLanguage() {
		return mSourceCodeLanguage;
	}

	public WitnessType getWitnessType() {
		return mType;
	}
}
