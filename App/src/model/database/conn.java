package model.database;

import java.sql.*;

public class Conn {


	public Connection c;  
	public Statement st; 
	
	public void Conn() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_java?useSSL=false&serverTimezone=UTC","root","");
			st = c.createStatement(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}