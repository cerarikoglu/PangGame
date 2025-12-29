package asset;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class PlayerAsset extends AssetManager {
	private static BufferedImage playerSheet;
    private static final HashMap<String, BufferedImage> playerMap = new HashMap<>();
	
	@Override
	public void load() {
		playerSheet = loadImage("/assets/Player.png");
		BufferedImage clearSheet = makeColorTransparent(playerSheet, new Color(0, 255, 0));
		playerMap.put("activePlayerImage", crop(clearSheet,10,2,32,32));
		BufferedImage livesCropped = crop(clearSheet, 154, 44, 16, 16);
		playerMap.put("playerLivesImage",livesCropped);
		BufferedImage upCropped = crop(clearSheet, 10, 36, 32, 32);
		playerMap.put("moveUpPlayer", upCropped);
	}
	public static BufferedImage getPlayer(String playerImage) {
		return playerMap.get(playerImage);
	}

	
}
