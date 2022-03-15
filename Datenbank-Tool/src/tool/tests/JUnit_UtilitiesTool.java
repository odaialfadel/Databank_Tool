package tool.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.swing.ImageIcon;

import org.junit.Test;

import tool.model.UtilitiesTool;

public class JUnit_UtilitiesTool {

	/**
	 * UtilitiesTool class test all funktions
	 */

	// Test my remove Extension function
	@Test
	public void test_removeFileExtension() {
		String newString = UtilitiesTool.removeFileExtension("test/love/haha/text.sql.text");
		assertEquals("test/love/haha/text.sql", newString);
	}

	// Test my remove Extension function
	@Test
	public void test_formatDate() {
		String newString = UtilitiesTool.formatDate();
		assertFalse("2022.03.15".equals(newString));
		assertEquals("20220315", newString);
	}

	// Test my remove Extension function
	@Test
	public void test_readFile() {
		String newString = UtilitiesTool
				.readFile("C:\\Users\\odaia\\OneDrive\\Desktop\\Abschluss_Projekt\\test_Statements\\odai1.txt");
		assertEquals(
				"SELECT MAX(Anschaffungs_preis) as hochstPreis, bezeichnung, Nutzungsdauer_Jahre FROM inventar WHERE Anschaffungs_preis > 80 AND Anschaffungs_preis < 91000 Order by Anschaffungs_preis DESC",
				newString);
	}

	// Test my remove Extension function
	@Test
	public void test_cleanQuery() {
		String newString = UtilitiesTool.cleanQuery("ewfwefwefwefwfwefwef;");
		String newString1 = UtilitiesTool.cleanQuery("ewfwefwefwefwfwefwef");
		assertEquals("ewfwefwefwefwfwefwef", newString);
		assertEquals("ewfwefwefwefwfwefwef", newString1);
	}

	// Test if it creates the right object
	@Test
	public void test_setIcon() {
		ImageIcon newImg = UtilitiesTool.setIcon("home3.jpg");
		ImageIcon newImgTest = new ImageIcon("src\\tool\\img\\home3.jpg");
		assertEquals(newImgTest.getImage(), newImg.getImage());
	}
}
