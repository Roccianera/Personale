package client;

import java.util.Random;

import interfaccia.IMaggazzino;

public class ThreadClientB  extends Thread{

    IMaggazzino maggazzino;
    public ThreadClientB(IMaggazzino maggazzino){
        this.maggazzino= maggazzino;
    }



    @Override
    public void run() {


        try {
            



            Random r = new Random();
            
            for (int i = 0; i < 3; i++) {
                
                int id = r.nextInt(100);
                if(id%2==0){

                    id =maggazzino.preleva("laptop");
                    System.out.println("articolo prelevato : laptop "+ id );

                }else{
                   id = maggazzino.preleva("smartphone");
                   System.out.println("articolo prelevato : smartphone "+ id );

                }

            }               


                
            Thread.sleep(1000*(r.nextInt(3)+2));



        } catch (Exception e) {
            e.printStackTrace();
        }




    }








    
}
