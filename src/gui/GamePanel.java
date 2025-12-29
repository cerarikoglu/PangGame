package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import GameObjects.Arrow;
import GameObjects.Bubble;
import javax.swing.*;
import asset.*;
import services.ScoreManager;
import GameObjects.Player;

public class GamePanel extends JFrame implements ActionListener{
	
	private boolean isLoggedIn = false;
	private String currentUser = null;
	private String selectedDiff = "Novice";
	private GameCanvas canvas;
	private Timer gameTimer; 
	
	private int remainingTime; 
	private Timer countdownTimer;

	private long lastHitTime = 0;

	private ArrayList<Bubble> bubbles = new ArrayList<>();
	private ArrayList<Arrow> arrows = new ArrayList<>();

	private BufferedImage playerLivesImage;
	private BufferedImage fgImage;
	private BufferedImage backgroundImage;
	
	private Image startGif;
	private boolean isGameStarted = false;
	private boolean isGameOver = false;
	
	private int currentLevel = 1;
	 private int playerLives;
	private Player player;
	
	private int score = 0;

	private Font arcadeFont;

	public GamePanel() {
		setTitle("Pang");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		
		canvas = new GameCanvas(this);
		canvas.setPreferredSize(new Dimension(800, 600));
	    add(canvas);
	    
        startGif = new ImageIcon(getClass().getResource("/assets/intro2.gif")).getImage();

		setMenuBar();
		setVisible(true);
		
		try {
		    InputStream is = getClass().getResourceAsStream("/PressStart2P-Regular.ttf");
		    if (is == null) {
		        throw new IOException("Font file not found!");
		    }
		    arcadeFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(arcadeFont);
		} catch (IOException | FontFormatException e) {
		    arcadeFont = new Font("Monospaced", Font.BOLD, 24); // fallback
		    e.printStackTrace();
		}


	}
	
	private void setMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu gameMenu = new JMenu("Game");
		JMenuItem register = new JMenuItem("Register");
		JMenuItem newGame = new JMenuItem("New");
		JMenuItem quit = new JMenuItem("Quit");
		
		register.addActionListener(this);
		newGame.addActionListener(this);
		quit.addActionListener(this);
		
		gameMenu.add(register);
		gameMenu.add(newGame);
		gameMenu.add(quit);
		
		
		JMenu optionsMenu = new JMenu("Options");
		JMenuItem history = new JMenuItem("History");
		JMenuItem highScore = new JMenuItem("High Score");
		optionsMenu.add(history);
		optionsMenu.add(highScore);
		history.addActionListener(this);
		highScore.addActionListener(this);
		
		JMenu diffLevel = new JMenu("Difficulty levels");
		
		JRadioButtonMenuItem novice = new JRadioButtonMenuItem("Novice");
		JRadioButtonMenuItem intermediate = new JRadioButtonMenuItem("Intermediate");
		JRadioButtonMenuItem advanced = new JRadioButtonMenuItem("Advanced");
	
		
		ButtonGroup diff = new ButtonGroup();
		diff.add(novice);
		diff.add(intermediate);
		diff.add(advanced);
		
		
		diffLevel.add(novice);
		diffLevel.add(intermediate);
		diffLevel.add(advanced);
		
		
		novice.addActionListener(e -> selectedDiff = "Novice");
		intermediate.addActionListener(e -> selectedDiff = "Intermediate");
		advanced.addActionListener(e -> selectedDiff = "Advanced");
		
		novice.setSelected(true);  
		optionsMenu.add(diffLevel);
		
		
		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(e -> JOptionPane.showMessageDialog(this,
				"Pang Game - Term Project\nDeveloper: [Ceren Nur Arıkoğlu]\n20230702026\ncerennur.arikoglu@std.yeditepe.edu.tr"
				,"About",JOptionPane.INFORMATION_MESSAGE));
		help.add(about);
		
		menuBar.add(gameMenu);
		menuBar.add(optionsMenu);
		menuBar.add(help);
		setJMenuBar(menuBar);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
		
			case "Register":
				LoginWindow LogWin = new LoginWindow(this);

				isLoggedIn = LogWin.isAuthenticated();
				currentUser = LogWin.getUser();
				break;
			
			case "New":
				if (!isLoggedIn) {
			        JOptionPane.showMessageDialog(this, "Before starting new game you should login first!");
			        LoginWindow loginWin = new LoginWindow(this);

			        if (loginWin.isAuthenticated()) {
			            isLoggedIn = true;
			            currentUser = loginWin.getUser();
			        } else {
			            JOptionPane.showMessageDialog(this, "Login Failed");
			            break;
			        }
			    }
				String[] levelsReversed = {"Level 4", "Level 3", "Level 2", "Level 1"};

			    int reversedChoice = JOptionPane.showOptionDialog(
			        this,
			        "Select a level to start",
			        "Level Selection",
			        JOptionPane.DEFAULT_OPTION,
			        JOptionPane.QUESTION_MESSAGE,
			        null,
			        levelsReversed,
			        "Level 1"
			    );

			    if (reversedChoice >= 0) {
			        int actualLevel = 4 - reversedChoice; 
			        setLevel(actualLevel);
			        gameStart();
			    }
			    break;
			
			case "History":
				if(isLoggedIn) {
					new HistoryWindow(this,currentUser);
				}
				else {
					JOptionPane.showMessageDialog(this, "Please log in first!");
				}
				break;
				
			case "High Score":
				new HighScoreWindow();
				break;
				
			case "Quit":
				System.exit(0);
		}
		
		
	}

	public void gameStart() {
	    isGameStarted = true;
	    isGameOver = false;
	    bubbles.clear();
	    arrows.clear();
	    score = 0;

	    if (gameTimer != null && gameTimer.isRunning()) gameTimer.stop();

	    int bubbleCount;
	    int playerSpeed;

	    switch (selectedDiff) {
	        case "Novice" -> {
	            bubbleCount = 1;
	            playerSpeed = 4;
	        }
	        case "Intermediate" -> {
	            bubbleCount = 3;
	            playerSpeed = 6;
	        }
	        case "Advanced" -> {
	            bubbleCount = 4;
	            playerSpeed = 8;
	        }
	        default -> {
	            bubbleCount = 1;
	            playerSpeed = 4;
	        }
	    }

	    for (int i = 0; i < bubbleCount; i++) {
	        int x = (int)(Math.random() * (canvas.getWidth() - BubbleAsset.getBubbleWidth("extraLarge")));
	        int y = 100;
	        bubbles.add(new Bubble(x, y, "extraLarge", selectedDiff));
	    }

	    int playerY = canvas.getHeight() - 100;
	    player = new Player(200, playerY);
	    player.setSpeed(playerSpeed);

	    gameTimer = new Timer(20, e -> {
	        if (!isGameOver) {
	            if (player != null) {
	                boolean[][] fgCollisionMap = FGroundAsset.getCollisionMap("level" + currentLevel + "FgImage");
	                player.move(fgCollisionMap);
	            }

	            for (Bubble bubble : bubbles) {
	                bubble.move(canvas.getWidth(), canvas.getHeight());

	                if (bubble.getBounds().intersects(player.getBounds())) {
	                    long now = System.currentTimeMillis();
	                    if (now - lastHitTime >= 1000) {
	                        playerLives--;
	                        lastHitTime = now;

	                        if (playerLives <= 0) {
	                            isGameOver = true;
	                            countdownTimer.stop();
	                            JOptionPane.showMessageDialog(this, "You lost all your lives!");
	                            ScoreManager.logGame(currentUser, score);
	                            ScoreManager.updateHighScore(currentUser, score);
	                        }
	                    }
	                    break;
	                }
	            }

	            ArrayList<Arrow> toRemove = new ArrayList<>();
	            ArrayList<Bubble> newBubbles = new ArrayList<>();

	            for (Arrow arrow : arrows) {
	                arrow.update();

	                if (arrow.isOutOfBounds()) {
	                    toRemove.add(arrow);
	                    continue;
	                }

	                for (Bubble bubble : bubbles) {
	                    if (arrow.getBounds().intersects(bubble.getBounds())) {
	                        toRemove.add(arrow);
	                        bubbles.remove(bubble);
	                        newBubbles.addAll(bubble.split());
	                        score += 10;
	                        break;
	                    }
	                }
	            }

	            arrows.removeAll(toRemove);
	            bubbles.addAll(newBubbles);
	        }
	        if (bubbles.isEmpty()) {
	            isGameOver = true;
	            gameTimer.stop();
	            countdownTimer.stop();
	            ScoreManager.updateHighScore(currentUser, score);
	            ScoreManager.logGame(currentUser, score);
	            JOptionPane.showMessageDialog(this, "You cleared all bubbles! Your score: " + score);
	        }

	        canvas.repaint();
	    });
	    gameTimer.start();

	    switch (currentLevel) {
	        case 1 -> remainingTime = 90;
	        case 2 -> remainingTime = 100;
	        case 3 -> remainingTime = 110;
	        case 4 -> remainingTime = 120;
	        default -> remainingTime = 90;
	    }

	    if (countdownTimer != null && countdownTimer.isRunning()) countdownTimer.stop();

	    countdownTimer = new Timer(1000, e -> {
	        remainingTime--;
	        if (remainingTime <= 0) {
	            countdownTimer.stop();
	            isGameOver = true;
	            JOptionPane.showMessageDialog(this, "Time's up!");
	            ScoreManager.logGame(currentUser, score);
	            ScoreManager.updateHighScore(currentUser, score);
	        }
	        canvas.repaint();
	    });
	    countdownTimer.start();

	    switch (selectedDiff) {
	        case "Advanced" -> playerLives = 2;
	        case "Intermediate" -> playerLives = 1;
	        default -> playerLives = 3;
	    }
	}




	public void setLevel(int level) {
	    this.currentLevel = level;
	    this.backgroundImage = BGroundAsset.getBgImage("level" + level + "Image");
	    String key = "level" + level + "FgImage";
	    FGroundAsset.loadFgLevel(key, getForegroundX(level), getForegroundY(level));
	    this.fgImage = FGroundAsset.getFgImage(key);
	    this.playerLivesImage = PlayerAsset.getPlayer("playerLivesImage");
	}


	public void drawGame(Graphics g, Component observer) {
	    if (!isGameStarted && startGif != null) {
	        g.drawImage(startGif, 0, 0, canvas.getWidth(), canvas.getHeight(), observer);
	        return;
	    }

	    if (isGameStarted && backgroundImage != null) {
	        g.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight() - 100, observer);
	    }

	    if (fgImage != null) {
	        g.drawImage(fgImage, 0, 0, canvas.getWidth(), canvas.getHeight() - 100, observer);
	    }

	    int infoBarHeight = 100;
	    g.setColor(Color.BLACK);
	    g.fillRect(0, canvas.getHeight() - infoBarHeight, canvas.getWidth(), infoBarHeight);

	    int lifeIconSize = 48;
	    int lifeY = canvas.getHeight() - infoBarHeight + (infoBarHeight - lifeIconSize) / 2;

	    for (int i = 0; i < 4; i++) {
	        g.drawImage(playerLivesImage, 10 + i * (lifeIconSize + 5), lifeY, lifeIconSize, lifeIconSize, observer);
	    }
	    g.setColor(Color.WHITE);
	    g.setFont(arcadeFont.deriveFont(28f));
	    int livesTextX = 10 + 4 * (lifeIconSize + 5) + 10;
	    int livesTextY = canvas.getHeight() - infoBarHeight / 2 + 10;
	    g.drawString(" " + playerLives, livesTextX, livesTextY);

	    if (player != null) {
	        g.drawImage(player.getImage(), player.getX(), player.getY(), observer);
	    }

	    for (Bubble bubble : bubbles) {
	        g.drawImage(bubble.getImage(), bubble.getBounds().x, bubble.getBounds().y - 60, observer);
	    }

	    for (Arrow arrow : arrows) {
	        g.drawImage(arrow.getImage(), arrow.getX(), arrow.getY() - 60, observer);
	    }

	    String timeStr = "TIME:" + remainingTime;
	    Font timeFont = arcadeFont.deriveFont(24f);
	    g.setFont(timeFont);
	    FontMetrics fm = g.getFontMetrics(timeFont);
	    int textWidth = fm.stringWidth(timeStr);
	    int timeX = canvas.getWidth() - textWidth - 20;
	    int timeY = 50;

	    g.setColor(Color.BLACK);
	    g.drawString(timeStr, timeX + 1, timeY + 3);
	    g.setColor(Color.WHITE);
	    g.drawString(timeStr, timeX, timeY);

	    g.setFont(arcadeFont.deriveFont(24f));
	    g.setColor(Color.WHITE);
	    g.drawString("SCORE: " + score, 20, 30);
	}

	
	
	private int getForegroundX(int level) {
	    return switch (level) {
	        case 1 -> 8;
	        case 2 -> 400;
	        case 3 -> 400;
	        case 4 -> 400;
	        default -> 8;
	    };
	}

	private int getForegroundY(int level) {
	    return switch (level) {
	        case 1 -> 44;
	        case 2 -> 260;
	        case 3 -> 908;
	        case 4 -> 1556;
	        default -> 44;
	    };
	}
	
	public Player getPlayer() {return player;}
	public void addArrow(Arrow arrow) {
	    arrows.add(arrow);
	}

}











































