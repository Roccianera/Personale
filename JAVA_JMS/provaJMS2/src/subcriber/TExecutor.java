package subcriber;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import coda.Coda;

public class TExecutor extends Thread{

    private Coda coda;
    public TExecutor(Coda coda){
        this.coda=coda;
    }


    private static final int TIME_INTERVAL_OF_CLEAN =10000;
    @Override
    public void run() {
        // dovrei fare una cosa di Stringe ma non ho voglia la faccio dopo nel caso ;

        try {
            
            while (true) {
     
                while (!coda.empty()) {
                    
                    int choice=coda.preleva();


                    String cmd = new String();
                    
                    if(choice ==0)
                        cmd="startSensor";
                    else if(choice==1)
                        cmd="stopSensor";
                    else
                        cmd="read";
                    

                    FileWriter fw = new FileWriter("CmdLog.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw); 
                    PrintWriter pw = new PrintWriter(bw); 
                    
                    pw.println(cmd);
                    pw.flush();
                    
                    pw.close();
                    bw.close();
                    fw.close();
                    
                }

                System.out.println("[TExecutor] coda pulita");

                Thread.sleep(TIME_INTERVAL_OF_CLEAN);
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
   




    }
    
}
