package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static userinterface.GameScreen.GROUDY;

import util.Resource;

public class Land {
	
	private List<ImageLand> listImage;
	private BufferedImage imageLand1, imageLand2, imageLand3;
	private Random random; 
	
	private class ImageLand {
		int posX;
		BufferedImage image;
	}
	
	public Land() {
		random = new Random();
		imageLand1 = Resource.getResourceImage("C:\\Users\\Admin\\eclipse-workspace\\ObstacleGame\\src\\data\\land1.png");
		imageLand2 = Resource.getResourceImage("C:\\Users\\Admin\\eclipse-workspace\\ObstacleGame\\src\\data\\land2.png");
		imageLand3 = Resource.getResourceImage("C:\\Users\\Admin\\eclipse-workspace\\ObstacleGame\\src\\data\\land3.png");
		listImage = new ArrayList<ImageLand>();
		
		int numberOfLandTiTle = 600 / imageLand1.getWidth() + 2;
		for(int i = 0; i < numberOfLandTiTle; i++) {
			ImageLand imageLand = new ImageLand();
			imageLand.posX = (int) (i * imageLand1.getWidth());
			imageLand.image = getImageLand();
			listImage.add(imageLand);
		}
	}
	
	public void update() {
		
		for (ImageLand imageLand : listImage) {
			imageLand.posX -= 5; 
		}
		
		ImageLand firstElement = listImage.get(0);
		if(firstElement.posX + imageLand1.getWidth() < 0) {
			firstElement.posX = listImage.get(listImage.size() - 1).posX + imageLand1.getWidth();
			listImage.add(firstElement);
			listImage.remove(0);		
		}
	}
	
	public void draw(Graphics g) {
		for(ImageLand imageLand : listImage) {
			g.drawImage(imageLand.image, imageLand.posX, (int) GROUDY - 20, null);
		}	
	}
	
	private BufferedImage getImageLand() {
		int i = random.nextInt(10);
		switch (i) {
		case 0: 
			return imageLand1; 
		case 1: 
			return imageLand3;
		default: 
			return imageLand2;	
		}
	}
}
