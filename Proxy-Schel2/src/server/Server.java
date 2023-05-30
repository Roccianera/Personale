package server;


public class Server {


    public static void main(String[] args) {
        MagazzinoSkelE magazzino = new MagazzinoImpl();
        magazzino.runSkeleton();
    }



    
}
