package net.codejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class JavaMySQLTest {
	
	public Connection connection;
	
	public JavaMySQLTest(){
		String url = "jdbc:mysql://localhost:3306/test1";
		String username = "root";
		String password = "password";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
	}
	
}















