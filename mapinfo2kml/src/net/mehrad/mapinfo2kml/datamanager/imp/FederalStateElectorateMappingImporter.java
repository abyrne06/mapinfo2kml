package net.mehrad.mapinfo2kml.datamanager.imp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import net.mehrad.mapinfo2kml.datamanager.FederalStateElectorateMappingDataManager;
import net.mehrad.mapinfo2kml.util.ParseExcelUtils;
import net.mehrad.mapinfo2kml.xls.FederalStateElectorateMapping;

public class FederalStateElectorateMappingImporter {

	public void doImport(InputStream inputStream) throws IOException
	{
		ArrayList<ArrayList<String>> genExcelRows = ParseExcelUtils.genExcelRows2(inputStream);
		FederalStateElectorateMappingDataManager dataManager=new FederalStateElectorateMappingDataManager();
		dataManager.deleteAll();
		
		boolean headerFlag=true;
		for(ArrayList<String> record:genExcelRows)
		{
			if(headerFlag)
			{
				headerFlag=false;
				continue;
			}
			
			FederalStateElectorateMapping model=new FederalStateElectorateMapping();
			model.setState("QLD");
			model.setFederalSeat(record.get(0));
			model.setStateSeat(record.get(1));
			
			dataManager.insert(model);
		}
		
	}
	
}
