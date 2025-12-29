package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import services.UserManager;

public class LoginWindow extends JDialog implements ActionListener{
	
	private boolean authenticated = false;
	private String currentUser = null;
	
	public boolean isAuthenticated() {
		return authenticated;
	}
	public String getUser() {
		return currentUser;
	}
	
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton helpButton;
	
	public LoginWindow(JFrame parent) {
		super(parent,"Login",true);
		setLayout(new GridLayout(3,3,5,5));
		
		JLabel userlabel = new JLabel("Username");
		add(userlabel);
		userNameField = new JTextField();
		add(userNameField);
		
		JLabel passWlabel = new JLabel("Password");
		add(passWlabel);
		passwordField = new JPasswordField();
		add(passwordField);
		
		helpButton = new JButton("Help");
		add(helpButton);
		
		loginButton = new JButton("Login");
		add(loginButton);
		
	
		loginButton.addActionListener(this);
		helpButton.addActionListener(this);
		
		setSize(300,150);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		UserManager um = new UserManager();
		if(e.getSource() == loginButton ) {
			String username = userNameField.getText();
			String password = new String(passwordField.getPassword());
			if(um.userExists(username)) {
				if(um.checkUser(username, password)) {
					authenticated = true;
					currentUser = username;
					JOptionPane.showMessageDialog(this, "Login successful!");
			        dispose();
				}
				else {JOptionPane.showMessageDialog(this, "Invalid password!", "Error", JOptionPane.ERROR_MESSAGE);}
			}
			else {
				um.addUser(username, password);
				authenticated = true;
				currentUser = username;
				JOptionPane.showMessageDialog(this, "New user registered successfully!");
				dispose();
			}
		}
		else if(e.getSource() == helpButton) {
			JOptionPane.showMessageDialog(this, "You have to login before initiating the game.","Yardım" ,JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
	

}
