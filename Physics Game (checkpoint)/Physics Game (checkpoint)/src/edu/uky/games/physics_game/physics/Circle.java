package edu.uky.games.physics_game.physics;

import java.awt.Graphics2D;

import edu.uky.games.physics_game.GamePanel;

/**
 * A rigid circular or ball shape.
 * 
 * @author Stephen G. Ware
 */
public class Circle extends RigidBody {

	/** The radius of the circle */
	public final double radius;
	
	/** The diameter of the circle */
	public final double diameter;
	
	/**
	 * Constructs a circle at the given location with the given radius.
	 * 
	 * @param location the location of the top left corder of the axis-aligned
	 * bounding box of this circle
	 * @param radius the radius of the circle
	 */
	public Circle(Vector location, double radius) {
		super(location, Math.PI * radius * radius);
		this.radius = radius;
		this.diameter = radius * 2;
	}
	
	/**
	 * Returns the location of the center of the circle.
	 * 
	 * @return the center
	 */
	public Vector getCenter() {
		return new Vector(position.getX() + radius, position.getY() + radius);
	}
	
	@Override
	public double getWidth() {
		return diameter;
	}

	@Override
	public double getHeight() {
		return diameter;
	}
	
	@Override
	public boolean contains(double x, double y) {
		return Vector.distance(getCenter(), new Vector(x, y)) <= radius;
	}
	
	@Override
	public boolean overlaps(RigidBody other) {
		GamePanel.collisionTests++;
		if(other instanceof Circle) {
			Circle otherCircle = (Circle) other;
			return Vector.distance(getCenter(), otherCircle.getCenter()) <= radius + otherCircle.radius;
		}
		else if(other instanceof Rectangle) {
			Rectangle otherRectangle = (Rectangle) other;
			// Find the point on the rectangle closest to the center of the circle.
			Vector center = getCenter();
			double closestX = clamp(otherRectangle.position.getX(), center.getX(), otherRectangle.position.getX() + otherRectangle.width);
			double closestY = clamp(otherRectangle.position.getY(), center.getY(), otherRectangle.position.getY() + otherRectangle.height);
			Vector closest = new Vector(closestX, closestY);
			// If that closest point is less than the radius' distance away, the circle and rectangle intersect.
			return Vector.distance(center, closest) <= radius;
		}
		else
			throw new IllegalArgumentException("The shape \"" + other.getClass().getSimpleName() + "\" is not supported.");
	}
	
	@Override
	public Vector getNormal(RigidBody other) {
		if(other instanceof Rectangle)
			return other.getNormal(this);
		Vector normal = Vector.subtract(getCenter(), ((Circle) other).getCenter());
		normal.normalize();
		return normal;
	}
	
	/**
	 * Returns a value between a lower and upper bound.  If the value is below
	 * the lower bound, the lower bound is returned.  If the value is above
	 * the upper bound, the upper bound is returned.  Otherwise, the value 
	 * itself is returned.
	 * 
	 * @param min the lower bound
	 * @param value the value
	 * @param max the upper bound
	 * @return the value within the given bounds
	 */
	private final double clamp(double min, double value, double max) {
		if(value < min)
			return min;
		else if(value > max)
			return max;
		else
			return value;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(round(position.getX()), round(position.getY()), round(diameter), round(diameter));
	}
}
