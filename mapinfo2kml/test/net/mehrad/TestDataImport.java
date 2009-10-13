package net.mehrad;

import java.io.FileInputStream;

import junit.framework.TestCase;
import net.mehrad.mapinfo2kml.datamanager.imp.FederalElectionResultsImporter;
import net.mehrad.mapinfo2kml.datamanager.imp.FederalResults2ppByElectorateImporter;
import net.mehrad.mapinfo2kml.datamanager.imp.FederalStateElectorateMappingImporter;
import net.mehrad.mapinfo2kml.datamanager.imp.StateResultsByElectorateImporter;

import org.junit.Test;

public class TestDataImport extends TestCase{

	@Test
	public void testDataImport()
	{

		StateResultsByElectorateImporter electorateMappingImporter = new StateResultsByElectorateImporter();
		FederalElectionResultsImporter electionResultsImporter=new FederalElectionResultsImporter();
		FederalResults2ppByElectorateImporter byElectorateImporter=new FederalResults2ppByElectorateImporter();
		FederalStateElectorateMappingImporter electorateMappingImporter2=new FederalStateElectorateMappingImporter();
		
		
		
		try {
			electorateMappingImporter.doImport(new FileInputStream("TestData//Election//Qld_State Results by Electorate-2006.xls"));
			electionResultsImporter.doImport(new FileInputStream("TestData//Election//Federal Election Results-Qld-2004.xls"));
			byElectorateImporter.doImport(new FileInputStream("TestData//Election//Qld_Federal Results 2 Party Preferred by Electorate-2004.xls"));
			electorateMappingImporter2.doImport(new FileInputStream("TestData//Election//Qld_Federal-State Electorate Mapping.xls"));
			

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} 

	}
}
