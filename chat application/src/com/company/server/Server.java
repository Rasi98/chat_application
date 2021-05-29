package com.company.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
	// write your code here
        System.out.println("server running..");

        int port=6000;
        ServerSocket serverSocket=new ServerSocket(port);
        System.out.println("server socket created");

        while (true) {
            //server run forever
            Socket client = serverSocket.accept();//wait until a client connects
            System.out.println("Server accept a client");
            ClientHandler clientHandler=new ClientHandler(client);
            Thread thread=new Thread(clientHandler);
            thread.start();

        }



    }
}
