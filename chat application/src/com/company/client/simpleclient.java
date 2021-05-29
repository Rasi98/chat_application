package com.company.client;

import java.io.*;

public class simpleclient {
    public static void main(String[] args) throws IOException {

        Client client=new Client();
        client.start();

        client.sendmessage("Hello from the client");
        client.sendmessage("How are you?");
        client.sendmessage("I'm Fine");
        client.sendmessage("Thank you");
        client.sendmessage("exit");

        System.out.println("Client finished execution");



    }
}
