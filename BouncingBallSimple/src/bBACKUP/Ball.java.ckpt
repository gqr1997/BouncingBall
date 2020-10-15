import java.awt.Color;
import java.util.*;

public class Ball {
	Random rand = new Random();
	// Ball's properties
	private float ballRadius = rand.nextInt(10) + 5; // Ball's radius
	private float ballX = ballRadius + rand.nextInt(400); // Ball's center (x, y)
	private float ballY = ballRadius + rand.nextInt(400); 
	private float ballSpeedX = rand.nextInt(10);   // Ball's speed for x and y
	private float ballSpeedY = rand.nextInt(10);
	private Color color = randomColor();
	
	public Ball(float radius, float x, float y, float speedX, float speedY, Color color) {
		this.ballRadius = radius;
		this.ballX = x;
		this.ballY = y;
		this.ballSpeedX = speedX;
		this.ballSpeedY = speedY;
		this.color = color;
	}
	
	public Ball() {}
	
	public Color randomColor() {
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return new Color(r, g, b);
    }
	
	public Color getColor() {
		return this.color;
	}
	
	public void setBallX(float x) {
		this.ballX = x;
	}
	
	public void setBallY(float y) {
		this.ballY = y;
	}
	
	public void setBallSpeedX(float speedX) {
		this.ballSpeedX = speedX;
	}
	
	public void setBallSpeedY(float speedY) {
		this.ballSpeedY = speedY;
	}
	
	public float getBallX() {
		return this.ballX;
	}
	
	public float getBallY() {
		return this.ballY;
	}
	
	public float getBallSpeedX() {
		return this.ballSpeedX;
	}
	
	public float getBallSpeedY() {
		return this.ballSpeedY;
	}
	
	public float getBallRadius() {
		return this.ballRadius;
	}
	
}
