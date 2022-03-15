package tool.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {

	private static Connection conn;

	// TODO Singelton for the database

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

	public static Connection connect(ConnectionData connectionData) {

		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + connectionData.getServiceName() + ":" + connectionData.getPort() + "/"
							+ connectionData.getDatenbank(),
					connectionData.getUsername(), connectionData.getPasswort());
			return conn;
		} catch (SQLException e) {
			return null;
		}

	}

	public static void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
