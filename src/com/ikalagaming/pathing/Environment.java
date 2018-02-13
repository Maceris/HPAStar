package com.ikalagaming.pathing;

import java.util.ArrayList;

/**
 * Interface to search environment. Based on the pathfinding library from the
 * University of Alberta.
 * 
 */
public abstract class Environment {

	/**
	 * Information about a successor of a node in the environment.
	 */
	abstract class Successor {
		/**
		 * The target.
		 */
		Node target;

		/**
		 * The cost
		 */
		int cost;

		/**
		 * Default constructor.
		 */
		public Successor() {}

		/**
		 * Constructs a {@link Successor} with default values.
		 * 
		 * @param targetNode the coordinates of the target
		 * @param nodeCost the cost
		 */
		public Successor(final Node targetNode, final int nodeCost) {
			// TODO figure out what these values are
			this.target = targetNode;
			this.cost = nodeCost;
		}

	}

	/**
	 * Returns a heuristic (guess) of the distance between two points.
	 * 
	 * @param start the start point
	 * @param target the end point
	 * @return the estimated distance from start to target.
	 */
	public int getHeuristic(final Node start, final Node target) {
		return 0;
	}

	public int getMaxCost() {
		return 0;
	}

	public int getMinCost() {
		return 0;
	}

	public int getNumberNodes() {
		return 0;
	}

	/**
	 * Generate successor nodes for the search.
	 * 
	 * @param current Current node
	 * @param lastNode Can be used to prune nodes, is set to
	 *            {@link Node#DNE_NODE} in {@link Search#checkPathExists}
	 * @param result the list to put results into.
	 */
	public void getSuccessors(final Node current, final Node lastNode,
		ArrayList<Successor> result) {}

	public boolean isValidNode(Node point) {
		return false;
	}

}
