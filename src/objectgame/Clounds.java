package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import util.Resource;

public class Clounds {
	
	private BufferedImage cloundImage;
	private List<Clound> clounds;
	
	public Clounds() {
		cloundImage = Resource.getResourceImage("C:\\Users\\Admin\\eclipse-workspace\\ObstacleGame\\src\\data\\sun01.png");
		clounds = new ArrayList<Clound>();
		
		Clound clound1 = new Clound();
		clound1.posX = 100;
		clound1.posY = 50;
		clounds.add(clound1);		
		
		clound1 = new Clound();
		clound1.posX = 350;
		clound1.posY = 80;
		clounds.add(clound1);
		
		clound1 = new Clound();
		clound1.posX = 600;
		clound1.posY = 60;
		clounds.add(clound1);
	}
	
	public void update() {
		for (Clound clound : clounds) {
			clound.posX-=3;
		}
		
		Clound firstClound = clounds.get(0);
		if(firstClound.posX + cloundImage.getWidth() < 0) {
			firstClound.posX = 600;
			clounds.remove(firstClound);
			clounds.add(firstClound);
		}
	}
	
	public void draw(Graphics g) {
		for (Clound clound : clounds) {
			g.drawImage(cloundImage,(int) clound.posX, (int) clound.posY, null);
		}	
	}
	
	private class Clound {
		float posX;
		float posY;
	}
}
