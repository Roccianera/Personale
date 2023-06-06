package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class LoggerImpl extends LoggerSkelE {


     public LoggerImpl(int porta){
        super(porta);
     }

     @Override
     public void registraDato(int dato) {
         try {
            
            
            FileWriter file = new FileWriter("doc.txt", true);
            BufferedWriter bw = new BufferedWriter(file);
            PrintWriter printWriter = new PrintWriter(bw);

            printWriter.println(dato);
            printWriter.flush();
            printWriter.close();
            bw.close();
            file.close();

         } catch (Exception e) {
            e.printStackTrace();
         }
     }
    
}
