package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.ICounter;

public class CounterProxy implements ICounter {

    private int port;
    private String host;

    CounterProxy( String hostName ){

        this.host= hostName;
        this.port = 2500;//  potrei modificarlo affinche trova uno qualunque libero ;
    }

    @Override
    public void inc() {

        try {
            Socket socket=  new Socket(host,port);
            DataOutputStream ostream = new DataOutputStream(socket.getOutputStream());
            

            ostream.writeUTF("inc");
            ostream.flush(); // chiedere a che serve per bene 

            ostream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        
        
    }


    @Override
    public void square() {
     
        try {
            Socket socket=  new Socket(host,port);
            DataOutputStream ostream = new DataOutputStream(socket.getOutputStream());
            

            ostream.writeUTF("sqr");
            ostream.flush(); // chiedere a che serve per bene 

            ostream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        
        
    }

    @Override
    public int get() {
        try {
            Socket socket=  new Socket(host,port);
            DataInputStream istream = new DataInputStream(socket.getInputStream());
            DataOutputStream ostream = new DataOutputStream(socket.getOutputStream());
            
            // devo ricevere un pkt 

            ostream.writeUTF("get");
            ostream.flush(); // chiedere a che serve per bene 

            int value = istream.readInt();
            

            istream.close();
            ostream.close();
            socket.close();

            return value;



        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0 ;
    }

    @Override
    public void sum(int value) {

        try {
            Socket socket=  new Socket(host,port);
            DataOutputStream ostream = new DataOutputStream(socket.getOutputStream());
            

            ostream.writeUTF("sum");
            ostream.writeInt(value);
            ostream.flush(); // chiedere a che serve per bene 
            ostream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }



    
}
