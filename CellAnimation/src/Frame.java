import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

public class Frame extends JFrame{
	private final Dimension SCREEN_SIZE, FRAME_SIZE;
	
	private AnimationPanel animationPanel;

	/**
	 * Create the application.
	 */
	public Frame() {
		SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
		
		FRAME_SIZE = new Dimension(SCREEN_SIZE.width / 10 * 8, SCREEN_SIZE.height / 10 * 8);
		
		animationPanel = new AnimationPanel(FRAME_SIZE);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		setSize(FRAME_SIZE);
		
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		mainPanel.add(animationPanel);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Frame f = new Frame();
	}
}
