package net.mehrad.mapinfo2kml.datamanager;

import java.util.HashMap;
import java.util.Map;

import net.mehrad.mapinfo2kml.xls.FederalResults2PPByElectorate;

public class FederalResults2PPByElectorateDataManager extends DataManager{
	
	protected Map<String, String> getPropertyMap(Object obj) {
		FederalResults2PPByElectorate fr=(FederalResults2PPByElectorate)obj;
		Map<String, String> propMap=new HashMap<String, String>();
		propMap.put("`State`","'"+fr.getState()+"'");
		propMap.put("`Year`","'"+fr.getYear()+"'");
		propMap.put("`Division`","'"+fr.getDivision().replaceAll("'","''")+"'");
		propMap.put("`PartyHeld`","'"+fr.getPartyHeld()+"'");
		propMap.put("`LNP_Votes`","'"+fr.getLNP_Votes()+"'");
		propMap.put("`LNP_Percentage`","'"+fr.getLNP_Percentage()+"'");
		propMap.put("`ALP_Votes`","'"+fr.getALP_Votes()+"'");
		propMap.put("`ALP_Percentage`","'"+fr.getALP_Percentage()+"'");
		propMap.put("`TotalVotes`","'"+fr.getTotalVotes()+"'");
		propMap.put("`Swing`","'"+fr.getSwing()+"'");
		return propMap;
	}

	@Override
	protected String getTableName() {
		return "federalresults2ppbyelectorate";
	}


}
