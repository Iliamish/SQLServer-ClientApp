package com.jdbc;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class NullClient {

    public static void main(String[] args) {


    int serverPort = 1984; // здесь обязательно нужно указать порт к которому привязывается сервер.
    String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа.
    // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Yes! I just got hold of the program.");

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
            System.out.println();


                out.writeUTF("ads");

                System.out.println(in.readUTF());
                System.out.println("Complite");
                // отсылаем введенную строку текста серверу.
                out.flush();


        }
     catch (Exception x) {
        x.printStackTrace();
    }
    }
}
