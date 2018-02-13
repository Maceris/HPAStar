package com.ikalagaming.pathing;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * The steps to take to get from the start position to end position that is
 * calculated by the pathfinding algorithm. This should be easily traversed
 * either forwards or backwards.
 * 
 * @author Ches Burks
 *
 */
public class Path {

	/**
	 * One end on one side, the other on the other side. We can traverse either
	 * way.
	 */
	private ArrayDeque<Node> bakedPath;
	/**
	 * A path that is still being modified.
	 */
	private LinkedList<Node> linkedPath;

	public Path() {
		// TODO Auto-generated constructor stub
	}

	public Iterator<Node> forwardIter() {
		return bakedPath.iterator();
	}

	public Iterator<Node> backwardIter() {
		return bakedPath.descendingIterator();
	}

	public ArrayList<Node> toArrayList() {
		ArrayList<Node> nodes = new ArrayList<>();
		nodes.addAll(bakedPath);
		return nodes;
	}

}
