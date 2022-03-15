package tool.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tool.model.ModelTool;
import tool.model.connection.ConnectionData;

public class JUnit_MdelTool {

	/**
	 * Test all functions in Modeltool class
	 */

	// test set and getConnetionDataList
	private ModelTool model = new ModelTool();

	@Test
	public void test_setConnectionData() {

		model.setConnectionData("odai", new ConnectionData("odai", "root", "1234", "serv", "125"));
		// System.err.println(model.getConnectionDataList().get("5445").getPasswort());
		assertTrue(model.getConnectionDataList().get("odai").getDatenbank() != null);
		assertFalse(model.getConnectionDataList().get("odai").getDatenbank() == null);
		assertEquals(model.getConnectionDataList().get("odai").getPasswort(), "1234");
	}

}
