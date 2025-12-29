package asset;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ItemsAsset extends AssetManager {
	private static BufferedImage itemSheet;
    private static final HashMap<String, BufferedImage> itemMap = new HashMap<>();
    
    @Override
    public void load() {
        itemSheet = loadImage("/assets/Items & Weapons.png");
        BufferedImage cleanSheet = makeColorTransparent(itemSheet,new Color(103, 150, 86));
        		
        		
        itemMap.put("arrow1", scale2x(crop(cleanSheet, 8, 1383, 9, 34)));
        itemMap.put("arrow2", scale2x(crop(cleanSheet, 25, 1381, 9, 36)));
        itemMap.put("arrow3", scale2x(crop(cleanSheet, 42, 1379, 9, 38)));
        itemMap.put("arrow4", scale2x(crop(cleanSheet, 59, 1376, 9, 41)));
        itemMap.put("arrow5", scale2x(crop(cleanSheet, 76, 1374, 9, 43)));
        itemMap.put("arrow6", scale2x(crop(cleanSheet, 93, 1372, 9, 45)));
        itemMap.put("arrow7", scale2x(crop(cleanSheet, 110, 1370, 9, 47)));
        itemMap.put("arrow8", scale2x(crop(cleanSheet, 127,1368, 9, 50)));
        itemMap.put("arrow9", scale2x(crop(cleanSheet, 144, 1366, 9, 52)));
        itemMap.put("arrow10", scale2x(crop(cleanSheet, 161, 1364, 9, 54)));
        itemMap.put("arrow11", scale2x(crop(cleanSheet, 178, 1362, 9, 56)));
        itemMap.put("arrow12", scale2x(crop(cleanSheet, 195, 1359,9, 59)));
        itemMap.put("arrow13", scale2x(crop(cleanSheet, 212, 1357, 9, 61)));
        itemMap.put("arrow14", scale2x(crop(cleanSheet, 229, 1355, 9, 63)));
        itemMap.put("arrow15", scale2x(crop(cleanSheet, 246, 1353, 9, 65)));
        itemMap.put("arrow16", scale2x(crop(cleanSheet, 263, 1350, 9, 68)));
        itemMap.put("arrow17", scale2x(crop(cleanSheet, 280, 1348, 9, 70)));
        itemMap.put("arrow18", scale2x(crop(cleanSheet, 297, 1346, 9, 72)));
        itemMap.put("arrow19", scale2x(crop(cleanSheet, 314, 1344, 9, 74)));
        itemMap.put("arrow20", scale2x(crop(cleanSheet, 331, 1341, 9, 77)));
        itemMap.put("arrow21", scale2x(crop(cleanSheet, 348, 1339, 9, 79)));
        itemMap.put("arrow22", scale2x(crop(cleanSheet, 365, 1337, 9, 81)));
        itemMap.put("arrow23", scale2x(crop(cleanSheet, 382, 1335, 9, 83)));
        itemMap.put("arrow24", scale2x(crop(cleanSheet, 8, 1474, 9, 86)));
        itemMap.put("arrow25", scale2x(crop(cleanSheet, 25, 1472, 9, 88)));
        itemMap.put("arrow26", scale2x(crop(cleanSheet, 42, 1470, 9, 90)));
        itemMap.put("arrow27", scale2x(crop(cleanSheet, 59, 1468, 9, 92)));
        itemMap.put("arrow28", scale2x(crop(cleanSheet, 76, 1465, 9, 95)));
        itemMap.put("arrow29", scale2x(crop(cleanSheet, 93, 1463, 9, 97)));
        itemMap.put("arrow30", scale2x(crop(cleanSheet, 110, 1461, 9, 99)));
        itemMap.put("arrow31", scale2x(crop(cleanSheet, 127, 1459, 9, 101)));
        itemMap.put("arrow32", scale2x(crop(cleanSheet, 144, 1456, 9, 104)));
        itemMap.put("arrow33", scale2x(crop(cleanSheet, 161, 1454, 9, 106)));
        itemMap.put("arrow34", scale2x(crop(cleanSheet, 178, 1452, 9, 108)));
        itemMap.put("arrow35", scale2x(crop(cleanSheet, 195, 1450, 9, 110)));
        itemMap.put("arrow36", scale2x(crop(cleanSheet, 212, 1447, 9, 113)));
        itemMap.put("arrow37", scale2x(crop(cleanSheet, 229, 1445, 9, 115)));
        itemMap.put("arrow38", scale2x(crop(cleanSheet, 246, 1443, 9, 117)));
        itemMap.put("arrow39", scale2x(crop(cleanSheet, 263, 1441, 9, 119)));
        itemMap.put("arrow40", scale2x(crop(cleanSheet, 280, 1438, 9, 122)));
        itemMap.put("arrow41", scale2x(crop(cleanSheet, 297, 1436, 9, 124)));
        itemMap.put("arrow42", scale2x(crop(cleanSheet, 314, 1434, 9, 126)));
        itemMap.put("arrow43", scale2x(crop(cleanSheet, 331, 1432, 9, 128)));
        itemMap.put("arrow44", scale2x(crop(cleanSheet, 348, 1430, 9, 131)));
        itemMap.put("arrow45", scale2x(crop(cleanSheet, 365, 1427, 9, 133)));
        itemMap.put("arrow46", scale2x(crop(cleanSheet, 382, 1425, 9, 135)));
        itemMap.put("arrow47", scale2x(crop(cleanSheet, 8, 1620, 9, 137)));
        itemMap.put("arrow48", scale2x(crop(cleanSheet, 25, 1617, 9, 140)));
        itemMap.put("arrow49", scale2x(crop(cleanSheet, 42, 1615, 9, 142)));
        itemMap.put("arrow50", scale2x(crop(cleanSheet, 59, 1613, 9, 144)));
        itemMap.put("arrow51", scale2x(crop(cleanSheet, 76, 1611, 9, 146)));
        itemMap.put("arrow52", scale2x(crop(cleanSheet, 93, 1608, 9, 149)));
        itemMap.put("arrow53", scale2x(crop(cleanSheet, 110, 1606, 9, 151)));
        itemMap.put("arrow54", scale2x(crop(cleanSheet, 127, 1604, 9, 153)));
        itemMap.put("arrow55", scale2x(crop(cleanSheet, 144, 1602, 9, 155)));
        itemMap.put("arrow56", scale2x(crop(cleanSheet, 161, 1599, 9, 158)));
        itemMap.put("arrow57", scale2x(crop(cleanSheet, 178, 1597, 9, 160)));
        itemMap.put("arrow58", scale2x(crop(cleanSheet, 195, 1595, 9, 162)));
        itemMap.put("arrow59", scale2x(crop(cleanSheet, 212, 1593, 9, 164)));
        itemMap.put("arrow60", scale2x(crop(cleanSheet, 229, 1590, 9, 167)));
        itemMap.put("arrow61", scale2x(crop(cleanSheet, 246, 1588, 9, 169)));
        itemMap.put("arrow62", scale2x(crop(cleanSheet, 263, 1586, 9, 171)));
        itemMap.put("arrow63", scale2x(crop(cleanSheet, 280, 1584, 9, 173)));
        itemMap.put("arrow64", scale2x(crop(cleanSheet, 297, 1581, 9, 176)));
        itemMap.put("arrow65", scale2x(crop(cleanSheet, 314, 1579, 9, 178)));
        itemMap.put("arrow66", scale2x(crop(cleanSheet, 331, 1577, 9, 180)));
        itemMap.put("arrow67", scale2x(crop(cleanSheet, 348, 1575, 9, 182)));
        itemMap.put("arrow68", scale2x(crop(cleanSheet, 365, 1572, 9, 185)));
        itemMap.put("arrow69", scale2x(crop(cleanSheet, 382, 1570, 9, 187)));
        itemMap.put("arrow70", scale2x(crop(cleanSheet, 399, 1568, 9, 189)));
	
       

 }


    public static BufferedImage scale2x(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage resized = new BufferedImage(w * 2, h * 2, img.getType());
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(img, 0, 0, w * 2, h * 2, null);
        g2d.dispose();
        return resized;
    }

	
	public static BufferedImage getItem(String key) {
		return itemMap.get(key);
	}
	
	
}
