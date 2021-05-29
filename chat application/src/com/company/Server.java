package com.company;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
	// write your code here
        System.out.println("server running..");

        int port=6000;
        ServerSocket serverSocket=new ServerSocket(port);
        System.out.println("server socket created");
        Socket client=serverSocket.accept();//wait until a client connects
        System.out.println("Server accept a client");

        //send some data
        DataOutputStream outputStream=new DataOutputStream(client.getOutputStream());//wrap outputstream
        outputStream.writeBytes("Hello from the server...\n");

        client.close();
    }
}
