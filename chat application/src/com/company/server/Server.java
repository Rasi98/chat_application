package com.company.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
	// write your code here
        System.out.println("server running..");

        //server socket waits for requests to come in over the network.
        //Creates a server socket, bound to a specified port(port 6000 here).
        ServerSocket serverSocket=new ServerSocket(6000);
        System.out.println("server socket created");

        //server run forever
        while (true) {
            //Listens for a connection to be made to this socket and accepts it.
            Socket client = serverSocket.accept();//wait until a client connects
            System.out.println("Server accept a client");
            ClientHandler clientHandler=new ClientHandler(client);
            //create a thread to handle the client.Can handle multiple clients simultaneously.
            Thread thread=new Thread(clientHandler);
            thread.start();

        }



    }
}
