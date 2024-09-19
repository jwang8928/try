/**
 * The foundation on which you will build a very simple 2D video game physics
 * engine.
 * 
 * <p>In this tutorial, you will create a very simple physics game similar to
 * KickUps that involves clicking on a bouncing ball in an attempt to knock it
 * into a basket in the center of the screen.</p>
 * <p>The skeleton of a 2D physics engine is provided here, including:</p>
 * <ul>
 * 	 <li>{@link edu.uky.games.physics_game.GameFrame} and
 *   {@link edu.uky.games.physics_game.GamePanel} for drawing
 *   rectangles and circles and getting user input.</li>
 *   <li>{@link edu.uky.games.physics_game.physics.Vector} for
 *   representing vectors and points and for doing basic vector math
 *   operations.</li>
 *   <li>A suite of helpful
 *   {@link edu.uky.games.physics_game.Utilities}.</li>
 *   <li>A customizable list of
 *   {@link edu.uky.games.physics_game.Settings}.</li>
 *   <li>A {@link edu.uky.games.physics_game.physics.PhysicsEngine}
 *   which you will write, which is responsible for updating the position of
 *   each body before each frame and for handling collisions.</li>
 *   <li>The components to build an efficient broad phase collision detection
 *   algorithm.</li>
 * </ul>
 * <p>Key parts of the code are missing.  You will fill in these missing code
 * fragments with help from in-line comments.</p>
 * <p>It is recommended that you work in this order:</p>
 * <ul>
 *   <li><b>PART I: Body Movement</b></li>
 *   <li>Step 1: Write the high-level method
 *   {@link edu.uky.games.physics_game.physics.PhysicsEngine#simulate(edu.uky.games.physics_game.physics.RigidBody[]) PhysicsEngine#simulate(RigidBody[] bodies)},
 *   which simulates all the bodies in the game.</li>
 *   <li>Step 2: Fill in the low-level method
 *   {@link edu.uky.games.physics_game.physics.PhysicsEngine#simulate(edu.uky.games.physics_game.physics.RigidBody) PhysicsEngine#simulate(RigidBody body)},
 *   which simulates an individual body.</li>
 *   <li>Checkpoint: Now the ball should move, but it will fall through the
 *   floor because there is no way to handle collisions.</li>
 *   <li><b>PART II: Handling Collisions</b></li>
 *   <li>Step 3: Add the basic information needed for collisions to
 *   {@link edu.uky.games.physics_game.physics.PhysicsEngine#collide(edu.uky.games.physics_game.physics.RigidBody, edu.uky.games.physics_game.physics.RigidBody) PhysicsEngine#collide}.</li>
 *   <li>Step 4: Make objects react to collisions by filling in more of
 *   {@link edu.uky.games.physics_game.physics.PhysicsEngine#collide(edu.uky.games.physics_game.physics.RigidBody, edu.uky.games.physics_game.physics.RigidBody) PhysicsEngine#collide}.</li>
 *   <li>Checkpoint: Now the ball should bounce off of other objects, but it
 *   may stick to or sink into them.</li>
 *   <li>Step 5: Move overlapping objects apart by filling in
 *   {@link edu.uky.games.physics_game.physics.PhysicsEngine#correctPosition(edu.uky.games.physics_game.physics.RigidBody, edu.uky.games.physics_game.physics.RigidBody) PhysicsEngine#correctPosition}.</li>
 *   <li>Checkpoint: Now the ball should bounce realistically.</li>
 *   <li>Step 6: Make the game react to user input by filling in
 *   {@link edu.uky.games.physics_game.GamePanel#controller GamePanel#controller#mousePressed}.</li>
 *   <li>Checkpoint: Now the physics should be totally working; however, the
 *   game should be performing exactly 28 narrow phase collision checks per
 *   frame.</li>
 *   <li><b>PART III: Efficient Broad Phase Collision Detection</b></li>
 *   <li>Step 7: Write the code to initialize the collision detection lists in
 *   {@link edu.uky.games.physics_game.physics.PhysicsEngine#initialize(edu.uky.games.physics_game.physics.RigidBody[]) PhysicsEngine#initialize}.
 *   </li>
 *   <li>Step 8: Comment out the naive collision detection code in
 *   {@link edu.uky.games.physics_game.physics.PhysicsEngine#detectCollisions(edu.uky.games.physics_game.physics.RigidBody[]) PhysicsEngine#detectCollisions}
 *   and fill in the more efficient code.</li>
 *   <li>Step 9: Fill in
 *   {@link edu.uky.games.physics_game.physics.PhysicsEngine#checkX(edu.uky.games.physics_game.physics.RigidBody) PhysicsEngine#checkX}
 *   to scan for bodies that overlap on the X axis.</li>
 *   <li>Step 10: Fill in
 *   {@link edu.uky.games.physics_game.physics.PhysicsEngine#checkY(edu.uky.games.physics_game.physics.RigidBody, edu.uky.games.physics_game.physics.RigidBody) PhysicsEngine#checkY}
 *   to verify that bodies also overlap on the Y axis.</li>
 *   <li>Checkpoint: Collision detection should work again, and the game should
 *   be performing less than 1 narrow phase collision tests per frame.</li>
 * </ul>
 */
package edu.uky.games.physics_game;