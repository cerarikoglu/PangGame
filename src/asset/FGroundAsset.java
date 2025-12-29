package asset;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class FGroundAsset extends AssetManager {
	private static BufferedImage fgSheet;
	private static final HashMap<String,BufferedImage> foregroundMap = new HashMap<>();
	private static final HashMap<String, boolean[][]> collisionMap = new HashMap<>();
	
	
	@Override
	public void load() {
		fgSheet = loadImage("/assets/Foreground.png");
		
		loadFgLevel("level1FgImage", 8, 44);
		loadFgLevel("level2FgImage", 400, 260);
		loadFgLevel("level3FgImage", 400, 908);
		loadFgLevel("level4FgImage", 400, 1556);

	} 
	public static void loadFgLevel(String key, int x, int y) {
	    BufferedImage cropped = crop(fgSheet, x, y, 384, 208);
	    BufferedImage transparent = makeColorTransparent(cropped, new Color(145, 129, 137));

	    int newWidth = (int)(transparent.getWidth() * 1.5);
	    int newHeight = (int)(transparent.getHeight() * 1.5);

	    Image scaled = transparent.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	    BufferedImage resized = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = resized.createGraphics();
	    g2d.drawImage(scaled, 0, 0, null);
	    g2d.dispose();

	    foregroundMap.put(key, resized);
	    collisionMap.put(key, generateCollisionMap(resized));
	}

	public static boolean[][] getCollisionMap(String level) {
	    return collisionMap.get(level);
	}
	
	private static boolean[][] generateCollisionMap(BufferedImage image) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    boolean[][] map = new boolean[w][h];

	    for (int x = 0; x < w; x++) {
	        for (int y = 0; y < h; y++) {
	            int pixel = image.getRGB(x, y);
	            int alpha = (pixel >> 24) & 0xff; 

	            map[x][y] = alpha > 0; 
	        }
	    }
	    return map;
	}
	
	public static BufferedImage getFgImage(String level) {
	    return foregroundMap.get(level);
	}

}
