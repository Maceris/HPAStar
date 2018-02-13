package com.ikalagaming.pathing;

import com.ikalagaming.pathing.Environment.Successor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Utilities for running searches, based on the pathfinding library from the
 * University of Alberta.
 */
public class SearchUtils {

	private Node targ;
	private Environment environment;
	private HashMap<Node, Boolean> mark;
	private ArrayList<ArrayList<Successor>> successorStack;

	/**
	 * Checks if a path exists in the environment from start to target.
	 *
	 * @param env the environment
	 * @param start the start node
	 * @param target the target node
	 * @return true if a path exists, otherwise false.
	 */
	public boolean checkPathExists(final Environment env, final Node start,
		final Node target) {
		this.environment = env;
		this.targ = target;
		this.mark.clear();
		return this.searchPathExists(start, 0);
	}

	/**
	 * Sets values of start and target to be random valid points on the map.
	 *
	 * @param env the environment
	 * @param start the start node. THIS IS MODIFIED.
	 * @param target the target node. THIS IS MODIFIED.
	 */
	public void findRandomStartTarget(final Environment env, Node start,
		Node target) {

		Node s = new Node();
		Node t = new Node();
		final int nodeNum = env.getNumberNodes();
		do {
			this.setRandValues(s, nodeNum);
			this.setRandValues(t, nodeNum);
		}
		while (!env.isValidNode(start) || !env.isValidNode(target)
			|| start.equals(target)
			|| !this.checkPathExists(env, start, target));
	}

	private boolean searchPathExists(Node node, int depth) {
		assert (this.environment.isValidNode(node));
		if (this.mark.get(node)) {
			return false;
		}
		if (node.equals(this.targ)) {
			return true;
		}
		this.mark.put(node, true);
		assert (depth >= 0);
		ArrayList<Successor> successors =
			this.successorStack.get(this.successorStack.size() - 1);
		this.environment.getSuccessors(node, Node.DNE_NODE, successors);
		int numSuccessors = successors.size();
		for (int i = 0; i < numSuccessors; ++i) {
			Successor successor =
				this.successorStack.get(this.successorStack.size() - 1).get(i);
			Node targetNode = successor.target;
			assert (this.environment.isValidNode(targetNode));
			if (this.searchPathExists(targetNode, depth + 1)) {
				return true;
			}
		}
		return false;
	}

	private void setRandValues(Node n, final int size) {
		long range = size * 2;
		long x = (long) (Math.random() * range) - size;
		long y = (long) (Math.random() * range) - size;
		x %= Node.MAX_VALUE;
		y %= Node.MAX_VALUE;
		n.setX((int) x);
		n.setY((int) y);
	}
}
