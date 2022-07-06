package tool.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.ResultSet;

import org.junit.Test;

import tool.model.UtilitiesTool;
import tool.model.connection.ConnectionData;
import tool.model.connection.MySQLConnection;
import tool.model.formate.ExcelFormat;

public class JUnit_ExcelFormat {

	ExcelFormat toExcel = new ExcelFormat();

	// Test my remove Extension function
	@Test
	public void test_export() {

		MySQLConnection.connect(new ConnectionData("odai", "root", "odai123", "", "3306"));
		ResultSet rs = MySQLConnection.execQuery(
				"SELECT MAX(Anschaffungs_preis) as hochstPreis, bezeichnung, Nutzungsdauer_Jahre FROM inventar WHERE Anschaffungs_preis > 80 AND Anschaffungs_preis < 91000 Order by Anschaffungs_preis DESC");

		toExcel.export(rs, "JUnitTest", "output\\");
		boolean found = false;
		File file = new File("output\\JUnitTest_" + UtilitiesTool.formatDate() + ".xlsx");

		if (file.exists()) {
			found = true;
		}
		assertTrue(found);
	}

}
