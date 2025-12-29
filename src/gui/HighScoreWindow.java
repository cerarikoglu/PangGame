package gui;

import services.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;

public class HighScoreWindow extends JFrame {
	
    public HighScoreWindow() {
        setTitle("High Scores");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ScoreManager.reloadScores(); // Refresh from file
        Map<String, Integer> scores = ScoreManager.getAllScores();

        List<Entry<String, Integer>> sorted = new ArrayList<>(scores.entrySet());
        sorted.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.BOLD, 16));
        textArea.setEditable(false);
        textArea.append(String.format("%-20s %s\n", "Username", "High Score"));
        textArea.append("------------------------------\n");
        for (Entry<String, Integer> entry : sorted) {
            textArea.append(String.format("%-20s %d\n", entry.getKey(), entry.getValue()));
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        setVisible(true);
    }
}
