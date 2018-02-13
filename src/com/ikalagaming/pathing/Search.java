package com.ikalagaming.pathing;

import java.util.ArrayList;

/**
 * Abstract search engine based on the pathfinding library from the University
 * of Alberta.
 */
public abstract class Search {

	/**
	 * The default value for node limit, which means unlimited search. ( *
	 * {@value} )
	 */
	protected static final int UNLIMITED_SEARCH = -1;

	private long nodeLimit;

	/**
	 * The default constructor.
	 */
	public Search() {
		this.nodeLimit = Search.UNLIMITED_SEARCH;
	}

	/**
	 * Find a path from start to target.
	 *
	 * @param env the environment to search in.
	 * @param start the start point
	 * @param target the end point
	 * @return false, if search was aborted due to node limit.
	 */
	public boolean findPath(final Environment env, Node start, Node target) {
		return false;
	}

	/**
	 * Returns the limit for the number of nodes while searching.
	 *
	 * @return The node limit
	 * @see #setNodeLimit(long)
	 */
	public long getNodeLimit() {
		return this.nodeLimit;
	}

	/**
	 * Returns the path found. This list is immutable.
	 *
	 * @return the path.
	 */
	public ArrayList<Node> getPath() {
		return new ArrayList<>();
	}

	/**
	 * Get a list with character labels for each visited node. Space character
	 * means not visited, otherwise the character can have different values and
	 * meanings depending on the concrete search engine.
	 *
	 * @return a list of labels for visited nodes.
	 */
	public ArrayList<Character> getVisitedNodes() {
		return new ArrayList<>();
	}

	/**
	 * Sets the node limit for the search engine. The default is
	 * {@value #UNLIMITED_SEARCH} which means unlimited search. The node limit
	 * is only a hint and the search engine might ignore it.
	 *
	 * @param nodesLimit the new limit.
	 */
	public void setNodeLimit(final long nodesLimit) {
		this.nodeLimit = nodesLimit;
	}

}
