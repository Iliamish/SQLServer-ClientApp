package com.jdbc;

import java.net.*;
import java.io.*;
import com.mysql.fabric.jdbc.FabricMySQLDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.*;






public class Server {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    public static Listener liss = new Listener();
    public static Sender send = new Sender();

    public static void main(String[] args) throws SQLException, IOException {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL,USERNAME,PASSWORD);
        if (conn != null)
            System.out.println("code..............");
        conn.close();
        JSONObject obj = new JSONObject();
        JSONArray obja = new JSONArray();
        ConnectorManager connector = new ConnectorManager();
        connector.start();

        liss.setIn(connector.getDataInputStream());
        send.setOut(connector.getDataOutputStream());


        while(true){
            String line = null;
            line = liss.getIn().readUTF();
            System.out.println("The dumb client just sent me this line : " + line);
            switch (line){
                case "Call": call(); break;
                case "Add" : add(liss.getIn().readUTF()); break;
                case "Delete" : delete(liss.getIn().readInt()); break;
                case "Update" : update(liss.getIn().readUTF()); break;

            }










        }
    }
        public static void call() throws SQLException, IOException {
            DBProcessor db = new DBProcessor();
            Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
            if (conn != null)
                System.out.println("code..............");
            String select = "select * from mydb.clients";

            JSONObject obj;
            JSONArray obja;
            Statement statement = conn.createStatement();
            ResultSet resSet = statement.executeQuery(select);
            obja = new JSONArray();
            while (resSet.next()) {

                obj = new JSONObject();
                int id;
                String name;
                String sname;
                String mail;
                id = resSet.getInt("id");
                name = resSet.getString("firstname");
                sname = resSet.getString("secondname");
                mail = resSet.getString("mail");
                Product product = new Product(id, name, sname, mail);
                obj.put("id", id);
                obj.put("name", name);
                obj.put("sname", sname);
                obj.put("mail", mail);
                obja.add(obj);
            }
            System.out.println(obja);
            send.getOut().writeUTF(obja.toString());
            statement.close();
            send.getOut().flush(); // заставляем поток закончить передачу данных.
            System.out.println("Wait");
            conn.close();
        }

    public static void delete(int row) throws SQLException, IOException {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        if (conn != null)
            System.out.println("code..............");

        System.out.println(row);
        String delete = "DELETE from mydb.clients where id = " + String.valueOf(row) + ";";
        System.out.println(delete);
        String select = "select * from mydb.clients";

        JSONObject obj;
        JSONArray obja;
        Statement statement = conn.createStatement();
        statement.execute(delete);
        ResultSet resSet = statement.executeQuery(select);
        obja = new JSONArray();
        while (resSet.next()) {

            obj = new JSONObject();
            int id;
            String name;
            String sname;
            String mail;
            id = resSet.getInt("id");
            name = resSet.getString("firstname");
            sname = resSet.getString("secondname");
            mail = resSet.getString("mail");
            Product product = new Product(id, name, sname, mail);
            obj.put("id", id);
            obj.put("name", name);
            obj.put("sname", sname);
            obj.put("mail", mail);
            obja.add(obj);
        }
        System.out.println(obja);
        send.getOut().writeUTF(obja.toString());
        statement.close();
        send.getOut().flush(); // заставляем поток закончить передачу данных.
        System.out.println("Wait");
        conn.close();
    }

    public static void update(String row) throws SQLException, IOException {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        if (conn != null)
            System.out.println("code..............");
        JSONObject object = new JSONObject();
        object =(JSONObject)JSONValue.parse(row);
        System.out.println(row);

        String update = "UPDATE clients set firstname='" + object.get("name")+ "' ,secondname='" + object.get("sname") + "' ,mail='" + object.get("mail") + "' where id=" + object.get("id");
        System.out.println(update);
        String select = "select * from mydb.clients";

        JSONObject obj;
        JSONArray obja;
        Statement statement = conn.createStatement();
        statement.execute(update);
        ResultSet resSet = statement.executeQuery(select);
        obja = new JSONArray();
        while (resSet.next()) {

            obj = new JSONObject();
            int id;
            String name;
            String sname;
            String mail;
            id = resSet.getInt("id");
            name = resSet.getString("firstname");
            sname = resSet.getString("secondname");
            mail = resSet.getString("mail");
            Product product = new Product(id, name, sname, mail);
            obj.put("id", id);
            obj.put("name", name);
            obj.put("sname", sname);
            obj.put("mail", mail);
            obja.add(obj);
        }
        System.out.println(obja);
        send.getOut().writeUTF(obja.toString());
        statement.close();
        send.getOut().flush(); // заставляем поток закончить передачу данных.
        System.out.println("Wait");
        conn.close();
    }
        public static void add(String row) throws SQLException, IOException {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        if (conn != null)
            System.out.println("code..............");
            JSONObject object = new JSONObject();
            object =(JSONObject)JSONValue.parse(row);
        String add = "INSERT INTO mydb.clients(firstname, secondname, mail ) VALUES ('" + object.get("name") +"' , '" +object.get("sname")+"' , '" + object.get("mail")+"')";
            System.out.println(add);
        String select = "select * from mydb.clients";
        JSONObject obj;
            JSONArray obja;
        Statement statement = conn.createStatement();
        statement.execute(add);
        ResultSet resSet = statement.executeQuery(select);
        obja = new JSONArray();
        while (resSet.next()) {

            obj = new JSONObject();
            int id;
            String name;
            String sname;
            String mail;
            id = resSet.getInt("id");
            name = resSet.getString("firstname");
            sname = resSet.getString("secondname");
            mail = resSet.getString("mail");
            Product product = new Product(id, name, sname, mail);
            obj.put("id", id);
            obj.put("name", name);
            obj.put("sname", sname);
            obj.put("mail", mail);
            obja.add(obj);
        }
        System.out.println(obja);
        send.getOut().writeUTF(obja.toString());
        statement.close();
        send.getOut().flush(); // заставляем поток закончить передачу данных.
        System.out.println("Wait");
        conn.close();
    }
    }



