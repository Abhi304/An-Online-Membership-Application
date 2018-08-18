package database;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        getConnection();
	}
	public static Connection getConnection() throws Exception{
		try{
		
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://sql1.njit.edu/avs43","avs43","DaicVvRt");
		//JOptionPane.showMessageDialog(null, "Connection Successful");
		System.out.println("Connected");
		return conn;
		} catch(Exception e){System.out.println(e);} 
		return null;
		}
}

