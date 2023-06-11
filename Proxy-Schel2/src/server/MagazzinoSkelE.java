package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

import interfaccia.IMaggazzino;

public abstract class MagazzinoSkelE  implements IMaggazzino{
    

    void runSkeleton(){


            try {

                ServerSocket serverSocket = new ServerSocket(8000);

                System.out.println("Server acceso ...");

                while (true) {
                    Socket socket =  serverSocket.accept();
                    ThreadMagazzino magazzinoThread = new ThreadMagazzino(socket, this);
                    magazzinoThread.start();
                    
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        


        
    }


    void runSkeletonUDP(){


        try {
            
            DatagramSocket socketServer = new DatagramSocket(8000);

            System.out.println("Server acceso...");


            while (true) {

                DatagramSocket socket = socketServer
                
            }







        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }

    }






}
