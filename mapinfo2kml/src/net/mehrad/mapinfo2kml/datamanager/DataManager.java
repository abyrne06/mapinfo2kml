package net.mehrad.mapinfo2kml.datamanager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public abstract class DataManager extends DataAccess implements IDataManager{

    protected abstract String getTableName();
    protected abstract Map<String,String> getPropertyMap(Object obj);
    
    protected String genInsertQuery(Object obj){
    	Map propMap=getPropertyMap(obj);
    	String colNames="";
    	String colVals="";
    	String[] cols=convertToStringArray( propMap.keySet().toArray());
    	for (int i = 0; i < cols.length; i++) {
			colNames=colNames+ cols[i]+",";
			colVals=colVals+propMap.get(cols[i])+",";
		}
    	colNames=colNames.substring(0, colNames.length()-1);
    	colVals=colVals.substring(0, colVals.length()-1);
    	
    	String query="INSERT INTO "+getTableName()+" ("+colNames+")"+
    		" VALUES "+"("+colVals+")";    	
    	return query;
    }
    
    private String[] convertToStringArray(Object[] objects)
    {
    	String[] result=new String[objects.length];
    	for (int i = 0; i < objects.length; i++) {
			result[i]=objects[i].toString();
		}
    	return result;
    }
    
    public void insert(Object obj){
    	prepareConnection();
    	String insertCmd=genInsertQuery(obj);
//    	System.out.println(insertCmd);
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

    public void deleteAll(){
    	prepareConnection();
    	String insertCmd="delete from "+getTableName();
        try {
			PreparedStatement stm =  this.con.prepareStatement(insertCmd);
			stm.executeUpdate();
			stm.close();
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	
    }

    
}
