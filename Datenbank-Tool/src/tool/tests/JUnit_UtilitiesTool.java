
package tool.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.ImageIcon;

import org.junit.Test;

import tool.model.UtilitiesTool;

public class JUnit_UtilitiesTool {

	// Teste die removeFileExtension, die de eErweiterung von der Datei loescht
	@Test
	public void test_removeFileExtension() {
		String newString = UtilitiesTool.removeFileExtension("test/folder/file.sql.text");
		String newString2 = UtilitiesTool.removeFileExtension("test/folder/file.sql");
		
		assertEquals("test/folder/file.sql", newString);
		assertEquals("test/folder/file", newString2);
	}

	// Teste formatDate Funktion, die das heutige Datum richtig formatiert
	@Test
	public void test_formatDate() {
		String newString = UtilitiesTool.formatDate();
		assertFalse("2022.03.18".equals(newString));
		assertTrue("20220318".equals(newString));
		assertEquals("20220318", newString);
	}

	// Teste readFile Funktion, die eine bestimmte Datei ausliest und als String
	// zurueckgibt
	@Test
	public void test_readFile() {
		String newString = UtilitiesTool
				.readFile("D:\\FC_9999.sql");
		assertEquals(
				"SELECT * FROM inventar;",
				newString);
	}

	// Teste cleanQuery Funktion, die die semiqulon von der SQL-Abfrage loescht
	@Test
	public void test_cleanQuery() {
		String newString = UtilitiesTool.cleanQuery("SELECT * FROM inventar;");
		String newString1 = UtilitiesTool.cleanQuery("SELECT * FROM inventar;;;;;;;;;");
		assertEquals("SELECT * FROM inventar", newString);
		assertEquals("SELECT * FROM inventar", newString1);
	}

	// Teste setIcon Funktion, die ein bestimmtes Foto uebergeben bekommt, liest und
	// dieses zurueckgibt
	@Test
	public void test_setIcon() {
		ImageIcon newImg = UtilitiesTool.setIcon("home3.jpg");
		ImageIcon newImgTest = new ImageIcon("src\\tool\\img\\home3.jpg");
		assertEquals(newImgTest.getImage(), newImg.getImage());
	}
}













