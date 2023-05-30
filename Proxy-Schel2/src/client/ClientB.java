package client;

import interfaccia.IMaggazzino;

public class ClientB {


    public static void main(String[] args) {
        
        IMaggazzino maggazzino= new MagazzinoProxy("127.0.0.1",8000);

        for (int i = 0; i < 5; i++) {
            ThreadClientB threadClientB = new ThreadClientB(maggazzino);
            threadClientB.start();
        }
    }
    
}
