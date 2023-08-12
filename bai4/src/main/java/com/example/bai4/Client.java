package com.example.bai4;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 8080;

        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to Server");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Gửi và nhận tin nhắn với Server
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("You: ");
                String message = br.readLine();
                out.println(message); // Gửi tin nhắn cho Server

                String response = in.readLine(); // Nhận phản hồi từ Server
                System.out.println("Server: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
