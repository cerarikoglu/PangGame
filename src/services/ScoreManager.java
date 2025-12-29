package services;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


public class ScoreManager {
	 private static final String SCORE_FILE = "game_history.txt";
	 private static Map<String, Integer> scoreMap = new HashMap<>();
	
	 
	
	 public static void loadScores() {
	     try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
	         String line;
	         while ((line = reader.readLine()) != null) {
	             String[] parts = line.split(";");
	             if (parts.length == 4) {
	                 String username = parts[0];
	                 int score = Integer.parseInt(parts[3]);
	                 int current = scoreMap.getOrDefault(username, 0);
	                 if (score > current) {
	                     scoreMap.put(username, score);
	                 }
	             }
	         }
	     } catch (IOException e) {
	         System.out.println("History file not found");
	     }
	 }
	
	 public static void updateHighScore(String username, int newScore) {
	     int current = scoreMap.getOrDefault(username, 0);
	     if (newScore > current) {
	         scoreMap.put(username, newScore);
	     }
	 }
	
	 public static int getHighScore(String username) {
	     return scoreMap.getOrDefault(username, 0);
	 }
	
	 public static Map<String, Integer> getAllScores() {
	     return new HashMap<>(scoreMap);
	 }
	
	 public static void reloadScores() {
	     scoreMap.clear();
	     loadScores();
	 }
	   
	 public static void logGame(String username, int score) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE, true))) {
	            String date = LocalDate.now().toString();
	            String time = LocalTime.now().withNano(0).toString();
	            writer.write(username + ";" + date + ";" + time + ";" + score);
	            writer.newLine();
	        } catch (IOException e) {
	            System.err.println("Error writing to game history: " + e.getMessage());
	        }
	    }
	 
}
