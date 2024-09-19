package edu.uky.games.physics_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import edu.uky.games.physics_game.physics.*;

/**
 * A {@link JPanel} which displays all the objects in the game and collects
 * user input.
 * 
 * @author Stephen G. Ware
 */
public class GamePanel extends JPanel {

	/** The number of frames that have been rendered */
	public static long frames = 0;
	
	/**
	 * The number of collision tests processed (i.e. number of calls to
	 * {@link RigidBody#overlaps(RigidBody)})
	 */
	public static long collisionTests = 0;
	
	/** Version 1 */
	private static final long serialVersionUID = 1L;
	
	/** The ball which bounces around the screen */
	private final Circle ball;
	
	/** An array of all bodies being simulated */
	private final RigidBody[] bodies;
	
	/** The physics engine which will simulate the bodies */
	private final PhysicsEngine physicsEngine = new PhysicsEngine();

	/**
	 * Constructs a game with 1 ball, a floor, ceiling, left and right walls,
	 * and a box with no top in the center of the screen.
	 */
	public GamePanel() {
		// Use double buffering.
		super(true);
		// Window size
		setPreferredSize(new Dimension(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		// Initialize a list of all rigid bodies in this simulation.
		ArrayList<RigidBody> bodies = new ArrayList<>();
		// Ball
		ball = new Circle(
				new Vector(20, 250),
				Settings.BALL_RADIUS);
		bodies.add(ball);
		// The ball starts with a small rightward velocity.
		ball.velocity.set(3, 0);
		// Floor
		bodies.add(new Rectangle(
				new Vector(0, Settings.WINDOW_HEIGHT - 10),
				Settings.WINDOW_WIDTH,
				10,
				RigidBody.INFINITE_MASS));
		// Ceiling
		bodies.add(new Rectangle(
				new Vector(0, 0),
				Settings.WINDOW_WIDTH,
				10,
				RigidBody.INFINITE_MASS));
		// Left wall
		bodies.add(new Rectangle(
				new Vector(0, 10),
				10,
				Settings.WINDOW_HEIGHT - 20,
				RigidBody.INFINITE_MASS));
		// Right wall
		bodies.add(new Rectangle(
				new Vector(Settings.WINDOW_WIDTH - 10, 10),
				10,
				Settings.WINDOW_HEIGHT - 20,
				RigidBody.INFINITE_MASS));
		// Basket
		int bw = (int) Settings.BALL_RADIUS * 3;
		int bx = Settings.WINDOW_WIDTH / 2 - bw / 2;
		int by = Settings.WINDOW_HEIGHT / 2 - bw / 2;
		// Left wall of basket
		bodies.add(new Rectangle(
				new Vector(bx, by),
				10,
				bw,
				RigidBody.INFINITE_MASS));
		// Bottom of basket
		bodies.add(new Rectangle(
				new Vector(bx, by + bw),
				bw + 10,
				10,
				RigidBody.INFINITE_MASS));
		// Right wall of basket
		bodies.add(new Rectangle(
				new Vector(bx + bw, by),
				10,
				bw,
				RigidBody.INFINITE_MASS));
		/*
		// Adds a second ball to the simulation.
		bodies.add(new Circle(new Vector(500, 250), Settings.BALL_RADIUS));
		*/
		// Initialize the array of bodies.
		this.bodies = bodies.toArray(new RigidBody[bodies.size()]);
		// Initialize the physics engine.
		physicsEngine.initialize(this.bodies);
		// Register mouse listener.
		addMouseListener(controller);
		// Start the clock.
		new Clock().start();
	}
	
	/**
	 * Draws all the bodies being simulated at their current locations as
	 * well as some statistics on collision detection.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for(RigidBody body : bodies)
			body.draw(g2d);
		g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(18f));
		g.drawString(collisionTests + " narrow phase collision tests", 15, 30);
		g.drawString(frames + " frames rendered", 15, 45);
		double ctpf = ((double) collisionTests) / ((double) frames);
		g.drawString(String.format("about %.4f collision tests per frame", ctpf), 15, 60);
	}
	
	/** Handles user input (mouse clicks) */
	private final MouseAdapter controller = new MouseAdapter() {
		
		/**
		 * Called every time the user clicks on the screen.
		 */
		@Override
		public void mousePressed(MouseEvent event) {
			// ===== STEP 6 =====
			// First, check if the user clicked on the ball.
			// Use RigidBody#contains and MouseEvent#getX and MouseEvent#getY.
			// If the user did not click on the ball, ignore the click.
			if(ball.contains(event.getX(), event.getY())) {
				// Initialize this vector to be the center of the ball.
				// Use Circle#getCenter for this.
				Vector acceleration = ball.getCenter();
				// Now convert that point to a vector by subtracting the
				// location of the mouse click.
				acceleration.subtract(new Vector(event.getX(), event.getY()));
				// Scale that vector by Settings#CLICK_POWER.
				acceleration.multiply(Settings.CLICK_POWER);
				// Apply this new acceleration to the ball by adding it to
				// the ball's velocity.
				ball.velocity.add(acceleration);
			}
		}
	};
	
	/**
	 * The clock thread calls the physics engine's update methods once per
	 * frame.
	 * 
	 * @author Stephen G. Ware
	 */
	private final class Clock extends Thread {
		
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(Settings.CLOCK_SPEED);
				}
				catch(InterruptedException ex) {
					// Do nothing.
				}
				frames++;
				physicsEngine.simulate(bodies);
				repaint();
			}
		}
	}
}
