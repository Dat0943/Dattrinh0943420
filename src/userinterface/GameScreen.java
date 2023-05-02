package userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import objectgame.Cactus;
import objectgame.EnemiesManager;
import objectgame.Land;
import objectgame.MainCharacter;
import objectgame.Clounds;
import util.Resource;

public class GameScreen extends JPanel implements Runnable, KeyListener{
	
	public static final float GRAVITY = 0.4f; 
	public static final float GROUDY = 100; 
	
	public static final int GAME_FIRST_STATE = 0;
	public static final int GAME_PLAY_STATE = 1;
	public static final int GAME_OVER_STATE = 2;
	
	private MainCharacter mainCharacter;
	private Thread thread;
	private Land land;
	private Clounds clounds;
	private EnemiesManager enemiesManager;
	private int score;
	
	private int gameState = GAME_FIRST_STATE;
	
	private BufferedImage imageGameOverText;
	
	public GameScreen() {
		thread = new Thread(this);
		mainCharacter = new MainCharacter();
		
		mainCharacter.setX(50); 
		mainCharacter.setY(65);
		
		clounds = new Clounds();
		land = new Land();
		enemiesManager = new EnemiesManager(mainCharacter, this);	
		imageGameOverText = Resource.getResourceImage("C:\\Users\\Admin\\eclipse-workspace\\ObstacleGame\\src\\data\\gameover_text.png");
	}

	public void startGame() {
		thread.start();
	}
	
	@Override
	public void run() {
		while (true) {
			try {			
				update();
				repaint();
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		
		switch (gameState) {
			case GAME_PLAY_STATE:
				mainCharacter.update();
				land.update();
				enemiesManager.update();
				clounds.update();
				
				if(!mainCharacter.getAlive()) {
					gameState = GAME_OVER_STATE;
				}
				break;
		}		
	}
	
	public void plusScore(int score) {
		this.score += score;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawLine(0, (int) GROUDY, getWidth(), (int) GROUDY);
		
		switch (gameState) {
			case GAME_FIRST_STATE:
				mainCharacter.draw(g);
				break;
			case GAME_PLAY_STATE:
				land.draw(g);
				mainCharacter.draw(g);
				enemiesManager.draw(g);
				clounds.draw(g);
				
				g.drawString("SCORE: " + String.valueOf(score), 500, 20); 
				break;
			case GAME_OVER_STATE:
				land.draw(g);
				mainCharacter.draw(g);
				enemiesManager.draw(g);
				clounds.draw(g);
				g.drawImage(imageGameOverText, 100, 50, null);
				
				g.drawString("SCORE: " + String.valueOf(score), 500, 20); 
				break;	
		}
	}
	
	public void reset() {
		mainCharacter.setAlive(true);
		mainCharacter.setX(50);
		mainCharacter.setY(65);
		enemiesManager.reset();
		score = 0;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if(gameState == GAME_FIRST_STATE) {
					gameState = GAME_PLAY_STATE;
				} else if(gameState == GAME_PLAY_STATE){
					mainCharacter.jump();
				} else if(gameState == GAME_OVER_STATE) {
					reset();
					gameState = GAME_PLAY_STATE;					
				}
				break;
		}	
	}
}
