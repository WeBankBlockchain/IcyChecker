/*
 * Copyright (C) 2012-2015 Daniel Dietsch (dietsch@informatik.uni-freiburg.de)
 * Copyright (C) 2015 University of Freiburg
 * 
 * This file is part of the ULTIMATE Core.
 * 
 * The ULTIMATE Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * The ULTIMATE Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE Core. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE Core, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP), 
 * containing parts covered by the terms of the Eclipse Public License, the 
 * licensors of the ULTIMATE Core grant you additional permission 
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.core.model.models;

import java.util.List;

/**
 * This interface describes models that represent a multi-graph structure, i.e. a graph with possibly multiple directed
 * edges between two nodes. The interface requires that the edges of such a multigraph have to be represented by
 * separate objects described by the {@link IMultigraphEdge} interface.
 * 
 * For a multigraph without explicit edges see {@link ILabeledEdgesMultigraph}.
 * 
 * @author dietsch
 * 
 * @param <V>
 *            is the type of the nodes of the concrete model. This parameter should be used by sub-interfaces to specify
 *            a more restrictive type and thus free clients from the need of down-casting.<br>
 *            Final implementations should fix this parameter to their type, e.g. a (fictive) type
 *            <tt>FinalModelNode</tt> would declare
 *            <tt>public final class FinalModelNode implements IExplicitEdgesMultigraph&lt;FinalModelNode,E&gt;</tt>.
 * @param <E>
 *            is the type of the edges of the concrete model. This parameter should be used by sub-interfaces to specify
 *            a more restrictive type and thus free clients from the need of down-casting.<br>
 *            Final implementations should fix this parameter to the corresponding node type, e.g. a (fictive) type
 *            <tt>FinalModelNode</tt> and the corresponding edge type <tt>FinalModelEdge</tt> would declare
 *            <tt>public final class FinalModelNode implements IExplicitEdgesMultigraph&lt;FinalModelNode,FinalModelEdge&gt;</tt>
 *            .
 * @param <VL>
 *            is the vertex label type (this can also be the actual type)
 * @param <EL>
 *            is the edge label type (this can also be the actual type)
 * @see IElement
 * @see IMultigraphEdge
 * @see IVisualizable
 * @see IWalkable
 */
public interface IExplicitEdgesMultigraph<V extends IExplicitEdgesMultigraph<V, E, VL, EL, VIS>, E extends IMultigraphEdge<V, E, VL, EL, VIS>, VL, EL, VIS extends IVisualizable<VIS>>
		extends IElement, IVisualizable<VIS>, IWalkable {

	/***
	 * The method returns all edges between immediate predecessors of the current node and the current node, i.e. edges
	 * that are directed at the current node. If there are no such edges, this method must return an empty list.
	 * 
	 * This list should be treated as not modifiable. Use it only to iterate or determine size.
	 * 
	 * @return A list containing all edges between the direct predecessors of the current node and the current node.
	 */
	List<E> getIncomingEdges();

	/***
	 * The method returns all edges between immediate successors of the current node and the current node, i.e. edges
	 * that lead away from the current node. If there are no such edges, this method must return an empty list.
	 * 
	 * This list should be treated as not modifiable. Use it only to iterate or determine size.
	 * 
	 * @return A list containing all edges between the direct successors of the current node and the current node.
	 */
	List<E> getOutgoingEdges();

	VL getLabel();
}
