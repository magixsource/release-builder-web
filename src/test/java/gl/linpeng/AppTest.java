package gl.linpeng;

import gl.linpeng.tools.builder.module.LocalStorageModule;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		// assertTrue( true );
		String text;
		try {
			text = org.apache.commons.io.IOUtils.toString(ClassLoader
					.getSystemResourceAsStream("modules.json"));
			assertTrue(text != null);
			
			List<LocalStorageModule> list = JSON.parseArray(text, LocalStorageModule.class);
			
			assertTrue(list !=null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
