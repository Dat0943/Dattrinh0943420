package objectgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.Animation;
import util.Resource;

import static userinterface.GameScreen.GROUDY;
import static userinterface.GameScreen.GRAVITY;;

public class MainCharacter {
	private float x = 0;
	private float y = 0;	
	private float speedY = 0;
	private Animation characterRun;
	private Rectangle rect; 
	private boolean isAlive = true; 
	
	public MainCharacter() {
		characterRun = new Animation(200);
		characterRun.addFrame(Resource.getResourceImage("C:\\Users\\Admin\\eclipse-workspace\\ObstacleGame\\src\\data\\robot1.png"));
		characterRun.addFrame(Resource.getResourceImage("C:\\Users\\Admin\\eclipse-workspace\\ObstacleGame\\src\\data\\robot2.png"));
		rect = new Rectangle();
	}
	
	public void update() {
		characterRun.update();
		
		if(y >= GROUDY - characterRun.getFrame().getHeight()) {
			speedY = 0;
			y = GROUDY - characterRun.getFrame().getHeight();
		} else {
			speedY += GRAVITY;
			y += speedY;
		}	
		
		rect.x = (int) x;
		rect.y = (int) y;
		rect.width = characterRun.getFrame().getWidth();
		rect.height = characterRun.getFrame().getHeight();
	}
	
	public Rectangle getBound() {
		return rect;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawImage(characterRun.getFrame(), (int) x, (int) y, null);
	}
	
	public void jump() {
		speedY = (float)-6; 
		y += speedY;
	}
	 
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getSpeedY() {
		return speedY;
	}
	
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	public boolean getAlive() {
		return isAlive;
	}
	
	public void setAlive(boolean alive) {
		isAlive = alive;
	}
}
