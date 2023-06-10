package generator;

import java.util.Random;

import interfaces.IDispatcher;

public class WorkerThread extends Thread{



    private IDispatcher dispatcher ;
    private static final int TIME_WAIT=5000;
    private static final int N_SET_READING=3;

    
    public WorkerThread(IDispatcher dispatcher){
        this.dispatcher=dispatcher;
    }

    @Override
    public void run() {


        try {
            
            for (int i = 0; i < N_SET_READING; i++) {
                



                Random r = new Random();
                String type = new String();


                if(r.nextInt(50)%2==0)
                    type ="temperatura";
                else 
                    type="pressione";
                

                int value = r.nextInt(51);

                dispatcher.setReading(new Reading(type, value));
                Thread.sleep(TIME_WAIT);            


            }




        } catch (Exception e) {

            e.printStackTrace();
        }







    }



    
}
