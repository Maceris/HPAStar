package com.ikalagaming.pathing;

import java.io.Serializable;

/**
 * A point in 2d space. Not synchronized, and must be accessed in a thread-safe
 * manner.
 *
 * @author Ches Burks
 *
 */
public class Node implements Comparable<Node>, Serializable {
	/**
	 * Generated Serial version UID.
	 */
	private static final long serialVersionUID = -6533337874674926765L;
	/**
	 * The maximum value a point may have for its x or y values.
	 */
	public static final int MAX_VALUE = 2147483646;
	/**
	 * The minimum value a point may have for its x or y values.
	 */
	public static final int MIN_VALUE = -2147483647;
	/**
	 * The highest valid value a point may have as its level.
	 */
	public static final int MAX_LEVEL = 32767;
	/**
	 * The lowest valid value a point may have as its level.
	 */
	public static final int MIN_LEVEL = 0;

	/**
	 * Returns a new synchronized (thread-safe) point backed by the specified
	 * object. In order to guarantee serial access, it is critical that
	 * <b>all</b> access to the backing point is accomplished through the
	 * returned collection. <br>
	 * Failure to follow this advice may result in non-deterministic behavior. <br>
	 * It is also suggested to create the new point in the call, such as
	 * {@code synchronizedPoint(new Point(...))}.
	 *
	 * @param p The point to be "wrapped" in a synchronized point.
	 * @return A synchronized view of the given point.
	 */
	public static Node synchronizedPoint(Node p) {
		return new NodeSynchroinzed(p);
	}

	/**
	 * The x value for the point.
	 */
	private int xValue;
	/**
	 * The y value for the point.
	 */
	private int yValue;
	/**
	 * The level for the point.
	 */
	private short lValue;

	/**
	 * Creates a new point at (0, 0) with a level of 0. This is the equivalent
	 * of calling {@link #Node(int, int, int) Point(0, 0, 0)}.
	 *
	 * @see #Node(int, int, int)
	 */
	public Node() {
		this(0, 0, 0);
	}

	/**
	 * Creates a new point at (x, y) with a level of 0. This is the equivalent
	 * of calling {@link #Node(int, int, int) Point(x, y, 0)}.
	 *
	 * @param x The starting X position of the point.
	 * @param y The starting Y position of the point.
	 *
	 * @throws IllegalArgumentException if the passed coordinates are not valid.
	 *
	 * @see #Node(int, int, int)
	 */
	public Node(final int x, final int y) {
		this(x, y, 0);
	}

	/**
	 * Creates a new point at (x, y) with the supplied level.
	 *
	 * @param x The starting X position of the point.
	 * @param y The starting Y position of the point.
	 * @param level The starting level of the point.
	 *
	 * @throws IllegalArgumentException if the passed coordinates or level are
	 *             not valid.
	 */
	public Node(final int x, final int y, final int level) {
		this.init();
		this.setX(x);
		this.setY(y);
		this.setLevel(level);
	}

	@Override
	public int compareTo(Node o) {
		if (this.getX() < o.getX()) {
			return -1;// x is less, so this is smaller
		}
		if (this.getX() > o.getX()) {
			return 1;
		}

		// x not less than or greater than, must be equal.
		if (this.getY() < o.getY()) {
			return -1;// x is equal but y is less, so this is smaller
		}
		if (this.getY() > o.getY()) {
			return 1;// x's are equal but this y is more, so this is larger
		}

		// y not less than or greater than, must be equal
		if (this.getLevel() < o.getLevel()) {
			return -1;
		}
		if (this.getLevel() > o.getLevel()) {
			return 1;
		}
		return 0;// x, y and lvl are equal, so they are equal
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node)) {
			return super.equals(obj);
		}
		return this.getX() == ((Node) obj).getX()
			&& this.getY() == ((Node) obj).getY()
			&& this.getLevel() == ((Node) obj).getLevel();
	}

	/**
	 * Returns the level of the point. This will be between {@value #MIN_LEVEL}
	 * and {@value #MAX_LEVEL}, inclusive. It will be positive, since negative
	 * levels are not supported.
	 *
	 * @return The level of the point as an integer.
	 */
	public int getLevel() {
		return this.lValue;
	}

	/**
	 * Returns the X value of the point. This will be between
	 * {@value #MIN_VALUE} and {@link #MAX_VALUE}, inclusive.
	 *
	 * @return The x value as a signed int.
	 */
	public int getX() {
		// decode the first 14 bits, or X position
		return this.xValue;
	}

	/**
	 * Returns the Y value of the point. This will be between
	 * {@value #MIN_VALUE} and {@link #MAX_VALUE}, inclusive.
	 *
	 * @return The y value as a signed int.
	 */
	public int getY() {
		return this.yValue;
	}

	/**
	 * Initializes members to default values. This exists so that it can be
	 * overridden in subclasses (namely, the synchronized wrapper class) so that
	 * variables can be ensured to exist before method calls.
	 */
	protected void init() {
		this.xValue = 0;
		this.yValue = 0;
		this.lValue = 0;
	}

	/**
	 * Sets the level of this point to the given number. Note that negative
	 * values are not supported.
	 *
	 * @param level the level to use.
	 * @throws IllegalArgumentException If the level is below
	 *             {@value #MIN_LEVEL} or above {@value #MAX_LEVEL}.
	 */
	public void setLevel(final int level) {
		if (level < Node.MIN_LEVEL) {
			throw new IllegalArgumentException(
				"level value is below the minimum allowed value");
		}
		if (level > Node.MAX_LEVEL) {
			throw new IllegalArgumentException(
				"level value is above the maximum allowed value");
		}

		if (level > Short.MAX_VALUE) {
			this.lValue = Short.MAX_VALUE;
		}
		else if (level < Short.MIN_VALUE) {
			this.lValue = Short.MIN_VALUE;
		}
		else {
			this.lValue = (short) level;
		}
	}

	/**
	 * Sets the X value of the point.
	 *
	 * @param x The new x value.
	 * @throws IllegalArgumentException if the passed value is below
	 *             {@value #MIN_VALUE} or above {@value #MAX_VALUE}.
	 */
	public void setX(final int x) {
		if (x < Node.MIN_VALUE) {
			throw new IllegalArgumentException(
				"X value is below the minimum allowed value");
		}
		if (x > Node.MAX_VALUE) {
			throw new IllegalArgumentException(
				"X value is above the maximum allowed value");
		}

		this.xValue = x;
	}

	/**
	 * Sets the Y value of the point.
	 *
	 * @param y The new y value.
	 * @throws IllegalArgumentException if the passed value is below
	 *             {@value #MIN_VALUE} or above {@value #MAX_VALUE}.
	 */
	public void setY(final int y) {
		if (y < Node.MIN_VALUE) {
			throw new IllegalArgumentException(
				"Y value is below the minimum allowed value");
		}
		if (y > Node.MAX_VALUE) {
			throw new IllegalArgumentException(
				"Y value is above the maximum allowed value");
		}
		this.yValue = y;
	}
}
