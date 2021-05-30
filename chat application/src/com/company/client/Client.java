package com.company.client;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Client {

    private DataOutputStream outputStream;
    private BufferedReader bufferedReader;
    private BufferedReader userReader;
    private String encryptionKey="1234567890123456";


    //can use a constructor also
    public void start() throws IOException {
        System.out.println("Client is running....");
        Socket socket=new Socket("localhost",6000);

        OutputStream outputStream=socket.getOutputStream();
        this.outputStream=new DataOutputStream(outputStream);

        InputStream inputStream=socket.getInputStream();
        this.bufferedReader = new BufferedReader((new InputStreamReader(inputStream)));

        this.userReader=new BufferedReader((new InputStreamReader(System.in)));

    }
    public  void sendmessage(String message) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        System.out.println("client says:"+message);
        this.outputStream.writeBytes(message+"\n");
        String dataFromServer=this.bufferedReader.readLine();
        System.out.println("server says:"+dataFromServer);
    }

    public void passUserInput() throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        System.out.println("say hi");
        //reading user input
        String Uinput=this.userReader.readLine();
        System.out.println("user says: "+Uinput);

        //Encrypt
        String EMSG=encryptMsg(Uinput.getBytes(),this.encryptionKey.getBytes());


        //send input to server
        this.outputStream.writeBytes(EMSG+"\n");
        //display server response
        String resServer=this.bufferedReader.readLine();
        System.out.println("Server says:"+resServer);

    }
    //Encrypt data using cipher. return message as an encrypted message
    public String encryptMsg(byte[] msg, byte[] keyBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //create AES Cipher instance using Cipher Block Chaining (CBC)
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey=new SecretKeySpec(keyBytes,"AES");
        //cipher encrypt mode
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        //Encrypt the message
        byte[] encryptedtMsg = cipher.doFinal(msg);
        return new String(encryptedtMsg);

    }
}

