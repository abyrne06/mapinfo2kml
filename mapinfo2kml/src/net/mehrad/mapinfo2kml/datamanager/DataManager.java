package net.mehrad.mapinfo2kml.datamanager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public abstract class DataManager extends DataAccess implements IDataManager{

    protected abstract String getTableName();
    protected abstract Map<String,String> getPropertyMap(Object obj);
    
    protected String genInsertQuery(Object obj){
    	Map propMap=getPropertyMap(obj);
    	String colNames="";
    	String colVals="";
    	String[] cols=(String[]) propMap.keySet().toArray();
    	for (int i = 0; i < cols.length; i++) {
			colNames=colNames+ cols[i]+",";
			colVals=colVals+propMap.get(cols[i])+",";
		}
    	colNames=colNames.substring(0, colNames.length()-2);
    	colVals=colVals.substring(0, colVals.length()-2);
    	
    	String query="INSERT INTO "+getTableName()+" ("+colNames+")"+
    		" VALUES "+"("+colVals+")";    	
    	return query;
    }
    
    public void insert(Object obj){
    	String insertCmd=genInsertQuery(obj);
        try {
			PreparedStatement stm =  this.con.prepareStatement(insertCmd);
			stm.executeUpdate();
			stm.close();
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    }

}
