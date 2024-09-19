package edu.uky.games.physics_game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * The high-level container for a {@link GamePanel}.
 * 
 * @author Stephen G. Ware
 */
public class GameFrame extends JFrame {

	/** Version 1 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new window for the game.
	 */
	public GameFrame() {
		setTitle(Settings.NAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new GamePanel(), BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Runs the game.
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		new GameFrame();
	}
}
