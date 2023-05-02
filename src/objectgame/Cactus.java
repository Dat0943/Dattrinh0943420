package objectgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.Resource;

public class Cactus extends Enemy{
	
	private BufferedImage cactusImage;
	private int posX, posY;
	private Rectangle rect;
	private MainCharacter mainCharacter;
	private boolean isScoreGot = false;
	
	public Cactus(MainCharacter mainCharacter) {
		this.mainCharacter = mainCharacter;
		cactusImage = Resource.getResourceImage("C:\\Users\\Admin\\eclipse-workspace\\JavaGame_6\\src\\data\\cactus1.png");
		posX = 200;
		posY = 65;
		rect = new Rectangle();
	}
	
	public void update() {
		posX -= 5;
		rect.x = posX;
		rect.y = posY;
		rect.width = cactusImage.getWidth();
		rect.height = cactusImage.getHeight();
	}
	
	@Override
	public Rectangle getBound() {
		return rect;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(cactusImage, posX, posY, null);
	}

	@Override
	public boolean isOutOfScreen() {
		return (posX + cactusImage.getWidth() < 0);
	}
	
	@Override
	public boolean isOver() {
		return (mainCharacter.getX() > posX); 
	}
	
	@Override
	public boolean isScoreGot() {
		return isScoreGot;
	}

	@Override
	public void setIsScoreGot(boolean isScoreGot) {
		this.isScoreGot = isScoreGot;
	}
	
	public void setCactusImage(BufferedImage cactusImage) {
		this.cactusImage = cactusImage;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
