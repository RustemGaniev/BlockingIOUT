package com.company;

import java.io.*;
import java.net.Socket;

class Server extends Thread {

    private static final int port   = 0;
    private String TEMPL_MSG =
            "The client '%d' sent me message : \n\t";
    private String TEMPL_CONN =
            "The client '%d' closed the connection";

    private  Socket socket;
    private  int    num;

    public Server() {

    }
    public void setSocket(int num, Socket socket)
    {

        this.num    = num;
        this.socket = socket;


        setDaemon(true);

        setPriority(NORM_PRIORITY);

        start();
    }
    public void run()
    {
        try {

            InputStream  sin  = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream  dis = new DataInputStream (sin );
            DataOutputStream dos = new DataOutputStream(sout);

            String line = null;
            while(true) {

                line = dis.readUTF();
                if (line.equalsIgnoreCase("quit")) {

                    socket.close();
                    System.out.println(
                            String.format(TEMPL_CONN, num));
                    break;
                }
                System.out.println(
                        String.format(TEMPL_MSG, num) + line);
                System.out.println("I'm sending it back...");

                int result = fibonacci(Integer.parseInt(line));

                dos.writeUTF(String.valueOf(result));

                dos.flush();
                System.out.println();

            }
        } catch(Exception e) {
            System.out.println("Exception : " + e);
        }
    }

    public int fibonacci(int fiboN){
        int a = 0;
        int b = 1;
        for (int i = 2; i <= fiboN; ++i) {
            int next = a + b;
            a = b;
            b = next;
        }
        return b;
    }
}
