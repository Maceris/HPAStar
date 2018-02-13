package com.ikalagaming.pathing;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The main class for dealing with pathfinding. Used to create a maze and do
 * pathing between points.
 * 
 * @author Ches Burks
 *
 */
public class Pathing {

	private Set<Entrance> entrances;
	/**
	 * Array of sets of clusters. One set per level of cluster.
	 */
	private ArrayList<Set<Cluster>> clusters;

	private Graph graph;

	private int clusterSize = 30;
	private int clustGrouping = 2;

	/**
	 * @param clusterSz the size of the lowest level cluster
	 * @param n higher level clusters are nxn lower level clusters.
	 */
	public Pathing(final int width, final int height, final int clusterSz,
		final int n) {
		this.entrances = new HashSet<>();
		this.clusters = new ArrayList<>();
		this.graph = new GraphImpl(width, height);
		this.clusterSize = clusterSz;
		this.clustGrouping = n;
	}

	/**
	 * Abstracts the maze into clusters.
	 */
	private void abstractMaze() {
		this.clusters.set(1, this.buildClusters(1));
		Set<Cluster> c = this.clusters.get(1);
		for (Cluster c1 : c) {
			for (Cluster c2 : c) {
				if (this.adjacent(c1, c2)) {
					this.entrances.addAll(this.buildEntrances(c1, c2));
				}
			}
		}
	}

	/**
	 * Adds a level of clusters to the graph. This expects the level below this
	 * to already exist.
	 *
	 * @param lvl the level to add.
	 */
	private void addLevelToGraph(final int lvl) {
		// TODO handle invalid levels
		this.clusters.set(lvl, this.buildClusters(lvl));
		Set<Cluster> cL = this.clusters.get(lvl);
		for (Cluster c1 : cL) {
			for (Cluster c2 : cL) {
				if (!this.adjacent(c1, c2)) {
					continue;
				}
				for (Entrance e : this.getEntrances(c1, c2)) {
					e.getNode1().setLevel(lvl);
					e.getNode2().setLevel(lvl);
					e.getEdge().setLevel(lvl);
				}
			}
		}
		for (Cluster c : cL) {
			for (Node n1 : c.getNodes()) {
				for (Node n2 : c.getNodes()) {
					if (n1 == n2) {
						continue;
					}
					float d = this.searchForDistance(n1, n2, c);
					if (Float.isFinite(d)) {
						this.graph.addEdge(n1, n2, lvl, d, Edge.INTRA_EDGE);
					}
				}
			}
		}
	}

	private boolean adjacent(Cluster c1, Cluster c2) {
		// TODO finish this
		// if c1=c2 false, if adjacent true, if not false
		return true;
	}

	private Set<Cluster> buildClusters(final int lvl) {
		// TODO finish this
		return new HashSet<>();
	}

	private Set<Entrance> buildEntrances(final Cluster c1, final Cluster c2) {
		// TODO finish this
		return new HashSet<>();
	}

	/**
	 * Creates the abstract graph of the problem.
	 */
	private void buildGraph() {
		for (Entrance e : this.entrances) {
			Cluster c1 = e.getCluster1(1);
			Cluster c2 = e.getCluster2(1);
			Node n1 = this.newNode(e, c1);
			Node n2 = this.newNode(e, c2);
			this.graph.addNode(n1, 1);
			this.graph.addNode(n2, 1);
			this.graph.addEdge(n1, n2, 1, 1, Edge.INTER_EDGE);
		}
		for (Cluster c : this.clusters.get(1)) {
			for (Node n1 : c.getNodes()) {
				for (Node n2 : c.getNodes()) {
					if (n1 == n2) {
						continue;
					}
					float d = this.searchForDistance(n1, n2, c);
					if (Float.isFinite(d)) {
						this.graph.addEdge(n1, n2, 1, d, Edge.INTRA_EDGE);
					}
				}
			}
		}
	}

	private boolean existsClearPath(final Node s, final Node g) {
		int dx = Math.abs(g.getX() - s.getX());
		int dy = Math.abs(g.getY() - s.getY());
		int x = s.getX();
		int y = s.getY();
		int n = 1 + dx + dy;
		int x_inc = (g.getX() > s.getX()) ? 1 : -1;
		int y_inc = (g.getY() > s.getY()) ? 1 : -1;
		int error = dx - dy;
		dx *= 2;
		dy *= 2;

		for (; n > 0; --n) {
			if (!this.graph.getNode(x, y).isPresent()) {
				return false;
			}

			if (error > 0) {
				x += x_inc;
				error -= dy;
			}
			else {
				y += y_inc;
				error += dx;
			}
		}
		return false;
	}

	/**
	 * Adds edges between the node and nodes on the border of the cluster which
	 * are reachable from that node.
	 *
	 * @param s the node to insert.
	 * @param c the cluster to insert into.
	 */
	private void connectToBorder(Node s, Cluster c) {
		int l = c.getLevel();
		for (Node n : c.getNodes()) {
			if (n.getLevel() < l) {
				continue;
			}
			float d = this.searchForDistance(s, n, c);
			if (Float.isFinite(d)) {
				this.graph.addEdge(s, n, l, d, Edge.INTRA_EDGE);
			}
		}
	}

	private Cluster determineCluster(Node s, int l) {
		// TODO finish this
		return null;
	}

	/**
	 * Returns the set of entrances that are shared between the given clusters.
	 * 
	 * @param c1 the first cluster.
	 * @param c2 the second cluster.
	 * @return a set of entrances shared between the two clusters.
	 */
	private Set<Entrance> getEntrances(final Cluster c1, final Cluster c2) {
		return entrances
			.parallelStream()
			.filter(
				(e) -> c1 == e.getCluster1(c1.getLevel()) ? c2 == e
					.getCluster2(c2.getLevel()) : c1 == e.getCluster2(c1
					.getLevel()) && c2 == e.getCluster1(c2.getLevel()))
			.collect(Collectors.toSet());
	}

	/**
	 * Runs a hiearchial search for the path from start to goal node at the
	 * given level.
	 *
	 * @param s the start node.
	 * @param g the goal node.
	 * @param l the level to search at.
	 * @return
	 */
	public Path hierarchialSearch(Node s, Node g, final int l) {
		this.insertNode(s, l);
		this.insertNode(g, l);
		Path absPath = this.searchForPath(s, g, l);
		Path lowLvlPath = this.refinePath(absPath, l);
		Path smPath = this.smoothPath(lowLvlPath);
		return smPath;
	}

	/**
	 * Insert a node into the graph. (Used for start and end nodes).
	 *
	 * @param s the node to insert.
	 * @param maxLevel the level to insert at.
	 */
	private void insertNode(Node s, final int maxLevel) {
		for (int l = 0; l < maxLevel; ++l) {
			Cluster c = this.determineCluster(s, l);
			this.connectToBorder(s, c);
		}
		s.setLevel(maxLevel);
	}

	/**
	 * Merges paths and returns a new path that starts at one end, goes to the
	 * shared node, and continues to the other end. The shared node appears only
	 * once. If the paths have the same start and end point, it does not change
	 * the path. If they do not share a path, returns null.
	 *
	 * @param p1 the first path.
	 * @param p2 the second path.
	 * @return the newly merged path, or empty if none can be created.
	 */
	public Optional<Path> merge(Path p1, Path p2) {
		// TODO finish this
		return null;
	}

	private Node newNode(Entrance e, Cluster c) {
		// TODO finish this
		return null;
	}

	/**
	 * Does pre-processing of the multilevel graph.
	 *
	 * @param maxLevel the max cluster level to build.
	 */
	private void preprocessing(final int maxLevel) {
		for (int l = 0; l < maxLevel; ++l) {
			this.clusters.add(new HashSet<>());
		}
		this.abstractMaze();
		this.buildGraph();
		for (int l = 2; l <= maxLevel; ++l) {
			this.addLevelToGraph(l);
		}
	}

	private Path refinePath(final Path p, final int l) {
		return null;
	}

	private float searchForDistance(Node n1, Node n2, Cluster c) {
		// TODO finish this
		return Float.POSITIVE_INFINITY;
	}

	private Path searchForPath(final Node s, final Node g, final int l) {
		// TODO finish this
		return null;
	}

	private Path smoothPath(final Path p) {
		ArrayList<Node> pNodes = p.toArrayList();
		if (pNodes.isEmpty()) {
			return null;// TODO throw exception
		}
		ArrayDeque<Node> result = new ArrayDeque<>();
		result.addLast(pNodes.get(0));
		int i, j;
		for (i = 0; i < pNodes.size() - 2; ++i) {
			j = i + 1;
			while (existsClearPath(pNodes.get(i), pNodes.get(j))) {
				++j;
			}
			result.addLast(pNodes.get(j));
		}
		// TODO finish this
		return null;
	}
}
