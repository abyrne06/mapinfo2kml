package net.mehrad.mapinfo2kml.datamanager.imp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import net.mehrad.mapinfo2kml.datamanager.StateResultsByElectorateDataManager;
import net.mehrad.mapinfo2kml.util.ParseExcelUtils;
import net.mehrad.mapinfo2kml.xls.StateResultsByElectorate;

public class StateResultsByElectorateImporter {

	public void doImport(InputStream inputStream) throws IOException {
		ArrayList<ArrayList<String>> genExcelRows = ParseExcelUtils
				.genExcelRows2(inputStream);
		StateResultsByElectorateDataManager dataManager = new StateResultsByElectorateDataManager();
		dataManager.deleteAll();
		
		int i=1;
		for (ArrayList<String> record : genExcelRows) {
			if (i<4) {
				i++;
				continue;
			}

			try{
			StateResultsByElectorate model = new StateResultsByElectorate();
			model.setState("QLD");
			model.setYear(2006);
			model.setDistrict(record.get(1));
			model.setCandidate1(record.get(3));
			model.setParty1(record.get(4));
			model.setVotes1((int) Double.parseDouble(record.get(5)));
			model.setPercentage1(Double.parseDouble(record.get(6)));
			model.setCandidate2(record.get(8));
			model.setParty2(record.get(9));
			model.setVotes2((int) Double.parseDouble(record.get(10)));
			model.setPercentage2(Double.parseDouble(record.get(11)));
			
			dataManager.insert(model);
			}catch(Exception e)
			{
				System.out.println("Ignored wrong record.");
			}
		}

	}

}
