package com.jdbc;

import org.omg.IOP.Encoding;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class NullServer {
    public static void main(String[] args) throws IOException {
        ConnectorManager connector = new ConnectorManager();
        connector.start();
        Listener liss = new Listener();
        //liss.setIn(connector.getBufferedReader());
        liss.setIn(connector.getDataInputStream());
        Sender send = new Sender();
        //send.setOut(connector.getPrintWriter());
        send.setOut(connector.getDataOutputStream());
                String line = liss.getIn().readUTF();

            System.out.println(line);
            send.getOut().writeUTF("asasas");

            send.getOut().flush();

            System.out.println("The dumb client just sent me this line : ");

    }
}
