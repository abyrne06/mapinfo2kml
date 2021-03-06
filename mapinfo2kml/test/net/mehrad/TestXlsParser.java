package net.mehrad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.mehrad.mapinfo2kml.parser.XlsParser;
import net.mehrad.mapinfo2kml.util.ParseExcelUtils;
import net.mehrad.mapinfo2kml.xls.XlsModel;

import org.junit.Test;

public class TestXlsParser extends TestCase{

	
	/**
	 * Test method for {@link net.mehrad.mapinfo2kml.Parser#parse()}.
	 */
	@Test
	public void testParse() {
		try {
			InputStream input=new FileInputStream("TestData//Qld_FederalResults by Electorate-2004.xls");
			List<ArrayList<String>> rows=ParseExcelUtils.genExcelRows(input);
			assertFalse(rows==null);
			XlsParser xlsParser=new XlsParser(rows);
			XlsModel model=(XlsModel) xlsParser.parse();
			assertFalse(model==null);
			assertFalse(model.getColheader()==null);
			assertFalse(model.getElectionResultRows()==null);
			assertFalse(model.getRowStr("blair").isEmpty());
			assertFalse(!model.getRowStr("NavidShariat").isEmpty());
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