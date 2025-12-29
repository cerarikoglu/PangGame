package asset;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BubbleAsset extends AssetManager{
	private static BufferedImage bubbleSheet;
    private static final HashMap<String, BufferedImage> bubbleMap = new HashMap<>();

	@Override
	public void load() {
		bubbleSheet = loadImage("/assets/baloons.png");
		bubbleMap.put("extraLarge", scale2x(crop(bubbleSheet, 1,   6,  48, 40)));
	    bubbleMap.put("large",     scale2x( crop(bubbleSheet, 52,  13,  32, 26)));
	    bubbleMap.put("medium",     scale2x(crop(bubbleSheet, 86,  19,  16, 14)));
	    bubbleMap.put("small",      scale2x(crop(bubbleSheet, 106, 23, 8, 7)));
	}
	private BufferedImage scale2x(BufferedImage original) {
        int w = original.getWidth();
        int h = original.getHeight();
        BufferedImage scaled = new BufferedImage(w * 2, h * 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaled.createGraphics();
        g.drawImage(original, 0, 0, w * 2, h * 2, null);
        g.dispose();
        return scaled;
    }
	public static BufferedImage getBubble(String size) {
		return bubbleMap.get(size);
	}
	 public static int getBubbleWidth(String sizeLevel) {
	        BufferedImage img = bubbleMap.get(sizeLevel);
	        
	        if(img != null ) return img.getWidth();
	        else return 0;
	}
	 
	 public static int getBubbleHeight(String sizeLevel) {
		 BufferedImage img = bubbleMap.get(sizeLevel);
		 if(img != null ) return img.getHeight();
	        else return 0;	 }

		
}
