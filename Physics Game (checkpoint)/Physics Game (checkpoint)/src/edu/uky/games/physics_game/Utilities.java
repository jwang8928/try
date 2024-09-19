package edu.uky.games.physics_game;

import edu.uky.games.physics_game.physics.RigidBody;

/**
 * A suite of helpful utility functions.
 * 
 * @author Stephen G. Ware
 */
public class Utilities {

	/**
	 * Divides two numbers, but returns 0 if the denominator is 0 in order to
	 * avoid divide by zero errors.
	 * 
	 * @param numerator the number to be divided
	 * @param denominator the number to divide by
	 * @return the quotient
	 */
	public static final double safeDivide(double numerator, double denominator) {
		if(denominator == 0)
			return 0;
		else
			return numerator / denominator;
	}
	
	/**
	 * Returns the amount by which the axis-aligned bounding boxes of two
	 * bodies overlap on the X axis.
	 * 
	 * @param b1 the first body
	 * @param b2 the second body
	 * @return the X axis overlap
	 */
	public static final double getXOverlap(RigidBody b1, RigidBody b2) {
		double widths = b1.getWidth() + b2.getWidth();
		double left = Math.min(b1.position.getX(), b2.position.getX());
		double right = Math.max(b1.position.getX() + b1.getWidth(), b2.position.getX() + b2.getWidth());
		return widths - (right - left);
	}
	
	/**
	 * Returns the amount by which the axis-aligned bounding boxes of two
	 * bodies overlap on the Y axis.
	 * 
	 * @param b1 the first body
	 * @param b2 the second body
	 * @return the Y axis overlap
	 */
	public static final double getYOverlap(RigidBody b1, RigidBody b2) {
		double heights = b1.getHeight() + b2.getHeight();
		double top = Math.min(b1.position.getY(), b2.position.getY());
		double bottom = Math.max(b1.position.getY() + b1.getHeight(), b2.position.getY() + b2.getHeight());
		return heights - (bottom - top);
	}
}
