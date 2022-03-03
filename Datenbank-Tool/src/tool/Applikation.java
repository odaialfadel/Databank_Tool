package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingUtilities;

import tool.controller.Controller;
import tool.model.Model;
import tool.model.connection.SQLConnection;
import tool.model.formate.BetterExcelFormate;
import tool.view.View;

/*
 * @authur Odai Al Fadel
 * @Date 24.02.2022
 * @Version 1.0
 */

public class Applikation {

	public static void main(String[] args) {
		SQLConnection.connect();
		SQLConnection.onQuery("SELECT SUM(anschaffungs_Preis) AS Summe, bezeichnung, lieferant_ID FROM inventar GROUP BY anschaffungs_Preis ORDER BY lieferant_ID ASC");
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runApp();
				View view = new View();
				view.setVisible(true);
				
			}
		});

	}

	public static void runApp() {
	//	Model model = new Model();

	//	View view = new View(model);

	//	Controller controller = new Controller(model, view);
	}

}
