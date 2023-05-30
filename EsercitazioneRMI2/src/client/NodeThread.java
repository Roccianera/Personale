package client;

import java.util.Random;

import interfaccie.IDispatcher;

public class NodeThread  extends Thread{


    private IDispatcher dispatcher;

    public NodeThread(IDispatcher dispatcher){
        
        this.dispatcher =dispatcher;


    }


    @Override
    public void run() {
     

        for (int i = 0; i < 3; i++) {
            
            Random r = new Random();
            Integer randomInteger = r.nextInt(51)+1;

            try {


                
                System.out.println("Richiesta con esito " +dispatcher.printRequest("doc"+randomInteger.toString())); 
                Thread.sleep(3000);





            } catch (Exception e) {
                e.printStackTrace();
            }


            
        }
    }
    
}
