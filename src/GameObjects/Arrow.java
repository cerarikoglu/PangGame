package GameObjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import asset.ItemsAsset;

public class Arrow {
    private int x;
    private final int baseY; 
    private int height = 0;   
    private final int maxHeight;
    private int frameIndex = 1;

    public Arrow(int x, int baseY, int maxHeight) {
        this.x = x;
        this.baseY = baseY;
        this.maxHeight = maxHeight;
    }

    public void update() {
        if (height < maxHeight) {
            height += 8; 
            frameIndex = Math.min(height / 5 + 1, 70); 
        }
    }

    public boolean isOutOfBounds() {
        return height >= maxHeight;
    }

    public BufferedImage getImage() {
        return ItemsAsset.getItem("arrow" + frameIndex);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return baseY - height;
    }

    public Rectangle getBounds() {
        BufferedImage img = getImage();
        return new Rectangle(x, baseY - height, img.getWidth(), height);
    }
}

