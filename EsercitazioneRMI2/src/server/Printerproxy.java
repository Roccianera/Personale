package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaccie.IPrinter;

public class Printerproxy implements IPrinter {

    @Override
    public boolean print(String docName) {
        // TODO Auto-generated method stub
        return false;
    }


    public void Callback(int PortNumber) {

        try (Socket socket = new Socket(InetAddress.getLocalHost(), PortNumber)) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream.writeUTF("Aggiunto Printer");

            dataInputStream.readUTF();
            
            socket.close();
        } catch (IOException e) {
            System.out.println("callbackfallita");
            e.printStackTrace();
        }


    }







}
