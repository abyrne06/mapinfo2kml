package net.mehrad.mapinfo2kml.datamanager;

import java.util.HashMap;
import java.util.Map;

import net.mehrad.mapinfo2kml.xls.FederalStateElectorateMapping;

public class FederalStateElectorateMappingDataManager extends DataManager {

	protected Map<String, String> getPropertyMap(Object obj) {
		FederalStateElectorateMapping fse=(FederalStateElectorateMapping) obj;
		Map<String, String> propMap=new HashMap<String, String>();
		propMap.put("'State'","'"+fse.getState()+"'");
		propMap.put("'FederalSeat'","'"+fse.getFederalSeat()+"'");
		propMap.put("'StateSeat'","'"+fse.getStateSeat()+"'");
		return propMap;
	}

	@Override
	protected String getTableName() {
		return "federalstateelectoratemapping";
	}


}

