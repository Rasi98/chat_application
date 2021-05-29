package com.company.server;

import java.io.*;
import java.net.Socket;


public class ClientHandler implements Runnable {

    private Socket client;

    public ClientHandler(Socket socket){
        this.client=socket;
    }


    @Override
    public void run() {

        try {
            Thread.sleep(30000);


        //get input from client
        InputStream inputStream = client.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        OutputStream outputStream = client.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);


        //continuous reading data coming from the client
        String inputData;
        while ((inputData = bufferedReader.readLine()) != null) {
            System.out.println("Client says:" + inputData);

            //send data to the client
            switch (inputData) {
                case "Hello from the client":
                    dataOutputStream.writeBytes("Hello from the server\n");
                    break;
                case "How are you?":
                    dataOutputStream.writeBytes("I'm fine.How are you?\n");
                    break;
                case "I'm Fine":
                    dataOutputStream.writeBytes("Okay good to know.\n");
                    break;
                case "Thank you":
                    dataOutputStream.writeBytes("You are Welcome \n");
                default:
                    dataOutputStream.writeBytes("Didn't understand.  \n");
            }

            if (inputData.equals("exit")) {
                break;
            }
        }

        this.client.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
