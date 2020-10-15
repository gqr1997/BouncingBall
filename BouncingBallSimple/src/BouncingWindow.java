import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.util.*;

public class BouncingWindow extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Random rand = new Random();
	
	// Container box's width and height
	private static final int BOX_WIDTH = 640;
	private static final int BOX_HEIGHT = 480;
	private static final int UPDATE_RATE = 30; // Number of refresh per second
	
	private int NUM_OF_BALLS = 4; // Number of balls.
		
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	
	private Color[] rainbowColor = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
			Color.CYAN, Color.MAGENTA, Color.WHITE, Color.BLACK};
	
	private Color backgroundColor = Color.WHITE;

	/** Constructor to create the UI components and init game objects. */
	public BouncingWindow() {
		this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
		
		startGame();
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					backgroundColor = rainbowColor[rand.nextInt(rainbowColor.length)];
				}
				if (e.getKeyCode() == KeyEvent.VK_Q) {
					for (int i=0; i<NUM_OF_BALLS; i++) {
						Ball currentBall = balls.get(i);
						currentBall.setBallSpeedX(currentBall.getBallSpeedX()*2);
						currentBall.setBallSpeedY(currentBall.getBallSpeedY()*2);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_E) {
					for (int i=0; i<NUM_OF_BALLS; i++) {
						Ball currentBall = balls.get(i);
						currentBall.setBallSpeedX(currentBall.getBallSpeedX()/2);
						currentBall.setBallSpeedY(currentBall.getBallSpeedY()/2);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_R) {
					balls = new ArrayList<Ball>();
					NUM_OF_BALLS = 4;
					startGame();
				}
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				balls.add(new Ball());
				NUM_OF_BALLS++;
				runSingleBall(NUM_OF_BALLS-1);
			}
		});
		this.setFocusable(true);
        this.requestFocusInWindow();
		
	}
	
	private void startGame() {
		// Initialize balls.
		for (int i=0; i<NUM_OF_BALLS; i++) {
			balls.add(new Ball());
		}
		for (int i=0; i<NUM_OF_BALLS; i++) {
			runSingleBall(i);
		}
	}
		
	private void runSingleBall(int index) {
		Ball currentBall = balls.get(index);
		// Start the ball bouncing (in its own thread)
		Thread gameThread = new Thread() {
			public void run() {
				while (true) { // Execute one update step
					// Calculate the ball's new position
					currentBall.setBallX(currentBall.getBallX()+currentBall.getBallSpeedX());
					currentBall.setBallY(currentBall.getBallY()+currentBall.getBallSpeedY());
					// Check if the ball moves over the bounds
					// If so, adjust the position and speed.
					if (currentBall.getBallX() - currentBall.getBallRadius() < 0) {
						currentBall.setBallSpeedX(-currentBall.getBallSpeedX());
						currentBall.setBallX(currentBall.getBallRadius());
					} else if (currentBall.getBallX() + currentBall.getBallRadius() > BOX_WIDTH) {
						currentBall.setBallSpeedX(-currentBall.getBallSpeedX());
						currentBall.setBallX(BOX_WIDTH - currentBall.getBallRadius());
					}
					// May cross both x and y bounds
					if (currentBall.getBallY() - currentBall.getBallRadius() < 0) {
						currentBall.setBallSpeedY(-currentBall.getBallSpeedY());
						currentBall.setBallY(currentBall.getBallRadius());
					} else if (currentBall.getBallY() + currentBall.getBallRadius() > BOX_HEIGHT) {
						currentBall.setBallSpeedY(-currentBall.getBallSpeedY());
						currentBall.setBallY(BOX_HEIGHT - currentBall.getBallRadius());
					}
					// Refresh the display
					repaint(); // Callback paintComponent()
					// Delay for timing control and give other threads a chance
					try {
						Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
					} catch (InterruptedException ex) { }
				}
			}
		};
		gameThread.start();  // Callback run()
	}
		  
	/** Custom rendering codes for drawing the JPanel */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);    // Paint background
		
		// Draw the box
		g.setColor(backgroundColor);
		g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
		
		// Draw the ball
		for (int i=0; i<NUM_OF_BALLS; i++) {
			Ball currentBall = balls.get(i);
			g.setColor(currentBall.getColor());
			g.fillOval((int) (currentBall.getBallX() - currentBall.getBallRadius()), 
					(int) (currentBall.getBallY() - currentBall.getBallRadius()), 
					(int)(2 * currentBall.getBallRadius()), (int)(2 * currentBall.getBallRadius()));
		}
		
	}
	
}
