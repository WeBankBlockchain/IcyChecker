/*
 * Copyright (C) 2013-2015 Daniel Dietsch (dietsch@informatik.uni-freiburg.de)
 * Copyright (C) 2015 University of Freiburg
 *
 * This file is part of the ULTIMATE ModelCheckerUtils Library.
 *
 * The ULTIMATE ModelCheckerUtils Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ULTIMATE ModelCheckerUtils Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE ModelCheckerUtils Library. If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE ModelCheckerUtils Library, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the ULTIMATE ModelCheckerUtils Library grant you additional permission
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.modelcheckerutils.cfg.structure;

import de.uni_freiburg.informatik.ultimate.core.lib.models.ModifiableMultigraphEdge;
import de.uni_freiburg.informatik.ultimate.core.model.models.IPayload;
import de.uni_freiburg.informatik.ultimate.core.model.models.annotation.Visualizable;

/**
 * Edge of an ICFG.
 *
 * @author Matthias Heizmann (heizmann@informatik.uni-freiburg.de)
 *
 */
public abstract class IcfgEdge extends ModifiableMultigraphEdge<IcfgLocation, IcfgEdge, IcfgLocation, IcfgEdge>
		implements IIcfgTransition<IcfgLocation> {

	private static final long serialVersionUID = -7368006453981803743L;

	protected IcfgEdge(final IcfgLocation source, final IcfgLocation target, final IPayload payload) {
		super(source, target, payload);
	}

	@Override
	public IcfgEdge getLabel() {
		return this;
	}

	@Override
	@Visualizable
	public String getPrecedingProcedure() {
		final IcfgLocation source = getSource();
		return source == null ? null : source.getProcedure();
	}

	@Override
	@Visualizable
	public String getSucceedingProcedure() {
		final IcfgLocation target = getTarget();
		return target == null ? null : target.getProcedure();
	}
}
