package com.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import org.json.simple.JSONObject;

import java.sql.*;


public class DBProcessor {
   private Connection connection;
   public DBProcessor() throws SQLException{
       DriverManager.registerDriver(new FabricMySQLDriver());
   }

    public Connection getConnection(String url, String username, String password) throws SQLException {
        if (connection != null)
            return connection;
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
