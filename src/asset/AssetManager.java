package asset;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public abstract class AssetManager {
	
	protected static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(AssetManager.class.getResource(path));
		}
		catch(IOException e) {
			System.err.println("Error loading image: " + path);
            e.printStackTrace();
            return null;
		}
	}
	
	
	protected static BufferedImage crop(BufferedImage sheet, int x, int y, int w, int h) {
		return sheet.getSubimage(x, y, w, h);
	}
	public abstract void load();

	
	protected static BufferedImage makeColorTransparent(BufferedImage image, Color colorToTransparent) {
	    BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    for (int y = 0; y < image.getHeight(); y++) {
	        for (int x = 0; x < image.getWidth(); x++) {
	            int pixel = image.getRGB(x, y);
	            if ((pixel & 0x00FFFFFF) == (colorToTransparent.getRGB() & 0x00FFFFFF)) {
	                result.setRGB(x, y, 0x00000000);
	            } else {
	                result.setRGB(x, y, pixel);
	            }
	        }
	    }
	    return result;
	}
}
