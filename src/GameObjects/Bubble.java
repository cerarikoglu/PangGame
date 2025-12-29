package GameObjects;

import asset.BubbleAsset;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
public class Bubble {
    private Rectangle bounds;
    private double x, y;
    private double vx, vy;
    private final double GRAVITY = 0.4;
    private BufferedImage image;
    private String sizeLevel;

    public Bubble(int x0, int y0, String sizeLevel, String difficulty) {
        this.sizeLevel = sizeLevel;
        this.image = BubbleAsset.getBubble(sizeLevel);
        int w = image.getWidth();
        int h = image.getHeight();

        this.x = x0;
        this.y = y0;
        this.bounds = new Rectangle(x0, y0, w, h);

        double speed;
        switch (difficulty) {
            case "Novice" -> speed = 5;
            case "Intermediate" -> speed = 8;
            case "Advanced" -> speed = 10;
            default -> speed = 5;
        }

        double angleDeg = 60;
        double angleRad = Math.toRadians(angleDeg);
        this.vx = (Math.random() < 0.5 ? -1 : 1) * speed * Math.cos(angleRad);
        this.vy = -speed * Math.sin(angleRad);
    }

    public void move(int panelWidth, int panelHeight) {
        vy += GRAVITY;
        x += vx;
        y += vy;

        int groundY = panelHeight - 40;

        if (y + bounds.height >= groundY) {
            y = groundY - bounds.height;
            vy = -Math.abs(vy);
        }

        if (x <= 0 || x + bounds.width >= panelWidth) {
            vx *= -1;
        }

        bounds.setBounds((int) x, (int) y, image.getWidth(), image.getHeight());
    }

    public BufferedImage getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isSmallest() {
        return "small".equals(sizeLevel);
    }

    public List<Bubble> split() {
        List<Bubble> parts = new ArrayList<>();
        String nextSize = switch (sizeLevel) {
            case "extraLarge" -> "large";
            case "large" -> "medium";
            case "medium" -> "small";
            default -> null;
        };

        if (nextSize != null) {
            parts.add(new Bubble((int) x, (int) y, nextSize, "Novice"));
            parts.add(new Bubble((int) x + bounds.width / 2, (int) y, nextSize, "Novice"));
        }

        return parts;
    }
}