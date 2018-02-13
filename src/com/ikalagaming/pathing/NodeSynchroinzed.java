package com.ikalagaming.pathing;

import java.util.concurrent.locks.ReentrantLock;

/**
 * A synchronized version of the point class that ensures thread safety.
 *
 * @author Ches Burks
 *
 */
class NodeSynchroinzed extends Node {
	/**
	 * Generated Serial Version UID.
	 */
	private static final long serialVersionUID = -2442550696153326316L;

	private ReentrantLock dataLock;
	private Node child;

	/**
	 * Creates a synchronized node.
	 */
	public NodeSynchroinzed() {}

	/**
	 * For serialization, initialize components so they exist before method
	 * calls in the constructor. Called at the beginning of the
	 * {@link Node#Node(int, int, int)} constructor.
	 */
	@Override
	protected void init() {
		this.dataLock = new ReentrantLock();
		this.child = new Node();
	}

	/**
	 * Passed in a synchronized point to control. This should really be the only
	 * reference that exists, so doing {@code PointSynchronized(new Point(...)}
	 * is preferred to ensure that the point can be truly synchronized.
	 *
	 * @param p The point to synchronize.
	 */
	public NodeSynchroinzed(Node p) {
		this.dataLock = new ReentrantLock();
		this.child = p;
	}

	/**
	 * Ensures that the reentrant lock exists, and creates it if it does not.
	 */
	private void checkLock() {
		if (this.dataLock == null) {
			this.dataLock = new ReentrantLock();
		}
	}

	@Override
	public int compareTo(Node o) {
		this.checkLock();
		this.dataLock.lock();
		try {
			return this.child.compareTo(o);
		}
		finally {
			this.dataLock.unlock();
		}
	}

	@Override
	public boolean equals(Object obj) {
		this.checkLock();
		this.dataLock.lock();
		try {
			return this.child.equals(obj);
		}
		finally {
			this.dataLock.unlock();
		}
	}

	@Override
	public int getLevel() {
		this.checkLock();
		this.dataLock.lock();
		try {
			return this.child.getLevel();
		}
		finally {
			this.dataLock.unlock();
		}
	}

	@Override
	public int getX() {
		this.checkLock();
		this.dataLock.lock();
		try {
			return this.child.getX();
		}
		finally {
			this.dataLock.unlock();
		}
	}

	@Override
	public int getY() {
		this.checkLock();
		this.dataLock.lock();
		try {
			return this.child.getY();
		}
		finally {
			this.dataLock.unlock();
		}
	}

	@Override
	public int hashCode() {
		this.checkLock();
		this.dataLock.lock();
		try {
			return this.child.hashCode();
		}
		finally {
			this.dataLock.unlock();
		}
	}

	@Override
	public void setLevel(int level) {
		this.checkLock();
		this.dataLock.lock();
		try {
			this.child.setLevel(level);
		}
		finally {
			this.dataLock.unlock();
		}
	}

	@Override
	public void setX(int x) {
		this.checkLock();
		this.dataLock.lock();
		try {
			this.child.setX(x);
		}
		finally {
			this.dataLock.unlock();
		}
	}

	@Override
	public void setY(int y) {
		this.checkLock();
		this.dataLock.lock();
		try {
			this.child.setY(y);
		}
		finally {
			this.dataLock.unlock();
		}
	}

}
