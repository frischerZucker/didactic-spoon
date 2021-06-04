import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import java.awt.Color;

public class AnimationPanel extends JPanel implements Runnable{
	private Dimension PANEL_SIZE;
	
	private int posX, posY;
	
	private Thread thread;
	
	private Animation animation;
	
	public Boolean isRunning = false;

	/**
	 * Create the panel.
	 */
	public AnimationPanel(Dimension size) {	
		PANEL_SIZE = new Dimension(size.height / 20 * 17, size.height / 20 * 17);
		
		posX = size.height / 20;
		posY = size.height / 20;
		
		thread = new Thread(this);
		
		animation = new Animation(100, 100);
		
		initialize();
	
		start();
	}
	
	private void initialize() {
		setBackground(Color.ORANGE);
		
		setBounds(posX, posY, PANEL_SIZE.width, PANEL_SIZE.height);
		
		setVisible(true);
	}
	
	public void start() {
		isRunning = true;
		
		thread.start();
	}
	
	public void stop() {
		isRunning = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(animation.img, 0, 0, PANEL_SIZE.width, PANEL_SIZE.height, this);
		
		g.setFont(new Font("", 1, 20));
		
		g.setColor(Color.green);
		
		g.drawString(Integer.toString(animation.cellsAlive), 10, 30);
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		
		final double ns = 1000000000 / 10;
		
		double delta = 0;
		
		while(isRunning) {
			long currentTime = System.nanoTime();
			
			delta += currentTime - lastTime;
			
			lastTime = currentTime;
			
			if(delta >= ns) {
				delta = 0;
				
				animation.updateField();
				
				repaint();
			}
		}
	}
}
