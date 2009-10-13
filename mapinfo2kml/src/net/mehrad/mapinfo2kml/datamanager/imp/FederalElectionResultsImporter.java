package net.mehrad.mapinfo2kml.datamanager.imp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import net.mehrad.mapinfo2kml.datamanager.FederalElectionResultsDataManager;
import net.mehrad.mapinfo2kml.util.ParseExcelUtils;
import net.mehrad.mapinfo2kml.xls.FederalElectionResults;

public class FederalElectionResultsImporter {

	public void doImport(InputStream inputStream) throws IOException {
		ArrayList<ArrayList<String>> genExcelRows = ParseExcelUtils
				.genExcelRows2(inputStream);
		FederalElectionResultsDataManager dataManager = new FederalElectionResultsDataManager();
		dataManager.deleteAll();
		
		boolean headerFlag = true;
		for (ArrayList<String> record : genExcelRows) {
			if (headerFlag) {
				headerFlag = false;
				continue;
			}

			FederalElectionResults model = new FederalElectionResults();
			model.setState("QLD");
			model.setYear(2004);
			model.setSeat(record.get(0));
			model.setParty(record.get(1));
			model.setPercentage(Double.parseDouble(record.get(2)));
			model.setMP(record.get(3));
			model.setHeldSince((int) Double.parseDouble(record.get(4)));
			try {
				model.setPreviouslyHeld(record.get(5));
			} catch(Exception e) {//nothing
			}
			dataManager.insert(model);
		}

	}

}
