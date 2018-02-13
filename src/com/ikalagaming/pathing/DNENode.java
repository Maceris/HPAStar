package com.ikalagaming.pathing;

/**
 * A node that Does Not Exist. It cannot be modified and has invalid values.
 *
 * @author Ches Burks
 *
 */
public class DNENode extends Node {

	/**
	 *
	 */
	private static final long serialVersionUID = 2755841024598224077L;

	/**
	 * Does nothing.
	 */
	public DNENode() {}

	/**
	 * Does nothing.
	 *
	 * @param x ignored
	 * @param y ignored
	 */
	public DNENode(int x, int y) {}

	/**
	 * Does nothing.
	 *
	 * @param x ignored
	 * @param y ignored
	 * @param level ignored
	 */
	public DNENode(int x, int y, int level) {}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	@Override
	public int compareTo(Node o) {
		return super.compareTo(o);
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int getLevel() {
		return Short.MIN_VALUE;
	}

	@Override
	public int getX() {
		return Integer.MIN_VALUE;
	}

	@Override
	public int getY() {
		return Integer.MIN_VALUE;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public void setLevel(int level) {}

	@Override
	public void setObstacle(boolean obstacle) {}

	@Override
	public void setX(int x) {}

	@Override
	public void setY(int y) {}

}
