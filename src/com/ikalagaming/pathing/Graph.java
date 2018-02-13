package com.ikalagaming.pathing;

import java.util.Optional;

/**
 * A graph that stores nodes.
 * 
 * @author Ches Burks
 *
 */
public interface Graph {
	/**
	 * Add a node at a given level.
	 * 
	 * @param n1 the node to add
	 * @param lvl the level that node is at
	 */
	public void addNode(Node n1, final int lvl);

	/**
	 * Add an edge between two nodes, at a certain level, with weight. It may be
	 * either a {@link Edge#INTER_EDGE} or {@link Edge#INTRA_EDGE}.
	 * 
	 * @param n1 the first node
	 * @param n2 the second node
	 * @param lvl the level of the edge
	 * @param weight the edge weight
	 * @param edgeType {@link Edge#INTER_EDGE} or {@link Edge#INTRA_EDGE}
	 */
	public void addEdge(Node n1, Node n2, final int lvl, final float weight,
		final int edgeType);

	/**
	 * Gets the node at (x, y). If there is not one there or the bounds are
	 * invalid, returns an empty optional.
	 * 
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 * @return an optional of the node at that position, which is empty if no
	 *         node exists.
	 */
	public Optional<Node> getNode(final int x, final int y);
}
