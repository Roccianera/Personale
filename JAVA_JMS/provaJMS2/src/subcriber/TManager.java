package subcriber;

import coda.Coda;

public class TManager extends Thread {
    
    private Coda coda;
    private String cmdString;
    
    public TManager (Coda coda , String message){
        this.cmdString = message;
        this.coda = coda;
    }

    @Override
    public void run() {



                    
        int cmd;

        if(cmdString.compareTo("startSensor")==0)
            cmd =0;
        else if (cmdString.compareTo("stopSensor")==0)
            cmd= 1;
        else 
            cmd= 2;

        coda.inserisci(cmd);

        
    }

}


