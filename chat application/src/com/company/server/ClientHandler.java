package com.company.server;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class ClientHandler implements Runnable {

    private Socket client;
    public ClientHandler(Socket socket){
        this.client=socket;
    }
    private String encryptionKey="1234567890123456";



    @Override
    public void run() {

        try {
            Thread.sleep(1000);


        //get input from client
        InputStream inputStream = client.getInputStream();
        //BufferedReader read the text from a character-based input stream
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        OutputStream outputStream = client.getOutputStream();
        //Creates a new data output stream to write data to the specified underlying output stream
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);


        //continuous reading data coming from the client
        String inputData;
       while ((inputData = bufferedReader.readLine()) != null) {
            //BufferedReader.readline() method read a line of text. A line is considered to be terminated by any one of a line feed ('\n')
            System.out.println("Client :" + inputData);
            //call decrypt function and get decrypted message
            //String DMSG= DcryptMSG(inputData.getBytes(),this.encryptionKey.getBytes());

            //check input coming from the client and respond accordingly.
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
                    break;
                case "hi":
                    dataOutputStream.writeBytes("Hi...!!!\n");
                    break;
               default:
                    dataOutputStream.writeBytes("Didn't understand.  \n");
                    break;
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

    public String DcryptMSG(byte[] msg, byte[] keyBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //create AES Cipher instance using Cipher Block Chaining (CBC)
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        //cipher decrypt mode
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        //Decrypt the message
        byte[] decryptedMsg=cipher.doFinal(msg);
        return new String(decryptedMsg);
    }
}
