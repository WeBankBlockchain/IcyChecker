/*
 * Copyright (C) 2016 University of Freiburg
 *
 * This file is part of the Ultimate Delta Debugger plug-in.
 *
 * The Ultimate Delta Debugger plug-in is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Ultimate Delta Debugger plug-in is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the Ultimate Delta Debugger plug-in. If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the Ultimate Delta Debugger plug-in, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the Ultimate Delta Debugger plug-in grant you additional permission
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.deltadebugger.core.parser.pst.interfaces;

import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTNode;

import de.uni_freiburg.informatik.ultimate.deltadebugger.core.text.ISourceDocument;
import de.uni_freiburg.informatik.ultimate.deltadebugger.core.text.ISourceRange;

/**
 * An IPSTNode represents a range of text in the source code and if possible the IASTNode that is generated from this
 * range of text. It serves as an intermediate data structure to compute potential changes to the source file.
 * The most important benefits over the original AST are:
 * <ul>
 * <li>Only nodes that are in the source file and which can be safely rewritten are part of the PST, but access to all
 * remaining parts of the original AST is still possible (with getUnexpandedChildNodes()).
 * <li>Comments, preprocessor directives and conditional blocks are part of the AST hierarchy (where possible), e.g. a
 * comment between two statements is actually located between the two siblings. This allows to take advantage of
 * locality.
 * <li>The PST structure is strictly hierarchical: Each node has its own source range that is only shared with
 * descendant nodes. A child node is always contained in the range of it's parent and siblings never overlap. Whenever
 * non-preprocessed code contains AST nodes that do overlap each others source range, these nodes are not expanded and a
 * overlapping/literal block node protects this source from accidental changes that result in syntax errors.
 * <li>Literal nodes can be inserted manually to protect source ranges from modifications without changing algorithms.
 * The algorithms just have to respect that literal nodes are supposed to be ignored.
 * <li>Unless the PST structure is modified concurrently all methods of PST nodes (that do not access AST nodes) are
 * thread safe. This is not the case for the original AST.
 * </ul>
 * IPSTNodes are divided into the following types:
 * <ul>
 * <li>Regular AST nodes which are generated by the C-parser as descendants of IASTTranslationUnit, e.g. a function
 * definition or an expression. Preprocessed code will only contain these nodes! (IPSTRegularNode)
 * <li>Nodes corresponding to preprocessor-code, e.g. an #include, a macro expansion or a comment. The source range may
 * result in zero or more regular nodes after being preprocessed. There exist IASTNode derived interfaces for these
 * nodes, but they are not actually a part of the original AST structure. (IPSTPreprocessorNode)
 * <li>Block nodes which group semantically related nodes together. Currently there are only conditional blocks, which
 * are special because they may contain inactive code and because the conditional directives belong together (in
 * contrast to other directives). These nodes do not have corresponding AST nodes.
 * <li>Literal nodes that mark a source range (and all originating nodes) as problematic or otherwise special. A special
 * case of this are overlapping block nodes where individual AST nodes overlap the same source range and cannot be part
 * of the strictly hierarchical PST.
 * </ul>
 * Acronym: PST = PreprocessorSyntaxTree
 */
public interface IPSTNode extends ISourceRange {
	/**
	 * @param action
	 *            PST visitor action.
	 * @return {@code true} iff visitor accepts
	 */
	boolean accept(IPSTVisitor action);
	
	/**
	 * @param index
	 *            Index.
	 * @param node
	 *            PST node
	 */
	void addChild(int index, IPSTNode node);
	
	/**
	 * @param node
	 *            PST node.
	 */
	void addChild(IPSTNode node);
	
	/**
	 * @param location
	 *            Source range.
	 * @return PST node
	 */
	IPSTNode findDescendantByLocation(ISourceRange location);
	
	/**
	 * @return AST node.
	 */
	IASTNode getAstNode();
	
	/**
	 * @return List of PST node children.
	 */
	List<IPSTNode> getChildren();
	
	/**
	 * @return Ending line number.
	 */
	int getEndingLineNumber();
	
	/**
	 * @return Parent PST node.
	 */
	IPSTNode getParent();
	
	/**
	 * @return Parent regular PST node.
	 */
	IPSTRegularNode getRegularParent();
	
	/**
	 * @return Source document.
	 */
	ISourceDocument getSource();
	
	/**
	 * @return Source text.
	 */
	String getSourceText();
	
	/**
	 * @return Starting line number.
	 */
	int getStartingLineNumber();
	
	/**
	 * @return Translation unit.
	 */
	IPSTTranslationUnit getTranslationUnit();
	
	/**
	 * @return Unexpanded AST child nodes.
	 */
	List<IASTNode> getUnexpandedChildNodes();
	
	/**
	 * @param index
	 *            Index.
	 */
	void removeChild(int index);
	
	/**
	 * @param node
	 *            Parent PST node.
	 */
	void setParent(IPSTNode node);
	
	/**
	 * @param astNodes
	 *            List of AST child nodes.
	 */
	void setUnexpandedChildNodes(List<IASTNode> astNodes);
}
