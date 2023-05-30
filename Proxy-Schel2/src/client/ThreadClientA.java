package client;

import java.util.Random;

import interfaccia.IMaggazzino;

public class ThreadClientA  extends Thread{

    IMaggazzino maggazzino;
    public ThreadClientA(IMaggazzino maggazzino){
        this.maggazzino= maggazzino;
    }



    @Override
    public void run() {


        try {
            



            Random r = new Random();
            
            for (int i = 0; i < 3; i++) {
                
                int id = r.nextInt(100);
                if(id%2==0){
                    maggazzino.deposita("laptop", id);
                    System.out.println("Inviato articolo  : laptop  "+ id);

                }else{
                    maggazzino.deposita("smartphone", id);
                    System.out.println("Inviato articolo : smartphone "+ id);

                }
                    

                Thread.sleep(1000*(r.nextInt(3)+2));
                

            }


        } catch (Exception e) {
            e.printStackTrace();
        }




    }








    
}
