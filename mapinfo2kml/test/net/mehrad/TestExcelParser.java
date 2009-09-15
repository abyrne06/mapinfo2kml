package net.mehrad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.mehrad.mapinfo2kml.Translator;
import net.mehrad.mapinfo2kml.parser.XlsParser;
import net.mehrad.mapinfo2kml.util.ParseExcelUtils;
import net.mehrad.mapinfo2kml.util.ParseStringUtils;
import net.mehrad.mapinfo2kml.xls.XlsModel;

import org.boehn.kmlframework.kml.Kml;

public class TestExcelParser extends TestCase{

	public static void testParseXls(String[] args)
	{
		try {
			FileInputStream fi=new FileInputStream("TestData//Qld_FederalResults by Electorate-2004.xls");
			List<ArrayList<String>> excelLines=ParseExcelUtils.genExcelRows(fi);
			Translator translator = new Translator(ParseStringUtils.getReadedLines(new FileInputStream(new File("TestData//QLD_Federal_Electoral_Boundaries.mid"))), ParseStringUtils.getReadedLines(new FileInputStream(new File("testData//QLD_Federal_Electoral_Boundaries.mif"))),(List)excelLines);
			Kml result = translator.translate();
			result.createKml("TestResults//QLD_Federal_Electoral_Boundaries.kml");

			List<ArrayList<String>> list=new ParseExcelUtils().genExcelRows(fi);
			XlsParser xlsP=new XlsParser((List)list);
			XlsModel model=(XlsModel) xlsP.parse();
			List<String> cHeader=model.getColheader();
			String lineStr=model.getRowStr("Wills");
			lineStr+="";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
