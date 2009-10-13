package net.mehrad.mapinfo2kml.datamanager.imp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import net.mehrad.mapinfo2kml.datamanager.FederalResults2PPByElectorateDataManager;
import net.mehrad.mapinfo2kml.util.ParseExcelUtils;
import net.mehrad.mapinfo2kml.xls.FederalResults2PPByElectorate;

public class FederalResults2ppByElectorateImporter {

	public void doImport(InputStream inputStream) throws IOException {
		ArrayList<ArrayList<String>> genExcelRows = ParseExcelUtils
				.genExcelRows2(inputStream);
		FederalResults2PPByElectorateDataManager dataManager = new FederalResults2PPByElectorateDataManager();
		dataManager.deleteAll();
		
		int i=1;
		for (ArrayList<String> record : genExcelRows) {
			if (i<3) {
				i++;
				continue;
			}

			FederalResults2PPByElectorate model = new FederalResults2PPByElectorate();
			model.setState(record.get(2));
			model.setYear(2004);
			model.setDivision(record.get(0));
			model.setPartyHeld(record.get(3));
			model.setLNP_Votes((int) Double.parseDouble(record.get(4)));
			model.setLNP_Percentage(Double.parseDouble(record.get(5)));
			model.setALP_Votes((int) Double.parseDouble(record.get(6)));
			model.setALP_Percentage(Double.parseDouble(record.get(7)));
			model.setTotalVotes((int) Double.parseDouble(record.get(8)));
			model.setSwing(Double.parseDouble(record.get(9)));

			dataManager.insert(model);
		}

	}

}
