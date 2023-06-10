package disk;

import javax.jms.MapMessage;

import interfaccia.ILogger;

public class WorkerThread extends Thread {


    private MapMessage message ;


    public WorkerThread(MapMessage message){
        this.message = message;



    }


    @Override
    public void run() {
        
        
        try {
                
            int PORT = message.getInt("porta");
            int DATA = message.getInt("dato");


            System.out.println("Messaggio ricevuto porta "+ PORT+" dato "+ DATA );

            ILogger logger = new ILoggerProxy(PORT);

            logger.registraDato(DATA);

        } catch (Exception e) {
            e.printStackTrace();
        }




        





    }



    
}
