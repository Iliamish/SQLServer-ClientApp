package com.jdbc;

import java.io.*;

public class Sender {
    DataOutputStream out;

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    /*PrintWriter out;
        public Sender() {


        }

        /*public PrintWriter getOut() {
            return out;
        }

        public void setOut(PrintWriter out) {
            this.out = out;
        }
    */
    public void stop() throws IOException {
        out.close();
    }
}
