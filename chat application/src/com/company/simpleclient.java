package com.company;

import java.io.*;
import java.net.Socket;

public class simpleclient {
    public static void main(String[] args) throws IOException {
        System.out.println("client running..");

        Socket socket=new Socket("localhost",6000);

        //Get the data
        InputStream inputStream=socket.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String receiveData= bufferedReader.readLine();

        System.out.println("Data received:"+receiveData);

    }
}
