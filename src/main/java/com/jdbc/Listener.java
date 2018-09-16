package com.jdbc;

import java.io.*;

public class Listener {
    //DataInputStream in;
    DataInputStream in;

    public Listener() {

    }


    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public void stop() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
