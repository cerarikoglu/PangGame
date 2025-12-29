package services;

import java.io.*;
import java.util.*;

public class UserManager {
	
	private static final String file = "logins.txt";
	
	public boolean userExists(String uname) {
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if(parts[0].equals(uname)) return true;
			}
		}
		catch(IOException e) {e.printStackTrace();}
		return false;
	}
	
	public boolean checkUser(String uname, String pw) {
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if(parts.length == 2) {
					String username = parts[0];
					String password = parts[1];
					if(username.equals(uname) && password.equals(pw)) return true;
					
				}
			}
		}
		catch(IOException e){ e.printStackTrace();}
		return false;
	}
	
	public void addUser(String uname, String pw) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
			bw.write(uname + ";" + pw);
			bw.newLine();
		}
		catch(IOException e) { e.printStackTrace();}
	}
}
