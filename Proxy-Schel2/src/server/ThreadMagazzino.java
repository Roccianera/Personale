package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import interfaccia.IMaggazzino;

public class ThreadMagazzino extends Thread {

    private Socket socket;
    private IMaggazzino maggazzino;

    public ThreadMagazzino (Socket socket, IMaggazzino maggazzino){
        this.maggazzino = maggazzino;
        this.socket = socket;
    }


    @Override
    public void run() {

        try {
        

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            
            String operazione = dataInputStream.readUTF();
            System.out.println("operazione letta : " +operazione);
            String articolo = dataInputStream.readUTF();
            System.out.println("articolo letto : " +articolo);



            if(operazione.compareTo("deposita")==0){

             
                int id = dataInputStream.readInt();
        
                maggazzino.deposita(articolo, id);


            }else if(operazione.compareTo("preleva")==0){


                int id = maggazzino.preleva(articolo);
                System.out.println("articolo prelevato : " + articolo + " " +id);
                dataOutputStream.writeInt(id);

            }
            


        } catch (Exception e) {
            e.printStackTrace();
        }












    }



    
}
