package net.mehrad.mapinfo2kml.datamanager;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.mehrad.mapinfo2kml.xls.SafetyStat;


public class StatisticDataManager extends DataAccess{
	
	/*public double DeterminePreviouslyWonFactor(double consecutiveElections){
		try {
			CallableStatement cst= this.con.prepareCall("{call DeterminePreviouslyWonFactor(?)}");
			cst.setDouble(1, consecutiveElections);
			boolean hasValue=cst.execute();
			while (hasValue){
				ResultSet rs=cst.getResultSet();
				hasValue=cst.getMoreResults();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}*/
	
	public StatisticDataManager() {
		super();
	}

	public List<String> getDivisionsForRegion(String requiredRegion){
		try {
			CallableStatement cst= this.con.prepareCall("{call GetDivisionsForRegion(?)}");
			cst.setString(1, requiredRegion);
			List<String> divisions=new ArrayList<String>();
			boolean hasValue=cst.execute();
			while (hasValue){
				ResultSet rs=cst.getResultSet();
	            while( rs.next()){
					String div=rs.getString(1);
					divisions.add(div);
	            }
	            rs.close();
				hasValue=cst.getMoreResults();
			}
			cst.close();
//			this.con.close();
			return divisions;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> determineRegionsForState(String requiredState){
		try {
			CallableStatement cst= this.con.prepareCall("{call DetermineRegionsForState(?)}");
			cst.setString(1, requiredState);
			List<String> regions=new ArrayList<String>();
			boolean hasValue=cst.execute();
			while (hasValue){
				ResultSet rs=cst.getResultSet();
	            while( rs.next()){
					String div=rs.getString("Region");
					regions.add(div);
	            }
				hasValue=cst.getMoreResults();
			}
			cst.close();
//			this.con.close();
			return regions;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	public List<SafetyStat> getSafetyStatisticsForState(String requiredState){
		try {
			CallableStatement cst= this.con.prepareCall("{call GetSafetyStatisticsForState(?)}");
			cst.setString(1, requiredState);
			List<SafetyStat> resultList=new ArrayList<SafetyStat>();
			boolean hasValue=cst.execute();
			while (hasValue){
				ResultSet rs=cst.getResultSet();
	            while( rs.next()){				
					SafetyStat ss=new SafetyStat();
					ss.setState(rs.getString("State"));
					ss.setSeat(rs.getString("Seat"));
					ss.setSafetyLevel(rs.getDouble("Safety"));
					resultList.add(ss);
	            }
				hasValue=cst.getMoreResults();
			}
			cst.close();
//			this.con.close();			
			return resultList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

}
