package net.mehrad;

import java.util.List;

import junit.framework.TestCase;
import net.mehrad.mapinfo2kml.datamanager.StatisticDataManager;


public class TestStatisticDataManager extends TestCase{

	public void testAll(){
		StatisticDataManager stMan=new StatisticDataManager();
		List list= stMan.getDivisionsForRegion("Brisbane");
		System.out.println(list);
		list=stMan.getSafetyStatisticsForState("QLD");
		System.out.println(list);		
		list=stMan.determineRegionsForState("QLD");
		System.out.println(list);		
	}
}
