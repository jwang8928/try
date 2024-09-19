package edu.uky.games.physics_game.physics;

import java.awt.Color;

import edu.uky.games.physics_game.*;
import edu.uky.games.physics_game.collision.*;
import edu.uky.games.physics_game.collision.ListNode.Type;

/**
 * A game physics engine simulates a group of bodies to provide the illusion of
 * real physics.  This very simple physics engine has two main functions: to
 * move bodies around according to their velocity and to simulate what happens
 * when two bodies collide, or bump into each other.
 * 
 * @author Your Name
 */
public class PhysicsEngine {
	
	/** The list of left and right edges for broad phase collision detection */
	private final CollisionList xAxisList = new CollisionList();
	
	/** The list of top and bottom edges for broad phase collision detection */
	private final CollisionList yAxisList = new CollisionList();
	
	/**
	 * This function is called once before the simulation begins.
	 * 
	 * @param bodies all the bodies being simulated
	 */
	public void initialize(RigidBody[] bodies) {
		// ===== STEP 7 =====
		// Initialize the collision detection lists by adding the top, bottom,
		// left, and right edges of each body to the appropriate list.
		
	}
	
	/**
	 * This function is called once at every regular time interval (i.e. every
	 * frame) to update the locations of every body in the simulation.
	 * 
	 * @param bodies all the bodies being simulated
	 */
	public void simulate(RigidBody[] bodies) {
		// ===== STEP 1 =====
		// For each body, call PhysicsEngine#simulate.
		for(RigidBody body : bodies)
			simulate(body);
		// Check for collisions with PhysicsEngine#detectCollisions.
		detectCollisions(bodies);
	}
	
	/**
	 * Update the location of a single body.  This method will use the very
	 * simple Euler integration technique, which will serve our purposes but
	 * suffers from significant inaccuracy over time.  A more advanced physics
	 * engine would use a more advanced integration technique.
	 * 
	 * @param body the body whose location will be updated
	 */
	private void simulate(RigidBody body) {
		// Reset the object's color to black.
		body.color = Color.black;
		// ===== STEP 2 =====
		// Update the position of the body based on its velocity.
		// Velocity is the distance a body is moving over time,
		// and in this case, the unit of time is every frame.
		// Simply add the body's current velocity to its position.
		body.position.add(body.velocity);
		// Calculate the force being applied to the body.
		// Remember: force = mass * acceleration.
		// In this case, the only force we are simulating is gravity.
		// Use Settings.GRAVITATIONAL_ACCELERATION as your acceleration.
		// Use RigidBody#mass as the body's mass.
		Vector force = Vector.multiply(Settings.GRAVITATIONAL_ACCELERATION, body.mass);
		// Update the velocity of the body.
		// Add the body's current acceleration to its velocity.
		// Remember: acceleration = force / mass.
		Vector acceleration = Vector.divide(force, body.mass);
		body.velocity.add(acceleration);
	}
	
	/**
	 * Checks all simulated bodies to see if any have collided.  If a pair of
	 * bodies has collided, they need to change direction appropriately.
	 * 
	 * @param bodies all bodies being simulated
	 */
	private void detectCollisions(RigidBody[] bodies) {
		// BEGIN naive solution.
		// The code below tests every pair of bodies every frame.
		// This is highly inefficient, but it works.
		// When you are ready to begin working on collision detection,
		// comment out this code.
		for(int i=0; i<bodies.length-1; i++)
			for(int j=i+1; j<bodies.length; j++)
				if(bodies[i].overlaps(bodies[j]))
					collide(bodies[i], bodies[j]);
		// END naive solution.
		// ===== STEP 8 =====
		// Begin by sorting the X axis list and Y axis list.
		
		// For each body, check along its X axis for overlaps.
		
	}
	
	/**
	 * Check along the X axis to see if a given body's axis-aligned bounding
	 * box overlaps with that of any other bodies.
	 * 
	 * @param body the body to check
	 */
	private void checkX(RigidBody body) {
		// ===== STEP 9 =====
		// Begin at the left edge of the body.
		// Move through the list one node at a time until you reach its right edge.
		
		// If you find the left edge of another body,
		// check if the two bodies overlap on the Y axis using PhysicsEngine#checkY.
		
	}
	
	/**
	 * Check along the Y axis to see if two bodies overlap.  Specifically,
	 * check if the bottom edge of the second body is between the top and
	 * bottom edge of the first body.  This means that in order to detect
	 * if the two bodies overlap, this method must be called twice, once
	 * with arguments (b1, b2) and then again with arguments (b2, b1).
	 * 
	 * @param b1 the first body
	 * @param b2 the second body
	 * @return true if the bottom edge of the second body occurs between
	 * the top and bottom edge of the first body, false otherwise
	 */
	private boolean checkY(RigidBody b1, RigidBody b2) {
		// ===== STEP 10 =====
		// Begin at the top edge of b1.
		// Move through the list one node at a time until you reach b1's bottom edge.
		
		// If you find the bottom edge of b2, the axis-aligned
		// bounding boxes of the shapes overlap on both axes.
		// Use RigidBody#overlaps as a more accurate test.
		// If they truly overlap, call #PhysicsEngine#collide.
		
		return false;
	}
	
	/**
	 * Called when two objects have collided.  The objects should change
	 * direction, giving the impression that they have bounced off of one
	 * another.
	 * 
	 * @param b1 the first body
	 * @param b2 the second body
	 */
	private void collide(RigidBody b1, RigidBody b2) {
		// Set both objects' color to red to indicate a collision.
		b1.color = Color.red;
		b2.color = Color.red;
		// ===== STEP 3 =====
		// Calculate some important information needed for the collision.
		// Find the normal vector of the collision pointing toward b1.
		// Use RigidBody#getNormal for this.
		Vector normal = b1.getNormal(b2);
		// Calculate the relative velocity of the two bodies.
		// This simply means subtract b1's velocity from b2's velocity.
		Vector relative = Vector.subtract(b2.velocity, b1.velocity);
		// Calculate the velocity along the collision normal.
		// This is simply the dot product of the normal vector and relative velocity.
		double velocityAlongNormal = Vector.dotProduct(relative, normal);
		// Correct the position of both bodies relative to one another.
		correctPosition(b1, b2);
		// ===== STEP 4 =====
		// Change the directions of the two bodies using an impulse.
		// An impulse is simply an instantaneous change in velocity.
		// First, calculate the impulse scalar.  This is a single number by
		// which we will multiply the normal vector to get the impulse.
		// impulse scalar = (1 + restitution) * velocity along the normal
		// Use Settings#RESTITUTION for the restitution value.
		double impulseScalar = (1 + Settings.RESTITUTION) * velocityAlongNormal;
		// Now calculate the impulse that needs to be applied to both bodies.
		// This is simply the impulse scalar that you calculated above
		// multiplied by the collision normal.
		Vector impulse = Vector.multiply(normal, impulseScalar);
		// When two objects collide, they impart force to one another relative
		// to their masses.
		// Calculate the relative mass of b1 (i.e. its proportion of the total mass).
		double b1relativeMass;
		double b2relativeMass;
		if(b1.mass == RigidBody.INFINITE_MASS) {
			b1relativeMass = 1;
			b2relativeMass = 0;
		}
		else if(b2.mass == RigidBody.INFINITE_MASS) {
			b1relativeMass = 0;
			b2relativeMass = 1;
		}
		else {
			b1relativeMass = b1.mass / (b1.mass + b2.mass);
			b2relativeMass = b2.mass / (b1.mass + b2.mass);
		}
		// Now calculate the impulse to be applied by multiplying the impulse
		// by b1's relative mass.  Note that if a body has infinite mass (which
		// is represented as mass = 0), then its impulse will become 0.
		Vector b1impulse = Vector.multiply(impulse, 1 - b1relativeMass);
		Vector b2impulse = Vector.multiply(impulse, 1 - b2relativeMass);
		// Remember that every action has an equal but opposite reaction.
		// This means that we need to add b1's impulse to b1
		// and subtract b2's impulse from b2.
		b1.velocity.add(b1impulse);
		b2.velocity.subtract(b2impulse);
	}
	
	/**
	 * Adjust the position of two overlapping bodies so that they no longer
	 * overlap.
	 * 
	 * @param b1 the first body
	 * @param b2 the second body
	 */
	private void correctPosition(RigidBody b1, RigidBody b2) {
		// ===== STEP 5 =====
		// Calculate how much the bodies overlap on the X and Y axes.
		// Use Utilities#getXOverlap and Utilities#getYOverlap.
		double xOverlap = Utilities.getXOverlap(b1, b2);
		double yOverlap = Utilities.getYOverlap(b1, b2);
		// These vectors will be the change in position for b1 and b2 so that
		// they will no longer overlap.  We need to move both bodies back the
		// way they came.  Initialize both vectors to the reverse of each
		// body's current velocity.
		Vector b1correction = Vector.multiply(b1.velocity, -1);
		Vector b2correction = Vector.multiply(b2.velocity, -1);
		// If the x overlap is smaller than the y overlap, this is a vertical
		// or left / right collision.  Scale the correction vectors so that
		// their x values are exactly the x overlap.  Use Vector#scaleXto.
		if(xOverlap < yOverlap) {
			b1correction.scaleXto(xOverlap);
			b2correction.scaleXto(xOverlap);
		}
		// If the y overlap is smaller than the x overlap, this is a horizontal
		// or top / bottom collision.  Scale the correction vectors so that
		// their y values are exactly the y overlap.  Use Vector#scaleYto.
		else {
			b1correction.scaleYto(yOverlap);
			b2correction.scaleYto(yOverlap);
		}
		// Remember that objects move relative to their mass.  Scale the
		// correction vectors to be each body's proportion of the total mass.
		// Note that if a body's mass = 0 (infinite mass) then its position
		// correction will be reduced to 0.
		double b1relativeMass, b2relativeMass;
		if(b1.mass == RigidBody.INFINITE_MASS) {
			b1relativeMass = 1;
			b2relativeMass = 0;
		}
		else if(b2.mass == RigidBody.INFINITE_MASS) {
			b1relativeMass = 0;
			b2relativeMass = 1;
		}
		else {
			b1relativeMass = b1.mass / (b1.mass + b2.mass);
			b2relativeMass = b2.mass / (b1.mass + b2.mass);
		}
		b1correction.multiply(1 - b1relativeMass);
		b2correction.multiply(1 - b2relativeMass);
		// Now move both bodies by adding the correction vectors to their
		// current positions.
		b1.position.add(b1correction);
		b2.position.add(b2correction);
	}
}
