package view.admin;

import java.sql.*;

public class conn {


	public Connection c;  
	public Statement st; 
	
	public conn() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_java?useSSL=false&serverTimezone=UTC","root","");
			st = c.createStatement(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}