package net.mehrad;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests extends TestSuite{

	public static Test suite() {
		//$JUnit-BEGIN$
		Class[] testClasses={TestMapinfoParser.class, 
				TestParseExcelUtils.class, TestParseStringUtils.class, 
				TestTranslator.class, TestXlsParser.class};
		TestSuite suite = new TestSuite(testClasses);
		//$JUnit-END$
		return suite;
	}

}
