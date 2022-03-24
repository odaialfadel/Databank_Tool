package tool.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JUnit_Config.class, JUnit_ExcelFormat.class, JUnit_MdelTool.class, JUnit_MySqlConnecton.class,
		JUnit_UtilitiesTool.class })
public class AllTests {

}
