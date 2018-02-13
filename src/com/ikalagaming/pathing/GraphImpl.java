package com.ikalagaming.pathing;

import java.util.Optional;

/**
 * An implementation of the {@link Graph} interface.
 * 
 * @author Ches Burks
 *
 */
class GraphImpl implements Graph {

	private Node[][] nodes;
	private final int w;
	private final int h;

	public GraphImpl(final int width, final int height) {
		nodes = new Node[width][height];
		w = width;
		h = height;
	}

	@Override
	public void addNode(Node n1, final int lvl) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addEdge(Node n1, Node n2, final int lvl, final float weight,
		final int edgeType) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<Node> getNode(final int x, final int y) {
		if (x < 0 || x >= w || y < 0 || y >= h) {
			return Optional.empty();
		}
		try {
			return Optional.of(nodes[x][y]);
		}
		catch (NullPointerException e) {
			return Optional.empty();
		}

	}
}
