package tool.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleSqlDeveloper {
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

	public static Connection connect(ConnectionData connectionData) {



		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@//localhost:" + connectionData.getPort() + "/" + connectionData.getServiceName(),
					connectionData.getUsername(), connectionData.getPasswort());
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Oracle connection Faild!");
		}

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
