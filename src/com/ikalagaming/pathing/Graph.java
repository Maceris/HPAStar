package com.ikalagaming.pathing;

import java.util.Optional;

/**
 * A graph that stores nodes.
 * 
 * @author Ches Burks
 *
 */
public interface Graph {
	public void addNode(Node n1, final int lvl);

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
