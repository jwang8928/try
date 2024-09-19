package edu.uky.games.physics_game.physics;

import java.awt.Graphics2D;

import edu.uky.games.physics_game.GamePanel;
import edu.uky.games.physics_game.Utilities;

/**
 * A rigid rectangular or box shape.  Because rotation is ignored, this body
 * will always be axis-aligned.
 * 
 * @author Stephen G. Ware
 */
public class Rectangle extends RigidBody {

	/** The width of the rectangle */
	public final double width;
	
	/** The height of the rectangle */
	public final double height;
	
	/**
	 * Constructs a new rectangle at a given location with the given width,
	 * height, and mass.
	 * 
	 * @param location the location of the top left corner
	 * @param width the width
	 * @param height the height
	 * @param mass the mass
	 */
	public Rectangle(Vector location, double width, double height, double mass) {
		super(location, mass);
		this.width = width;
		this.height = height;
	}

	/**
	 * Constructs a rectangle with the default mass equal to its area.
	 * 
	 * @param location the location of the top left corner
	 * @param width the width
	 * @param height the height
	 */
	public Rectangle(Vector location, double width, double height) {
		this(location, width, height, width * height);
	}
	
	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}
	
	@Override
	public boolean contains(double x, double y) {
		return position.getX() <= x &&
				position.getY() + width - 1 >= x &&
				position.getX() <= y &&
				position.getY() + height - 1 >= y;
	}
	
	@Override
	public boolean overlaps(RigidBody other) {
		if(other instanceof Circle)
			return other.overlaps(this);
		GamePanel.collisionTests++;
		if(other instanceof Rectangle) {
			return Utilities.getXOverlap(this, other) > 0 &&
				Utilities.getYOverlap(this, other) > 0;
		}
		else
			throw new IllegalArgumentException("The shape \"" + other.getClass().getSimpleName() + "\" is not supported.");
	}
	
	/** A unit vector pointing upwards */
	private static final Vector UP = new Vector(0, -1);
	
	/** A unit vector pointing downwards */
	private static final Vector DOWN = new Vector(0, 1);
	
	/** A unit vector pointing left */
	private static final Vector LEFT = new Vector(-1, 0);
	
	/** A unit vector pointing right */
	private static final Vector RIGHT = new Vector(1, 0);

	@Override
	public Vector getNormal(RigidBody other) {
		double xOverlap = Utilities.getXOverlap(this, other);
		double yOverlap = Utilities.getYOverlap(this, other);
		if(xOverlap < yOverlap) {
			if(position.getX() < other.position.getX())
				return LEFT;
			else
				return RIGHT;
		}
		else {
			if(position.getY() < other.position.getY())
				return UP;
			else
				return DOWN;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(round(position.getX()), round(position.getY()), round(width), round(height));
	}
}
