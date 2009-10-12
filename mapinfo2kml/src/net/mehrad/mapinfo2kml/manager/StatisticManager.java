package net.mehrad.mapinfo2kml.manager;

import java.util.List;

import net.mehrad.mapinfo2kml.datamanager.StatisticDataManager;
import net.mehrad.mapinfo2kml.xls.SafetyStat;

public class StatisticManager {

	private StatisticDataManager stManager=new StatisticDataManager();
	
	public List<String> getDivisionsForRegion(String requiredRegion){
		return this.stManager.getDivisionsForRegion(requiredRegion);
	}
	
	public List<String> determineRegionsForState(String requiredState){
		return this.stManager.determineRegionsForState(requiredState);
	}

	public List<SafetyStat> getSafetyStatisticsForState(String requiredState){
		return this.getSafetyStatisticsForState(requiredState);
	}

}
