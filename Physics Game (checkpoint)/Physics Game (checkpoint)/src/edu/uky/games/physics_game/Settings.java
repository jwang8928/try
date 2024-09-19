package edu.uky.games.physics_game;

import edu.uky.games.physics_game.physics.Vector;

/**
 * Settings for this project.
 * 
 * @author Stephen G. Ware
 */
public class Settings {

	/** The name displayed on the game window */
	public static final String NAME = "Physics Game";
	
	/** The width of the {@link GamePanel} in pixels */
	public static final int WINDOW_WIDTH = 800;
	
	/** The height of the {@link GamePanel} in pixels */
	public static final int WINDOW_HEIGHT = 500;
	
	/** The number of milliseconds to wait between each frame (16 milliseconds ~= 60 frames per second) */
	public static final int CLOCK_SPEED = 16;
	
	/** The radius of the ball */
	public static final double BALL_RADIUS = 30;
	
	/**
	 * Earth's gravity accelerates objects at at rate of 9.8 meters per second squared.
	 * Assumes 1 pixel = 1 meter and that the game is rendering 60 frames per second.
	 */
	public static final Vector GRAVITATIONAL_ACCELERATION = new Vector(0, 9.8 / 60);
	
	/**
	 * Restitution is the amount of energy retained when two object collide.
	 * 1 means objects collide elastically (lose no energy) and 0 means objects
	 * collide inelastically (lose all energy and thus stop).  The closer this
	 * number is to 1 to bouncier objects will be.
	 */
	public static final double RESTITUTION = 0.75;
	
	/**
	 * Scale applied to the velocity that is added to the ball when it is
	 * clicked.  Low values mean that clicks will accelerate the ball less,
	 * and high values mean that clicks will accelerate the ball more.
	 */
	public static final double CLICK_POWER = 0.75;
}
