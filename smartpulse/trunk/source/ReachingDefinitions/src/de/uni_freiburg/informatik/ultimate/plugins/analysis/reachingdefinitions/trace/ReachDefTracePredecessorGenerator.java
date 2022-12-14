/*
 * Copyright (C) 2014-2015 Daniel Dietsch (dietsch@informatik.uni-freiburg.de)
 * Copyright (C) 2015 University of Freiburg
 * 
 * This file is part of the ULTIMATE ReachingDefinitions plug-in.
 * 
 * The ULTIMATE ReachingDefinitions plug-in is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * The ULTIMATE ReachingDefinitions plug-in is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE ReachingDefinitions plug-in. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE ReachingDefinitions plug-in, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP), 
 * containing parts covered by the terms of the Eclipse Public License, the 
 * licensors of the ULTIMATE ReachingDefinitions plug-in grant you additional permission 
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.plugins.analysis.reachingdefinitions.trace;

import java.util.ArrayList;
import java.util.List;

import de.uni_freiburg.informatik.ultimate.plugins.analysis.reachingdefinitions.annotations.IAnnotationProvider;
import de.uni_freiburg.informatik.ultimate.plugins.analysis.reachingdefinitions.annotations.ReachDefStatementAnnotation;
import de.uni_freiburg.informatik.ultimate.plugins.generator.rcfgbuilder.cfg.CodeBlock;
import de.uni_freiburg.informatik.ultimate.plugins.generator.rcfgbuilder.cfg.ParallelComposition;
import de.uni_freiburg.informatik.ultimate.plugins.generator.rcfgbuilder.cfg.SequentialComposition;
import de.uni_freiburg.informatik.ultimate.plugins.generator.rcfgbuilder.cfg.StatementSequence;
import de.uni_freiburg.informatik.ultimate.plugins.generator.rcfgbuilder.util.RCFGEdgeVisitor;

public class ReachDefTracePredecessorGenerator extends RCFGEdgeVisitor {

	private final IAnnotationProvider<ReachDefStatementAnnotation> mProvider;
	private final String mKey;

	public ReachDefTracePredecessorGenerator(IAnnotationProvider<ReachDefStatementAnnotation> provider) {
		this(provider, null);
	}

	public ReachDefTracePredecessorGenerator(IAnnotationProvider<ReachDefStatementAnnotation> provider, String key) {
		mProvider = provider;
		mKey = key;
	}

	private List<ReachDefStatementAnnotation> rtr;

	/**
	 * Returns all {@link ReachDefStatementAnnotation}s from the predecessor
	 * 
	 * @param e
	 * @return
	 */
	public List<ReachDefStatementAnnotation> process(CodeBlock predecessor) {
		rtr = new ArrayList<ReachDefStatementAnnotation>();
		visit(predecessor);
		return rtr;
	}

	@Override
	protected void visit(SequentialComposition c) {
		final List<CodeBlock> blck = c.getCodeBlocks();
		if (blck == null || blck.isEmpty()) {
			return;
		}
		super.visit(blck.get(blck.size() - 1));
	}

	@Override
	protected void visit(ParallelComposition c) {
		throw new UnsupportedOperationException("Parallel composition is not supported");
	}

	@Override
	protected void visit(StatementSequence stmtSeq) {
		final ReachDefStatementAnnotation annot = getAnnotation(stmtSeq);
		if (annot != null) {
			rtr.add(annot);
		}
		super.visit(stmtSeq);
	}

	private ReachDefStatementAnnotation getAnnotation(StatementSequence stmtSeq) {
		if (mKey == null) {
			return mProvider.getAnnotation(stmtSeq.getStatements().get(stmtSeq.getStatements().size() - 1));
		} else {
			return mProvider.getAnnotation(stmtSeq.getStatements().get(stmtSeq.getStatements().size() - 1), mKey);
		}
	}
}
