package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import GameObjects.Player;
import GameObjects.Arrow;
import GameObjects.Bubble;
import java.util.List;

public class GameCanvas extends JPanel {
    private GamePanel gamePanel;

    public GameCanvas(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setFocusable(true);
        
        addKeyListener(new KeyAdapter()
        {
        	public void keyPressed(KeyEvent e) {
        		if(gamePanel.getPlayer() == null) return;
        		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
        			gamePanel.getPlayer().setMovingLeft(true);
        			}
        		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
        			gamePanel.getPlayer().setMovingRight(true);
        		}
        		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Player p = gamePanel.getPlayer();
                    gamePanel.addArrow(new Arrow(p.getX() + p.getImage().getWidth() / 2, getHeight() - 100, getHeight() - 100));
                }
        	}
        	
        	public void keyReleased(KeyEvent e) {
        		if(gamePanel.getPlayer() == null) return;
        		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
        			gamePanel.getPlayer().setMovingLeft(false);
        		}
        		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
        			gamePanel.getPlayer().setMovingRight(false);
        		}
        	}	
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gamePanel.drawGame(g, this); 
    }
      
}

