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
	public static ResultSet execQuery(String sql) throws SQLException {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
	}

	public static Connection connect(ConnectionData connectionData) throws SQLException {

			conn = DriverManager.getConnection(
					"jdbc:mysql://" + connectionData.getServiceName() + ":" + connectionData.getPort() + "/"
							+ connectionData.getDatenbank(),
					connectionData.getUsername(), connectionData.getPasswort());
		return conn;
	}

	public static void desconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
