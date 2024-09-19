package edu.uky.games.physics_game.physics;

import edu.uky.games.physics_game.Utilities;

/**
 * A vector in a 2 dimensional space is composed of two numbers: an X value and
 * a Y value.  A vector can express the slope of a line (direction) as well as
 * the length of a line segment (magnitude).  Because it is simply a pair of
 * numbers, it can also represent a point in 2D space.  In this game, this
 * class is used interchangeably to represent vectors and points.
 * 
 * @author Stephen G. Ware
 */
public class Vector {

	/** The X axis component */
	private double x;
	
	/** The Y axis component */
	private double y;
	
	/**
	 * Constructs a new vector with the given values.
	 * 
	 * @param x the X axis component
	 * @param y the Y axis component
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructs a new vector with values (0, 0).
	 */
	public Vector() {
		this(0, 0);
	}
	
	/**
	 * Converts this object to a string of the form "(X, Y)".
	 */
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * Gets the X axis component of this vector.
	 * 
	 * @return the X axis component
	 */
	public synchronized double getX() {
		return x;
	}
	
	/**
	 * Gets the Y axis component of this vector.
	 * 
	 * @return the Y axis component
	 */
	public synchronized double getY() {
		return y;
	}
	
	/**
	 * Sets the X and Y components of this vector.
	 * 
	 * @param x the X component
	 * @param y the Y component
	 */
	public synchronized void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Modifies this vector by adding another vector to it.
	 * 
	 * @param other the vector to add to this vector
	 */
	public void add(Vector other) {
		set(x + other.x, y + other.y);
	}
	
	/**
	 * Modifies this vector by subtracting another vector from it.
	 * 
	 * @param other the vector to subtract from this vector
	 */
	public void subtract(Vector other) {
		set(x - other.x, y - other.y);
	}
	
	/**
	 * Modifies this vector by multiplying both components by a scalar value.
	 * 
	 * @param scalar the scalar multiple
	 */
	public void multiply(double scalar) {
		set(x * scalar, y * scalar);
	}
	
	/**
	 * Modifies this vector by dividing both components by a scalar value.
	 * Uses {@link Utilities#safeDivide(double, double)} to ensure there are no
	 * divide by zero errors.
	 * 
	 * @param scalar the scalar divisor
	 */
	public void divide(double scalar) {
		set(Utilities.safeDivide(x, scalar), Utilities.safeDivide(y, scalar));
	}
	
	/**
	 * Scale this vector so that it's x component is equal to some given
	 * number.  The sign of the argument is ignored.  In other words, this
	 * method will not change the direction of a vector, only its scale.
	 * 
	 * @param x the number that this vector's x component will be after it
	 * has been scaled (sign ignored)
	 */
	public void scaleXto(double x) {
		multiply(Utilities.safeDivide(x, Math.abs(this.x)));
	}
	
	/**
	 * Scale this vector so that it's y component is equal to some given
	 * number.  The sign of the argument is ignored.  In other words, this
	 * method will not change the direction of a vector, only its scale.
	 * 
	 * @param y the number that this vector's y component will be after it
	 * has been scaled (sign ignored)
	 */
	public void scaleYto(double y) {
		multiply(Utilities.safeDivide(y, Math.abs(this.y)));
	}
	
	/**
	 * Scale this vector so that it's magnitude is 1.
	 */
	public void normalize() {
		double length = Math.sqrt(x * x + y * y);
		divide(length);
	}
	
	/**
	 * Returns a new vector that is the sum of the given vectors.
	 * 
	 * @param v1 the first vector
	 * @param v2 the second vector
	 * @return the sum
	 */
	public static Vector add(Vector v1, Vector v2) {
		return new Vector(v1.x + v2.x, v1.y + v2.y);
	}
	
	/**
	 * Returns a new vector that is the difference of the given vectors.
	 * 
	 * @param v1 the first vector
	 * @param v2 the second vector
	 * @return the difference
	 */
	public static Vector subtract(Vector v1, Vector v2) {
		return new Vector(v1.x - v2.x, v1.y - v2.y);
	}
	
	/**
	 * Returns a new vector that is scaled by a given multiple.
	 * 
	 * @param v1 the vector
	 * @param scalar the scalar multiple
	 * @return the product
	 */
	public static Vector multiply(Vector v1, double scalar) {
		return new Vector(v1.x * scalar, v1.y * scalar);
	}
	
	/**
	 * Returns a new vector that is scaled by a given divisor.
	 * Uses {@link Utilities#safeDivide(double, double)} to ensure there are no
	 * divide by zero errors.
	 * 
	 * @param v1 the vector
	 * @param scalar the scalar divisor
	 * @return the quotient
	 */
	public static Vector divide(Vector v1, double scalar) {
		return new Vector(Utilities.safeDivide(v1.x, scalar), Utilities.safeDivide(v1.y, scalar));
	}
	
	/**
	 * Returns the dot product of two vectors.
	 * 
	 * @param v1 the first vector
	 * @param v2 the second vector
	 * @return the dot product
	 */
	public static double dotProduct(Vector v1, Vector v2) {
		return (v1.x * v2.x) + (v1.y * v2.y);
	}
	
	/**
	 * Returns the distance between two points.
	 * 
	 * @param v1 the first point
	 * @param v2 the second point
	 * @return the distance between them
	 */
	public static double distance(Vector v1, Vector v2) {
		double dx = v2.x - v1.x;
		double dy = v2.y - v1.y;
		return Math.sqrt((dx * dx) + (dy * dy));
	}
}
