package gui;
import javax.swing.*;

import java.awt.BorderLayout;
import java.io.*;
import java.util.*;

public class HistoryWindow extends JDialog {
	
	
	public HistoryWindow(JFrame parent,String user) {
		super(parent,"Game History",true);
		
		JTextArea textarea = new JTextArea(15,40);
		textarea.setEditable(false);
		
		try(BufferedReader br = new BufferedReader(new FileReader("game_history.txt"))){
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if(parts.length == 4 && parts[0].equals(user)) {
					String date = parts[1];
					String time = parts[2];
					String score = parts[3];
					textarea.append("Date: " + date + " | Time: " + time + " | Score: " + score+"\n");
					
				}
			}
		}
		catch(IOException e) {
			textarea.setText("No game history");
		}
		
		
		JScrollPane scroll = new JScrollPane(textarea);
		add(scroll,BorderLayout.CENTER);
		JButton close = new JButton("Close");
		close.addActionListener(e -> dispose());
		
		JPanel panel = new JPanel();
		panel.add(close);
		add(panel,BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}
	
	
}
