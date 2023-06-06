package view.admin;

/*
CLASS Creation Steps:
1.conn
2.welcome_page
3.login_page
4.details_page
5.add_employee
6.view_employee
7.print_data
8.remove_employee
9.search_employee
10.update_employee
*/

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