package tool.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
	
	public static Connection conn;
	private static Statement stmt;

	public static void connect() {
		conn = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/odai", "root", "");
			System.out.println("Verbindung zu Datenbank hergestellt.");
			
			stmt = conn.createStatement();
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	public static void disconnect() {
		try {
			if(conn != null) {
				conn.close();
				System.out.println("Verbindung zu Datenbank getrennt.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//beispiel String query = "SELECT SUM(anschaffungs_Preis) AS Summe, bezeichnung, lieferant_ID FROM inventar GROUP BY anschaffungs_Preis ORDER BY lieferant_ID ASC";
	public static ResultSet onQuery(String sql) {
		try {
			return stmt.executeQuery(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
		
	
}
