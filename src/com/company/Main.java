package com.company;


import java.net.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final int port   = 5001;
    public static void main(String[] args) throws IOException {
        {
            ServerSocket srvSocket = null;
            try {
                try {
                    int i = 0;

                    InetAddress ia;
                    ia = InetAddress.getByName("localhost");
                    srvSocket = new ServerSocket(port, 0, ia);

                    System.out.println("Server started\n\n");

                    while(true) {

                        Socket socket = srvSocket.accept();
                        System.err.println("Client accepted");

                        new Server().setSocket(i++, socket);
                    }
                } catch(Exception e) {
                    System.out.println("Exception : " + e);
                }
            } finally {
                try {
                    if (srvSocket != null)
                        srvSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.exit(0);
        }
    }
}