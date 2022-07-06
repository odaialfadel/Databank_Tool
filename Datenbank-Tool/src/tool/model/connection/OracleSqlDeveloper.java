package tool.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleSqlDeveloper {
	private static Connection conn;


	/**
	 * Verbindung aufbauen, statement ausfueren und speichern, Verbindung Abbrechen
	 * 
	 * @return ResultSet
	 * @throws SQLException
	 */
	public static ResultSet execQuery(String sql) {

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}

	/*
	 * Verbindung herstellen
	 */
	public static Connection connect(ConnectionData connectionData) {
		try {
			// Diese Klasse muss aufgerufen werden, um den OJDBC-Treiber zu aktivieren
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@//localhost:" + connectionData.getPort() + "/" + connectionData.getServiceName(),
					connectionData.getUsername(), connectionData.getPasswort());
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Oracle connection Faild!");
			return null;
		}

		
	}

	/*
	 * Verbindung Abbrechen
	 */
	public static void desconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

