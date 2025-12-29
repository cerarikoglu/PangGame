package asset;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class BGroundAsset extends AssetManager {

	private static BufferedImage bgSheet;
	private static final HashMap<String,BufferedImage> backgroundMap = new HashMap<>(); 
	
	@Override
	public void load() {
		bgSheet = loadImage("/assets/Backgrounds.png");
		int bgWidth = 384;
		int bgHeight = 208;
		backgroundMap.put("level1Image", crop(bgSheet,8,8,bgWidth,bgHeight));
		backgroundMap.put("level2Image", crop(bgSheet,8,440,bgWidth,bgHeight));
		backgroundMap.put("level3Image", crop(bgSheet,8,1736,bgWidth,bgHeight));
		backgroundMap.put("level4Image", crop(bgSheet,8,3680,bgWidth,bgHeight));
	}
	
	
	public static BufferedImage getBgImage(String level) {
		return backgroundMap.get(level);
	}
	
	
}
