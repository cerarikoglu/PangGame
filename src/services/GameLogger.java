package services;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class GameLogger {
 private static final String HISTORY_FILE = "game_history.txt";

 public static void logGame(String username, int score) {
     LocalDateTime now = LocalDateTime.now();
     String date = now.toLocalDate().toString(); 
     String time = now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

     String line = String.format("%s;%s;%s;%d", username, date, time, score);

     try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE, true))) {
         writer.write(line);
         writer.newLine();
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
