package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main1 {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";

    public static void main(String[] args) throws SQLException{
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL,USERNAME,PASSWORD);
        if (conn != null)
            System.out.println("code..............");
        String query = "select * from mydb.clients";
        Statement statement = conn.createStatement();
        ResultSet resSet = statement.executeQuery(query);

        while (resSet.next()){
            int id;
            String name;
            String sname;
            String mail;
            id = resSet.getInt("id");
            name = resSet.getString("firstname");
            sname = resSet.getString("secondname");
            mail = resSet.getString("mail");
            Product product = new Product(id, name, sname, mail);
            System.out.println(product);
        }
        statement.close();
        conn.close();
    }
}
