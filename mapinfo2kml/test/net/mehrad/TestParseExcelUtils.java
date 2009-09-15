package net.mehrad;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import net.mehrad.mapinfo2kml.util.ParseExcelUtils;


public class TestParseExcelUtils extends TestCase{

	public void testAll() {
		try {
			InputStream input=new FileInputStream("TestData//Qld_FederalResults by Electorate-2004.xls");
			List<ArrayList<String>> rows=ParseExcelUtils.genExcelRows(input);
			assertFalse(rows==null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}
}
