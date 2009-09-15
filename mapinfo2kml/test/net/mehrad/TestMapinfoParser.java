package net.mehrad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.*;
import net.mehrad.mapinfo2kml.exception.ParserException;
import net.mehrad.mapinfo2kml.parser.*;
import net.mehrad.mapinfo2kml.util.ParseStringUtils;
import net.mehrad.mapinfo2kml.mif.*;

public class TestMapinfoParser {
	/**
	 * Test method for {@link net.mehrad.mapinfo2kml.Parser#parse()}.
	 */
	@Test
	public void testParse() {
		try {
			Parser p = new MapinfoParser(ParseStringUtils.getReadedLines(new FileInputStream(new File("testData/QLD_Federal_Electoral_Boundaries.mid"))), ParseStringUtils.getReadedLines(new FileInputStream(new File("testData/QLD_Federal_Electoral_Boundaries.mif"))));
			
			MifModel model = (MifModel)p.parse();
			
			assertEquals(model.getColumns().size(), 9);
			assertEquals("WindowsLatin1", model.getCharset());
			assertEquals(",", model.getDelimiter());
			assertEquals("450", model.getVersion());
			assertEquals(28, model.getMifDatas().size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		try {
			Parser p = new MapinfoParser(ParseStringUtils.getReadedLines(new FileInputStream(new File("testData/testfail.mid"))), ParseStringUtils.getReadedLines(new FileInputStream(new File("testData/goh.mif"))));
			p.parse();
			
			Parser p2 = new MapinfoParser(ParseStringUtils.getReadedLines(new FileInputStream(new File("testData/testfail.mid"))), ParseStringUtils.getReadedLines(new FileInputStream(new File("testData/testfail.mif"))));
			p2.parse();
			
			fail();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			
		}
		
		
	}
}
