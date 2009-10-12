package net.mehrad.mapinfo2kml.datamanager;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DataAccess {

    protected Connection con;
  
    public DataAccess(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.con = DriverManager.getConnection(Constants.connectionString,
            		Constants.username, Constants.password);
        } catch (Exception ex) {
            
        }
    }
  
}
