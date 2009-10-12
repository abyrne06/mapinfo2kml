package net.mehrad.mapinfo2kml.datamanager;

import java.util.HashMap;
import java.util.Map;

import net.mehrad.mapinfo2kml.xls.FederalElectionResults;

public class FederalElectionResultsDataManager extends DataManager{

	@Override
	protected Map<String, String> getPropertyMap(Object obj) {
		FederalElectionResults fer=(FederalElectionResults)obj;
		Map<String, String> propMap=new HashMap<String, String>();
		propMap.put("'State'","'"+fer.getState()+"'");
		propMap.put("'Year'","'"+fer.getYear()+"'");
		propMap.put("'Seat'","'"+fer.getSeat()+"'");
		propMap.put("'Party'","'"+fer.getParty()+"'");
		propMap.put("'Percentage'","'"+fer.getPercentage()+"'");
		propMap.put("'MP'","'"+fer.getMP()+"'");
		propMap.put("'HeldSince'","'"+fer.getHeldSince()+"'");
		propMap.put("'PreviouslyHeld'","'"+fer.getPreviouslyHeld()+"'");
		return propMap;
	}

	@Override
	protected String getTableName() {
		return "federalelectionresults";
	}


}
