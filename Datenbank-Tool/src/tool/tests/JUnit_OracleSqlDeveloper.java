
package tool.tests;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.Test;

import tool.model.connection.ConnectionData;
import tool.model.connection.MySQLConnection;

public class JUnit_OracleSqlDeveloper {



	// test if there is connection
	@Test
	public void test_connect() {
		Connection conn = MySQLConnection.connect(new ConnectionData("odai", "root", "odai123", "", "3306"));

		boolean connected = false;
		if (conn != null) {
			connected = true;
		}
		assertTrue(connected);
	}

	// test if the connection disconnected
	@Test
	public void test_disconnect() {
		Connection conn = MySQLConnection.connect(new ConnectionData("odai", "root", "odai123", "", "3306"));
		MySQLConnection.disconnect();

		boolean disconnected = false;
		if (conn != null) {
			disconnected = true;
		}
		assertTrue(disconnected);
	}

	// test if the execQuery return ResaultSet
	@Test
	public void test_execQuery() {
		// connection created
		MySQLConnection.connect(new ConnectionData("test", "root", "odai123", "", "3306"));

		// ResultSet created
		ResultSet rs = MySQLConnection.execQuery(
				"SELECT MAX(Anschaffungs_preis) as hochstPreis, bezeichnung, Nutzungsdauer_Jahre FROM inventar "
						+ " WHERE Anschaffungs_preis > 80 AND Anschaffungs_preis < 91000 Order by Anschaffungs_preis DESC");
		
		boolean executed = false;
		if (rs != null) {
			executed = true;
		}
		assertTrue(executed);
	}

}



