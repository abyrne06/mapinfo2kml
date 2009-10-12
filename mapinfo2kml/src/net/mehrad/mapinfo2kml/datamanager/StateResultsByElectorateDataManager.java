package net.mehrad.mapinfo2kml.datamanager;

import java.util.HashMap;
import java.util.Map;

import net.mehrad.mapinfo2kml.xls.StateResultsByElectorate;

public class StateResultsByElectorateDataManager extends DataManager{

	protected Map<String, String> getPropertyMap(Object obj) {
		StateResultsByElectorate sr=(StateResultsByElectorate)obj;
		Map<String, String> propMap=new HashMap<String, String>();
		propMap.put("'State'","'"+sr.getState()+"'");
		propMap.put("'Year'","'"+sr.getYear()+"'");
		propMap.put("'District'","'"+sr.getDistrict()+"'");
		propMap.put("'Candidate1'","'"+sr.getCandidate1()+"'");
		propMap.put("'Party1'","'"+sr.getParty1()+"'");
		propMap.put("'Votes1'","'"+sr.getVotes1()+"'");
		propMap.put("'Percentage1'","'"+sr.getPercentage1()+"'");
		propMap.put("'Candidate2'","'"+sr.getCandidate2()+"'");
		propMap.put("'Party2'","'"+sr.getParty2()+"'");
		propMap.put("'Votes2'","'"+sr.getVotes2()+"'");
		propMap.put("'Percentage2'","'"+sr.getPercentage2()+"'");
		return propMap;
	}

	@Override
	protected String getTableName() {
		return "stateresultsbyelectorate";
	}


}
