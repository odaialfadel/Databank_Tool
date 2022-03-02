package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingUtilities;

import tool.controller.Controller;
import tool.model.Model;
import tool.model.formate.BetterExcelFormate;
import tool.view.View;

/*
 * @authur Odai Al Fadel
 * @Date 24.02.2022
 * @Version 1.0
 */

public class Applikation {

	public static void main(String[] args) {
		connect();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runApp();
				View view = new View();
				view.setVisible(true);
				
			}
		});

	}

	public static void runApp() {
		Model model = new Model();

		View view = new View(model);

		Controller controller = new Controller(model, view);
	}

	public static void connect() {
		
		BetterExcelFormate toExcel = new BetterExcelFormate();
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/odai", "root", "");
			String query = "SELECT SUM(anschaffungs_Preis) AS Summe, bezeichnung, lieferant_ID FROM inventar GROUP BY anschaffungs_Preis ORDER BY lieferant_ID ASC";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			toExcel.export(rs, "sumBetter","output//");
			
//			while(rs.next()) 
//	        {
//	            String f1 = rs.getString(1);
//				String f2=rs.getString(2);
//				System.out.println(f1+"  "+f2);
//	        } 
	        
			
			if (!con.isClosed())
				System.out.println("Successfully connected to MySQL server...");
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}

	}

}
