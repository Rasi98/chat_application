package com.company.client;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class client_1 {
    public static void main(String[] args) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        Client client=new Client();
        client.start();

        client.sendmessage("Hello from the client");
        client.sendmessage("How are you?");
        client.sendmessage("I'm Fine");
        client.sendmessage("Thank you");

        //call for passuserinput function
        client.passUserInput();


       client.sendmessage("exit");
       System.out.println("Client finished execution");



    }
}
