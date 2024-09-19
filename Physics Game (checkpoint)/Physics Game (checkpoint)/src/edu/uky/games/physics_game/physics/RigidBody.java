package edu.uky.games.physics_game.physics;

import java.awt.Color;
import java.awt.Graphics2D;

import edu.uky.games.physics_game.collision.ListNode;

/**
 * A rigid body is a hard physical object that cannot be deformed (e.g. bent).
 * In most 2D spaces, rigid bodies have three degrees of freedom: the X axis,
 * the Y axis, and rotation around the Z axis.  However, in this simple game,
 * we ignore rotation.
 * 
 * @author Stephen G. Ware
 */
public abstract class RigidBody {
	
	/** Bodies with infinite mass do not move */
	public static final double INFINITE_MASS = 0;
	
	/** The ID number to assign to the next rigid body that is created */
	private static int nextID = 0;

	/** A unique ID number to identify this body */
	public final int id;
	
	/** The location of the top left corner of this body's axis-aligned bounding box */
	public final Vector position;
	
	/** The velocity at which this body is traveling through space per frame */
	public final Vector velocity;
	
	/** The weight of this object */
	public final double mass;
	
	/** The color this object will be drawn in */
	public Color color = Color.BLACK;
	
	/** The top edge of this body's axis-aligned bounding box (for use in collision detection) */
	public final ListNode top;
	
	/** The bottom edge of this body's axis-aligned bounding box (for use in collision detection) */
	public final ListNode bottom;
	
	/** The left edge of this body's axis-aligned bounding box (for use in collision detection) */
	public final ListNode left;
	
	/** The right edge of this body's axis-aligned bounding box (for use in collision detection) */
	public final ListNode right;
	
	/**
	 * Constructs a new rigid body at a given location with a given mass.
	 * All rigid bodies begin still (i.e. no velocity).
	 * 
	 * @param position the position of the body
	 * @param mass the mass of the body
	 */
	public RigidBody(Vector position, double mass) {
		this.id = nextID++;
		this.position = position;
		this.velocity = new Vector();
		this.mass = mass;
		this.top = new ListNode.Top(this);
		this.bottom = new ListNode.Bottom(this);
		this.left = new ListNode.Left(this);
		this.right = new ListNode.Right(this);
	}

	/**
	 * Returns a string representation of this body, which includes the name of
	 * its shape, its ID number, its position, and its velocity.
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + " id=" + id + " position=" + position + " velocity=" + velocity;
	}
	
	/**
	 * Returns the width of this body's axis-aligned bounding box.
	 * 
	 * @return the width
	 */
	public abstract double getWidth();
	
	/**
	 * Returns the height of this body's axis-aligned bounding box.
	 * 
	 * @return the height
	 */
	public abstract double getHeight();
	
	/**
	 * Tests whether or not a given point is inside this body.
	 * 
	 * @param x the X axis coordinate of the point
	 * @param y the Y axis coordinate of the point
	 * @return true if the point is inside the body, false otherwise
	 */
	public abstract boolean contains(double x, double y);
	
	/**
	 * Tests whether or not this body overlaps (i.e. penetrates) another body.
	 * 
	 * @param other the other body to test
	 * @return true of the two bodies overlap, false otherwise
	 */
	public abstract boolean overlaps(RigidBody other);
	
	/**
	 * Returns a normal vector which is perpendicular to the plane between this
	 * body and the given body.  The normal vector returned will be a unit
	 * vector and it will point toward this body.
	 * 
	 * @param other the other body
	 * @return the normal unit vector
	 */
	public abstract Vector getNormal(RigidBody other);
	
	/**
	 * Draws this body to the screen.
	 * 
	 * @param g the graphical context on which to draw
	 */
	public abstract void draw(Graphics2D g);
	
	/**
	 * Rounds a double to an int.
	 * 
	 * @param d the double to round
	 * @return the value, rounded to the nearest int
	 */
	protected static final int round(double d) {
		return (int) Math.round(d);
	}
}
