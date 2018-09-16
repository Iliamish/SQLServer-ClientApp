package com.jdbc;

import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;


public class Client {
    public static void main(String[] ar) {
        Object[] headers = { "Name", "Surname", "Mail", "Id"};
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

            /*while (true) {
                line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                System.out.println("Sending this line to the server...");
                out.writeUTF(line);
                System.out.println(in.readUTF());
                obja=(JSONArray)JSONValue.parse(in.readUTF());
                    System.out.println(obja);


                System.out.println("Complite");
                    // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
               // ждем пока сервер отошлет строку текста.
                obj=(JSONObject)obja.get(1);
                System.out.println(obj.get("name"));
            }*/



            //int width = 1000, heigth = 800;
            JFrame frame = new JFrame();
            JPanel jpanel = new JPanel();
            ArrayList humans = new ArrayList<>();
            JButton addbtn = new JButton("Add");
            JButton calldb = new JButton("Call DB");
            JButton delbtn = new JButton("Delete");
            JButton upbtn = new JButton("Update");
            JTextField NameField = new JTextField(10);
            JTextField SnameField = new JTextField(10);
            JTextField MailField = new JTextField(10);
            MyTableModel tModel = new MyTableModel(humans);
            //На основе модели, создадим новую JTable
            JTable jTabPeople = new JTable(tModel);


            /*addbtn.addActionListener(ae -> {
                try {
                    humans.add(new Human( obj.getString("name"), obj.getString("surname"), obj.getString("mail"), obj.getString("id")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tModel.fireTableDataChanged();
            });*/



            //Создадим модель таблицы
            addbtn.setAlignmentX(800);

            //Создаем панель прокрутки и включаем в ее состав нашу таблицу
            JScrollPane jscrlp = new JScrollPane(jTabPeople);
            //Устаналиваем размеры прокручиваемой области
            frame.getContentPane().setLayout(new FlowLayout());


            frame.setSize(1200, 300);
            jTabPeople.setPreferredScrollableViewportSize(new Dimension(500, 200));
            //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
            jscrlp.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
            frame.add(jpanel);
            frame.getContentPane().add(jscrlp);
            frame.getContentPane().add(calldb);
            frame.getContentPane().add(addbtn);
            frame.getContentPane().add(delbtn);

            frame.getContentPane().add(upbtn);
            frame.getContentPane().add(NameField);
            frame.getContentPane().add(SnameField);
            frame.getContentPane().add(MailField);

            frame.setVisible(true);



            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            calldb.addActionListener(ae -> {
                try {


                    System.out.println(humans);

                    tModel.fireTableDataChanged();
                    humans.removeAll(humans);
                    JSONArray obja = new JSONArray();
                    JSONObject obj = new JSONObject();
                    System.out.println("Sending this line to the server...");
                    out.writeUTF("Call");
                    //System.out.println(in.readUTF());
                    obja =(JSONArray)JSONValue.parse(in.readUTF());
                    //int k = in.readInt();
                    System.out.println(obja);


                    System.out.println("Complite");
                    // отсылаем введенную строку текста серверу.
                    out.flush(); // заставляем поток закончить передачу данных.
                    // ждем пока сервер отошлет строку текста.
                    int p;
                    for (p = 0; p <= obja.size(); p++){

                        obj =(JSONObject)obja.get(p);
                        humans.add(new Human(obj.get("name").toString(), obj.get("sname").toString(), obj.get("mail").toString(), obj.get("id").toString()));
                        tModel.fireTableDataChanged();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            upbtn.addActionListener(ae -> {
                try {
                    JSONArray obja = new JSONArray();
                    JSONObject obj = new JSONObject();
                    System.out.println("Sending this line to the server...");

                    int rw = jTabPeople.getSelectedRow();
                    System.out.println(rw);
                    obj.put("name",NameField.getText());
                    obj.put("sname",SnameField.getText());
                    obj.put("mail",MailField.getText());
                    obj.put("id",jTabPeople.getValueAt(rw,3).toString());
                    System.out.println(obj);
                    //System.out.println(in.readUTF());
                    humans.removeAll(humans);
                    out.writeUTF("Update");
                    out.writeUTF(obj.toString());
                    obja =(JSONArray)JSONValue.parse(in.readUTF());
                    //int k = in.readInt();
                    System.out.println(obja);


                    System.out.println("Complite");
                    // отсылаем введенную строку текста серверу.
                    out.flush(); // заставляем поток закончить передачу данных.
                    // ждем пока сервер отошлет строку текста.
                    int p;
                    for (p = 0; p <= obja.size(); p++){

                        obj =(JSONObject)obja.get(p);
                        humans.add(new Human(obj.get("name").toString(), obj.get("sname").toString(), obj.get("mail").toString(), obj.get("id").toString()));
                        tModel.fireTableDataChanged();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });


            delbtn.addActionListener(ae -> {
                try {


                    int row = jTabPeople.getSelectedRow();

                    String ids = jTabPeople.getValueAt(row,3).toString();
                    System.out.println(Integer.valueOf(ids));
                    //System.out.println(ids);

                    System.out.println();
                    tModel.fireTableDataChanged();
                    JSONArray obja = new JSONArray();
                    JSONObject obj = new JSONObject();
                    System.out.println("Sending this line to the server...");
                    out.writeUTF("Delete");

                    out.writeInt(Integer.valueOf(ids));
                    humans.removeAll(humans);
                    //System.out.println(in.readUTF());
                    obja =(JSONArray)JSONValue.parse(in.readUTF());
                    //int k = in.readInt();
                    System.out.println(obja);


                    System.out.println("Complite");
                    // отсылаем введенную строку текста серверу.
                    out.flush(); // заставляем поток закончить передачу данных.
                    // ждем пока сервер отошлет строку текста.
                    int p;
                    for (p = 0; p <= obja.size(); p++){

                        obj =(JSONObject)obja.get(p);
                        humans.add(new Human(obj.get("name").toString(), obj.get("sname").toString(), obj.get("mail").toString(), obj.get("id").toString()));
                        tModel.fireTableDataChanged();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            addbtn.addActionListener(ae -> {
                try {


                    JSONArray obja = new JSONArray();
                    JSONObject obj = new JSONObject();


                    System.out.println("Sending this line to the server...");


                    obj.put("name",NameField.getText());
                    obj.put("sname",SnameField.getText());
                    obj.put("mail",MailField.getText());

                    out.writeUTF("Add");
                    out.writeUTF(obj.toString());
                    humans.removeAll(humans);
                    //System.out.println(in.readUTF());
                    obja =(JSONArray)JSONValue.parse(in.readUTF());

                    System.out.println(obja);


                    System.out.println("Complite");
                    // отсылаем введенную строку текста серверу.
                    out.flush(); // заставляем поток закончить передачу данных.
                    // ждем пока сервер отошлет строку текста
                    int p;
                    for (p = 0; p <= obja.size(); p++){

                        obj =(JSONObject)obja.get(p);

                        humans.add(new Human(obj.get("name").toString(), obj.get("sname").toString(), obj.get("mail").toString(), obj.get("id").toString()));
                        tModel.fireTableDataChanged();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });


        } catch (Exception x) {
            x.printStackTrace();
        }




    }
}

class MyTableModel extends AbstractTableModel {

    ArrayList<Human> humans;
    MyTableModel(ArrayList<Human> humans) {
        super();
        this.humans = humans;
    }
    @Override
    public String getColumnName(int c) {
        switch (c) {
            case 0:
                return "Name";
            case 1:
                return "Surname";
            case 2:
                return "Mail";
            case 3:
                return "id";
        }
        return "";
    }
    @Override
    public int getRowCount() {
        return humans.size();
    }
    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return humans.get(r).getName();
            case 1:
                return humans.get(r).getSurname();
            case 2:
                return humans.get(r).getTelephone();
            case 3:
                return humans.get(r).getId();
            default:
                return "";
        }
    }
}

class Human {
    String name;
    String surname;
    String telephone;
    String id;
    public Human(String name, String surname, String telephone, String id) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getId(){
        return id;
    }

}