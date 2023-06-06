package printer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import interfaccie.IPrinter;

public class Worker extends Thread {
    

    private Socket socket ;
    private IPrinter printer;

    public Worker(Socket socket , IPrinter printer ){
        this.socket =socket;
        this.printer= printer;

    }




    @Override
    public void run() {

        try {
            DataInputStream dataInputStream= new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());


            String docName = dataInputStream.readUTF();
            
            dataOutputStream.writeBoolean(printer.print(docName));
        

            dataInputStream.close();
            dataOutputStream.close();
            socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
           

    }







}
