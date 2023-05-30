package printer;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Worker extends Thread {
    

    private Socket socket ;

    public Worker(Socket socket){
        this.socket =socket;

    }




    @Override
    public void run() {
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                String response= dataInputStream.readUTF();  
                System.out.println("[CALLBACK] Printer aggiunto : " + response);
                socket.close();
            } catch (IOException e) {
         
                e.printStackTrace();
            }
    }







}
