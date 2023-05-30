package client;

import interfaccia.IMaggazzino;

public class ClientA {


    public static void main(String[] args) {
        
        
        IMaggazzino maggazzino = new MagazzinoProxy("127.0.0.1",8000);    
        for (int i = 0; i < 5; i++) {
            ThreadClientA threadClientA = new ThreadClientA(maggazzino);
            threadClientA.start();
        }



    }
    
}
