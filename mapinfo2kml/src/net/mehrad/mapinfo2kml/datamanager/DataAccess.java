package net.mehrad.mapinfo2kml.datamanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataAccess {

    protected Connection con;
  
    public DataAccess(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
        } catch (Exception ex) {
            
        }
    }
    
    public void prepareConnection()
    {
    	try {
			this.con = DriverManager.getConnection(Constants.connectionString,
					Constants.username, Constants.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
  
}
