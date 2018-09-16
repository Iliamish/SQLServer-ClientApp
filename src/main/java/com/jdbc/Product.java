package com.jdbc;
import org.json.simple.JSONObject;

public class Product {
    private int id;
    private String name;
    private String sname;
    private String mail;

    public Product() {
    }

    public  Product(int id, String name, String sname, String mail) {
        this.id = id;
        this.name = name;
        this.sname = sname;
        this.mail = mail;

    }



    @Override
    public String toString() {
        return id + "\t" + name + "\t" + sname + "\t" + mail;

    }

}

