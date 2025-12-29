package gui;

import asset.*;
import services.ScoreManager;

public class Main {
    public static void main(String[] args) {
    	
    	new BGroundAsset().load();
    	new BubbleAsset().load();
    	new ItemsAsset().load();
    	new PlayerAsset().load();
    	new FGroundAsset().load();
    	new ItemsAsset().load();
    	ScoreManager.loadScores();

    	
        javax.swing.SwingUtilities.invokeLater(() -> {
        	new GamePanel();
        });
    }
}
