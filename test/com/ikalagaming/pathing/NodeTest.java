package com.ikalagaming.pathing;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Tests the point class to ensure it works properly.
 *
 * @author Ches Burks
 *
 */
public class NodeTest {
	/**
	 * Tests the comparison of points for ordering.
	 */
	@Test
	public void testCompareTo() {
		Node c = new Node(2, 2);
		Node right = new Node(3, 2);
		Node left = new Node(1, 2);
		Node above = new Node(2, 1);
		Node below = new Node(2, 3);
		Node same = new Node(2, 2);
		Node upleft = new Node(1, 1);
		Node downright = new Node(3, 3);
		Assert.assertEquals(0, c.compareTo(same));
		Assert.assertEquals(-1, c.compareTo(right));
		Assert.assertEquals(1, c.compareTo(left));
		Assert.assertEquals(1, c.compareTo(above));
		Assert.assertEquals(-1, c.compareTo(below));
		Assert.assertEquals(1, c.compareTo(upleft));
		Assert.assertEquals(-1, c.compareTo(downright));
	}

	/**
	 * Tests that getLevel() works as intended.
	 */
	@Test
	public void testGetLevel() {
		final int lVal = 4;
		final int newVal = 95;
		Node p = new Node(3, 4, lVal);
		Assert.assertEquals(lVal, p.getLevel());
		p.setLevel(newVal);
		Assert.assertNotEquals(lVal, p.getLevel());
		Assert.assertNotEquals(newVal, lVal);// make sure the test is valid
		Assert.assertEquals(newVal, p.getLevel());

		// test extremes
		p.setLevel(Node.MIN_LEVEL);
		Assert.assertEquals(Node.MIN_LEVEL, p.getLevel());
		p.setLevel(Node.MAX_LEVEL);
		Assert.assertEquals(Node.MAX_LEVEL, p.getLevel());
	}

	/**
	 * Tests that the getX() method works as intended.
	 */
	@Test
	public void testGetX() {
		final int xVal = 4;
		final int newVal = 95;

		Node p = new Node(xVal, 5);
		Assert.assertEquals(xVal, p.getX());
		p.setX(newVal);
		Assert.assertNotEquals(xVal, p.getX());
		Assert.assertNotEquals(newVal, xVal);// make sure the test is valid
		Assert.assertEquals(newVal, p.getX());

		// test extremes
		p.setX(Node.MIN_VALUE);
		Assert.assertEquals(Node.MIN_VALUE, p.getX());
		p.setX(Node.MAX_VALUE);
		Assert.assertEquals(Node.MAX_VALUE, p.getX());
	}

	/**
	 * Tests that getY() works as intended.
	 */
	@Test
	public void testGetY() {
		final int yVal = 4;
		final int newVal = 95;
		Node p = new Node(3, yVal);
		Assert.assertEquals(yVal, p.getY());
		p.setY(newVal);
		Assert.assertNotEquals(yVal, p.getY());
		Assert.assertNotEquals(newVal, yVal);// make sure the test is valid
		Assert.assertEquals(newVal, p.getY());

		// test extremes
		p.setY(Node.MIN_VALUE);
		Assert.assertEquals(Node.MIN_VALUE, p.getY());
		p.setY(Node.MAX_VALUE);
		Assert.assertEquals(Node.MAX_VALUE, p.getY());

	}

	/**
	 * Tests the point constructors for errors and expected values. If the
	 * getters and setters do not work this also might fail.
	 */
	@Test
	public void testPoint() {
		try {
			Node p1 = new Node();

			// test default values
			Assert.assertEquals("Default x value", 0, p1.getX());
			Assert.assertEquals("Default y value", 0, p1.getY());
			Assert.assertEquals("Default level value", 0, p1.getLevel());

		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Tests the 2 arg constructor works as intended.
	 */
	@Test
	public void testPointIntInt() {
		final int x = 4;
		final int y = 7;
		try {
			Node p = new Node(x, y);
			Assert
				.assertEquals("Inconsistent x value Point(x, y)", x, p.getX());
			Assert
				.assertEquals("Inconsistent y value Point(x, y)", y, p.getY());
			Assert.assertEquals("Default level value", 0, p.getLevel());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Tests the 3 arg constructor works as intended.
	 */
	@Test
	public void testPointIntIntInt() {
		final int x = 5;
		final int y = 6;
		final int l = 3;
		try {
			Node p = new Node(x, y, l);
			Assert.assertEquals("Inconsistent x value Point(x, y, l)", x,
				p.getX());
			Assert.assertEquals("Inconsistent y value Point(x, y, l)", y,
				p.getY());
			Assert.assertEquals("Inconsistent level value Point(x, y, l)", l,
				p.getLevel());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Tests that points can be serialized to a file.
	 */
	@Test
	public void testSerialization() {
		final String filename = "point.ser";

		Node p = new Node(1, 2, 3);
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(p);
			out.close();
			fileOut.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			Assert.fail("Failed to output Point to file.");
		}

		Node deserial = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			deserial = (Node) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
			Assert.fail("Failed reading in Point from file.");
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
			Assert.fail("Employee class not found");
		}
		try {
			File toRemove = new File(filename);
			if (toRemove.exists() && toRemove.isFile()) {
				toRemove.delete();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(deserial);
		Assert.assertEquals(p.getX(), deserial.getX());
		Assert.assertEquals(p.getY(), deserial.getY());
		Assert.assertEquals(p.getLevel(), deserial.getLevel());
	}

	/**
	 * Ensures the level sets correctly and checks for invalid input.
	 */
	@Test
	public void testSetLevel() {
		final int lVal = 4;
		final int newVal = 95;
		Node p = new Node(3, 5, lVal);
		Assert.assertEquals(lVal, p.getLevel());
		p.setLevel(newVal);
		Assert.assertNotEquals(lVal, p.getLevel());
		Assert.assertNotEquals(newVal, lVal);// make sure the test is valid
		Assert.assertEquals(newVal, p.getLevel());

		// test extremes
		p.setLevel(Node.MIN_LEVEL);
		Assert.assertEquals(Node.MIN_LEVEL, p.getLevel());
		p.setLevel(Node.MAX_LEVEL);
		Assert.assertEquals(Node.MAX_LEVEL, p.getLevel());

		boolean err = false;
		try {
			p.setLevel(Node.MIN_LEVEL - 1);
		}
		catch (IllegalArgumentException e) {
			err = true;
		}
		Assert.assertEquals("Level below min value not throwing error", true,
			err);

		err = false;
		try {
			p.setLevel(Node.MAX_LEVEL + 1);
		}
		catch (IllegalArgumentException e) {
			err = true;
		}
		Assert.assertEquals("Level above max value not throwing error", true,
			err);
	}

	/**
	 * Ensures the setX method works and checks values properly.
	 */
	@Test
	public void testSetX() {
		final int xVal = 4;
		final int newVal = 95;

		Node p = new Node(xVal, 5);
		Assert.assertEquals(xVal, p.getX());
		p.setX(newVal);
		Assert.assertNotEquals(xVal, p.getX());
		Assert.assertNotEquals(newVal, xVal);// make sure the test is valid
		Assert.assertEquals(newVal, p.getX());

		// test extremes
		p.setX(Node.MIN_VALUE);
		Assert.assertEquals(Node.MIN_VALUE, p.getX());
		p.setX(Node.MAX_VALUE);
		Assert.assertEquals(Node.MAX_VALUE, p.getX());

		boolean err = false;
		try {
			p.setX(Node.MIN_VALUE - 1);
		}
		catch (IllegalArgumentException e) {
			err = true;
		}
		Assert.assertEquals("X value below min value not throwing error", true,
			err);

		err = false;
		try {
			p.setX(Node.MAX_VALUE + 1);
		}
		catch (IllegalArgumentException e) {
			err = true;
		}
		Assert.assertEquals("X value above max value not throwing error", true,
			err);
	}

	/**
	 * Tests that setting y works correctly and checks input.
	 */
	@Test
	public void testSetY() {
		final int yVal = 4;
		final int newVal = 95;
		Node p = new Node(3, yVal);
		Assert.assertEquals(yVal, p.getY());
		p.setY(newVal);
		Assert.assertNotEquals(yVal, p.getY());
		Assert.assertNotEquals(newVal, yVal);// make sure the test is valid
		Assert.assertEquals(newVal, p.getY());

		// test extremes
		p.setY(Node.MIN_VALUE);
		Assert.assertEquals(Node.MIN_VALUE, p.getY());
		p.setY(Node.MAX_VALUE);
		Assert.assertEquals(Node.MAX_VALUE, p.getY());

		boolean err = false;
		try {
			p.setY(Node.MIN_VALUE - 1);
		}
		catch (IllegalArgumentException e) {
			err = true;
		}
		Assert.assertEquals("Y value below min value not throwing error", true,
			err);

		err = false;
		try {
			p.setY(Node.MAX_VALUE + 1);
		}
		catch (IllegalArgumentException e) {
			err = true;
		}
		Assert.assertEquals("Y value above max value not throwing error", true,
			err);
	}

	/**
	 * Test the creation of synchronized points.
	 */
	@Test
	public void testSynchronizedPoint() {
		try {
			Node p1 = Node.synchronizedPoint(new Node());
			Node p2 = Node.synchronizedPoint(new Node(1, 2));
			Node p3 = Node.synchronizedPoint(new Node(2, 3, 4));
			Node p = new Node();
			Node p4 = Node.synchronizedPoint(p);
			p1.setX(6);
			p2.setX(7);
			p3.setX(8);
			p4.setX(10);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Tests that synchronized points can be serialized to a file.
	 */
	@Test
	public void testSynchSerialization() {
		final String filename = "pointsynch.ser";

		Node synched = Node.synchronizedPoint(new Node(2, 3, 4));
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(synched);
			out.close();
			fileOut.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			Assert.fail("Failed to output Point to file.");
		}

		Node deserial = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			deserial = (Node) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException i) {
			i.printStackTrace();
			Assert.fail("Failed reading in Point from file.");
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
			Assert.fail("Employee class not found");
		}
		try {
			File toRemove = new File(filename);
			if (toRemove.exists() && toRemove.isFile()) {
				toRemove.delete();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(deserial);
		Assert.assertEquals(synched.getX(), deserial.getX());
		Assert.assertEquals(synched.getY(), deserial.getY());
		Assert.assertEquals(synched.getLevel(), deserial.getLevel());
	}

}
