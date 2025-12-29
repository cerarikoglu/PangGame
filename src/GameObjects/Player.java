package GameObjects;
import asset.PlayerAsset;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Player {
	private int x,y;
	private int width,height;
	private int speed;
	private BufferedImage imageRight;
	private BufferedImage imageLeft;

	private BufferedImage image;
	private Rectangle bounds;
	
	private boolean moveRight = false;
	private boolean moveLeft = false;

	public Player(int startX , int startY) {
		BufferedImage original = PlayerAsset.getPlayer("activePlayerImage");

		Image scaled = original.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(scaled, 0, 0, null);
		g2d.dispose();

		this.imageRight = resized;
		this.imageLeft = flipHorizontally(resized);

		this.image = imageRight; 
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.x = startX;
		this.y = startY - 100;
		this.bounds = new Rectangle(x, y, width, height);
		this.speed = 5;
	}
	
	private BufferedImage flipHorizontally(BufferedImage src) {
		int w = src.getWidth();
		int h = src.getHeight();
		BufferedImage flipped = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = flipped.createGraphics();
		g.drawImage(src, 0, 0, w, h, w, 0, 0, h, null); 
		g.dispose();
		return flipped;
	}



	public void move(boolean[][] fgCollisionMap) {
		if (moveLeft) {
			int nextX = Math.max(0, x - speed);
			if (!checkCollision(nextX, y, fgCollisionMap)) {
				x = nextX;
			}
			image = imageLeft;
		}
		if (moveRight) {
			int nextX = Math.min(800 - width, x + speed); 
			if (!checkCollision(nextX, y, fgCollisionMap)) {
				x = nextX;
			}
			image = imageRight;
		}
		bounds.setLocation(x, y);
	}


	public boolean checkCollision(int nextX,int y, boolean[][] fgCollisionMap) {
		if (fgCollisionMap == null) return false;
		int maxX = fgCollisionMap.length;
		int maxY = fgCollisionMap[0].length;
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				int fx = nextX + i;
				int fy = y + j;
				if(fx >= 0 && fx < maxX && fy >= 0 && fy < maxY && fgCollisionMap[fx][fy]) {
					return true;
				}
			}
		}
		return false;
	}
	public void setSpeed(int speed) {
	    this.speed = speed;
	}

	
	public void setMovingLeft(boolean movingLeft) {
        this.moveLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.moveRight = movingRight;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
