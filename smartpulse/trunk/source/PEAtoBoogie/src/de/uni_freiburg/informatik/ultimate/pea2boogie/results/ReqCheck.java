/*
 * Copyright (C) 2013-2015 Jochen Hoenicke (hoenicke@informatik.uni-freiburg.de)
 * Copyright (C) 2018 Daniel Dietsch (dietsch@informatik.uni-freiburg.de)
 * Copyright (C) 2013-2018 University of Freiburg
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
package de.uni_freiburg.informatik.ultimate.pea2boogie.results;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import de.uni_freiburg.informatik.ultimate.core.lib.models.annotation.Check;
import de.uni_freiburg.informatik.ultimate.lib.srparse.pattern.PatternType;
import de.uni_freiburg.informatik.ultimate.util.datastructures.DataStructureUtils;

/**
 *
 * @author Jochen Hoenicke (hoenicke@informatik.uni-freiburg.de)
 * @author Daniel Dietsch (dietsch@informatik.uni-freiburg.de)
 *
 */
public class ReqCheck extends Check {

	private static final long serialVersionUID = 6800618860906443122L;

	private final int mStartline;
	private final int mEndline;

	private final PatternType[] mReqs;

	public ReqCheck(final Check.Spec type) {
		this(EnumSet.of(type), 0, 0, new PatternType[0]);
	}

	public ReqCheck(final Check.Spec type, final PatternType[] reqs) {
		this(EnumSet.of(type), reqs);
	}

	private ReqCheck(final EnumSet<Check.Spec> types, final PatternType[] reqs) {
		this(types, -1, -1, reqs);
	}

	private ReqCheck(final EnumSet<Check.Spec> types, final int startline, final int endline,
			final PatternType[] reqs) {
		super(types, a -> ReqCheck.getCustomPositiveMessage(a, reqs), a -> ReqCheck.getCustomNegativeMessage(a, reqs));
		mStartline = startline;
		mEndline = endline;
		mReqs = reqs;
	}

	public int getStartLine() {
		return mStartline;
	}

	public int getEndLine() {
		return mEndline;
	}

	private static String getCustomPositiveMessage(final Spec spec, final PatternType[] types) {
		return getRequirementTexts(types) + " " + getPositiveMessage(spec);
	}

	private static String getCustomNegativeMessage(final Spec spec, final PatternType[] types) {
		return getRequirementTexts(types) + " " + getNegativeMessage(spec);
	}

	private static String getRequirementTexts(final PatternType[] types) {
		if (types.length == 0) {
			return "All requirements are";
		}
		final StringBuilder sb = new StringBuilder();
		sb.append("Requirement");
		if (types.length != 1) {
			sb.append("s");
		}
		final Iterator<PatternType> iter = Arrays.stream(types).iterator();
		sb.append(" ").append(iter.next().getId());
		while (iter.hasNext()) {
			sb.append(", ").append(iter.next().getId());
		}
		if (types.length != 1) {
			sb.append(" are");
		} else {
			sb.append(" is");
		}
		return sb.toString();
	}

	public ReqCheck merge(final ReqCheck other) {
		if (other == null) {
			return this;
		}
		if (other == this) {
			return this;
		}

		final EnumSet<Spec> newSpec = EnumSet.copyOf(getSpec());
		newSpec.addAll(other.getSpec());
		final int startline = Math.min(mStartline, other.mStartline);
		final int endline = Math.max(mEndline, other.mEndline);
		final PatternType[] reqs = DataStructureUtils.concat(mReqs, other.mReqs);
		return new ReqCheck(newSpec, startline, endline, reqs);
	}

	public Set<String> getIds() {
		return Arrays.stream(mReqs).map(a -> a.getId()).collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		if (mReqs.length == 0) {
			return super.toString() + " for all requirements";
		}
		return super.toString() + " for " + Arrays.stream(mReqs).map(a -> a.getId()).collect(Collectors.joining(", "));
	}

}
