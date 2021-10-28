package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {


    public static void main(String[] args) throws IOException {
       
        ServerSocket servSocket = new ServerSocket(23444);
        while (true) {

            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new
                         InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {

                    int result = fibonacci(Integer.parseInt(line));
                    out.println(" Число Фибоначи для " + line + " = " + result);

                    if (line.equals("end")) {
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static int fibonacci(int fiboN) {
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