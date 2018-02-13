package com.ikalagaming.pathing;

/**
 * An edge between two nodes.
 * 
 * @author Ches Burks
 *
 */
public interface Edge {

	/**
	 * Edge that exists only within a chunk.
	 */
	public static final int INTER_EDGE = 0;
	/**
	 * Edge that is between chunks.
	 */
	public static final int INTRA_EDGE = 1;

	public void setLevel(int lvl);

	public float getLength();

}
