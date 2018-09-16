package com.jdbc;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectorManager {
    Socket socket;
    InputStream sin;
    OutputStream sout;
    //PrintWriter printWriter;
    //BufferedReader bufferedReader;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    public ConnectorManager() {
    }

    public void start(){
        int port = 1984; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");

            socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
            System.out.println();

            sin = socket.getInputStream();
            sout = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            dataInputStream = new DataInputStream(socket.getInputStream());
            //bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public InputStream getSin() {
        return sin;
    }

    public OutputStream getSout() {
        return sout;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    /*public PrintWriter getPrintWriter() {
        return printWriter;
    }*/

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    /*public BufferedReader getBufferedReader() {
        return bufferedReader;
    }*/

    public void stop()  {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
