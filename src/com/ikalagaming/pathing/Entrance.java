package com.ikalagaming.pathing;

/**
 * An entrance between two adjacent clusters.
 * 
 * @author Ches Burks
 *
 */
public interface Entrance {

	public Cluster getCluster1(int lvl);

	public Cluster getCluster2(int lvl);

	public Node getNode1();

	public Node getNode2();

	public Edge getEdge();
}
