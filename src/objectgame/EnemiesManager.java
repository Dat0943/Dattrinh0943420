package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import userinterface.GameScreen;
import util.Resource;

public class EnemiesManager {
	
	private List<Enemy> enemies;
	private Random random;
	
	private BufferedImage imageCactus1, imageCactus2;
	private MainCharacter mainCharacter;
	private GameScreen gameScreen;
	
	public EnemiesManager(MainCharacter mainCharacter, GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		this.mainCharacter = mainCharacter;
		enemies = new ArrayList<Enemy>();
		imageCactus1 = Resource.getResourceImage("C:\\Users\\Admin\\eclipse-workspace\\ObstacleGame\\src\\data\\thungdau01.jpg");
		imageCactus2 = Resource.getResourceImage("C:\\Users\\Admin\\eclipse-workspace\\ObstacleGame\\src\\data\\thungdau02.jpg");	
		random = new Random();
		
		enemies.add(getRandomCactus());
	}
	
	public void update() {
		for (Enemy enemy : enemies) {
			enemy.update();
			if(enemy.isOver() && !enemy.isScoreGot()) {
				gameScreen.plusScore(1);
				enemy.setIsScoreGot(true);
			}
			
			if(enemy.getBound().intersects(mainCharacter.getBound())) {
				mainCharacter.setAlive(false);
			}
		}
		
		Enemy firstEnemy = enemies.get(0);
		if(firstEnemy.isOutOfScreen()) {
			enemies.remove(firstEnemy);
			enemies.add(getRandomCactus());
		}
	}
	
	public void draw(Graphics g) {
		for (Enemy enemy : enemies) {
			enemy.draw(g);
		}
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(getRandomCactus());
	}
	
	private Cactus getRandomCactus() {
		Cactus cactus;
		cactus = new Cactus(mainCharacter);
		cactus.setPosX(600);
		if(random.nextBoolean()) {
			cactus.setCactusImage(imageCactus1);
		} else {
			cactus.setCactusImage(imageCactus2);
		}
		return cactus;
	}
}
