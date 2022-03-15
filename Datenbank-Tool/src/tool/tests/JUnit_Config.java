package tool.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tool.model.connection.Config;
import tool.model.connection.ConnectionData;

public class JUnit_Config {

	/**
	 * Test all functions in Config class
	 */

	Config config = new Config();

	// test createConfig function
	@Test
	public void test_createConfig() {

		// added new Config
		config.createConfig("newConfig", new ConnectionData("odai", "root", "1234", "serv", "125"));
		ConnectionData conDa = config.getAllConfig().get("newCongif");
		boolean found = false;
		if (conDa != null) {
			found = true;
		}
		assertFalse(found);
	}

	// test getValueByName function
	@Test
	public void test_getValueByName() {
		config.createConfig("newConfig", new ConnectionData("odai", "root", "1234", "serv", "125"));
		// added new Config

		ConnectionData conDa = config.getValueByName("newConfig");
		boolean found = false;
		if (conDa != null) {
			found = true;
		}
		assertTrue(found);
	}

	// test getKeyByConfig function
	@Test
	public void test_getKeyByConfig() {
		config.createConfig("newConfig", new ConnectionData("odai", "root", "1234", "serv", "125"));
		// added new Config

		ConnectionData conDa = config.getAllConfig().get("newConfig");
		String key = config.getKeyByConfig(conDa);
		assertEquals(config.getAllConfig().get(key), key);
	}

	// test deleteConfig function
	@Test
	public void test_deleteConfig() {
		// added new Config
		config.createConfig("newConfig", new ConnectionData("odai", "root", "1234", "serv", "125"));
		ConnectionData conDa = config.getAllConfig().get("newCongif");
		// Config deleted
		config.deleteConfig("newConfig");
		boolean found = true;
		if (conDa == null) {
			found = false;
		}
		assertFalse(found);
		}
}
