package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

import interfaccie.IPrinter;

public class Printerproxy implements IPrinter {

    private IPrinter printer ;
    private int port;
    private String host;



    public Printerproxy(IPrinter printer,int port){
        this.port=port;
        this.printer= printer;
        this.host = new String("127.0.0.1");
    }


    @Override
    public boolean print(String docName) throws RemoteException {
       

        boolean result = false;

        try {


            Socket socket = new Socket(host, port);


            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());


            dataOutputStream.writeUTF(docName);

            dataOutputStream.flush();

            result= dataInputStream.readBoolean();
    
    
    
            socket.close();
    
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return result;
    }




}
