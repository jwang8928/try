package edu.uky.games.physics_game.collision;

import edu.uky.games.physics_game.physics.RigidBody;

/**
 * Represents a single edge of one rigid body's axis-aligned bounding box
 * along one dimension.  Because this engine deals with 2D space, there are
 * found possible types of edges: top, bottom, left, and right.
 * 
 * @author Stephen G. Ware
 */
public abstract class ListNode {
	
	/** The type of edge that a node represents */
	public enum Type {
		TOP, BOTTOM, LEFT, RIGHT;
	}

	/** The body this edge belongs to */
	public final RigidBody body;
	
	/** The type of edge (top, bottom, left, or right) */
	public final Type type;
	
	/** The next node in the list after this node */
	ListNode next = null;
	
	/**
	 * Constructs a new list node for a given body and edge type.
	 * 
	 * @param body the rigid body that this edge belongs to
	 * @param type the type of edge
	 */
	protected ListNode(RigidBody body, Type type) {
		this.body = body;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "[" + body.id + " " + type + ": " + getValue() + "]";
	}
	
	/**
	 * Returns the current location of this node's edge on the appropriate
	 * axis.
	 * 
	 * @return the location of the edge
	 */
	public abstract double getValue();
	
	/**
	 * Returns the next node in the list.  If the list has been sorted since
	 * the bodies last moved, this method will return the node representing
	 * the edge with the next highest value.
	 * 
	 * @return the next node in the list, or null of this is the last node
	 */
	public ListNode getNext() {
		return next;
	}
	
	/**
	 * Represents the top edge of a body.
	 * 
	 * @author Stephen G. Ware
	 */
	public static final class Top extends ListNode {
		
		/**
		 * Constructs a node representing the top edge of the given body.
		 * 
		 * @param body the body
		 */
		public Top(RigidBody body) {
			super(body, Type.TOP);
		}

		/**
		 * Returns the location of the body's top edge on the Y axis.
		 */
		@Override
		public double getValue() {
			return body.position.getY();
		}
	}
	
	/**
	 * Represents the bottom edge of a body.
	 * 
	 * @author Stephen G. Ware
	 */
	public static final class Bottom extends ListNode {
		
		/**
		 * Constructs a node representing the bottom edge of the given body.
		 * 
		 * @param body the body
		 */
		public Bottom(RigidBody body) { 
			super(body, Type.BOTTOM);
		}

		/**
		 * Returns the location of the body's bottom edge on the Y axis.
		 */
		@Override
		public double getValue() {
			return body.position.getY() + body.getHeight() - 1;
		}
	}
	
	/**
	 * Represents the left edge of a body.
	 * 
	 * @author Stephen G. Ware
	 */
	public static final class Left extends ListNode {
		
		/**
		 * Constructs a node representing the left edge of the given body.
		 * 
		 * @param body the body
		 */
		public Left(RigidBody body) { 
			super(body, Type.LEFT);
		}

		/**
		 * Returns the location of the body's left edge on the X axis.
		 */
		@Override
		public double getValue() {
			return body.position.getX();
		}
	}
	
	/**
	 * Represents the right edge of a body.
	 * 
	 * @author Stephen G. Ware
	 */
	public static final class Right extends ListNode {
		
		/**
		 * Constructs a node representing the right edge of the given body.
		 * 
		 * @param body the body
		 */
		public Right(RigidBody body) { 
			super(body, Type.RIGHT);
		}

		/**
		 * Returns the location of the body's right edge on the X axis.
		 */
		@Override
		public double getValue() {
			return body.position.getX() + body.getWidth() - 1;
		}
	}
}
