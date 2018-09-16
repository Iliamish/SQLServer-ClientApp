package com.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import org.json.simple.JSONObject;

import java.sql.*;


public class Main {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    public static void main(String[] args) throws SQLException{
        Driver driver;

        try{
            driver =  new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        }
        catch (SQLException ex){
            System.out.println("Произошла ошибка драйвера.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){
            System.out.println("code..............");
            statement.executeQuery("");
            System.out.println(statement.executeQuery("select * from mydb.clients"));
            JSONObject obj=new JSONObject();


            obj.put("name"," Иванов Михаил");

            obj.put("age",new Integer(21));
            System.out.println("Name:"+obj.get("name"));
            System.out.println("Age:"+obj.get("age"));
        }
        catch (SQLException exe){
            System.out.println(exe);
            System.out.println("Не удалось создать соединение");

        }

    }

}
