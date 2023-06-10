package logger;

import interfaccia.ILogger;

public class WorkerThread extends Thread{


    private ILogger logger;
    private String mess;

    public WorkerThread (ILogger logger, String mess){
        this.logger=logger;
        this.mess= mess;
    }
    

    @Override
    public void run() {
        


        int Dato= Integer.parseInt(mess);

        logger.registraDato(Dato);
        System.out.println("Dato registrato");
        

        
    }
}
