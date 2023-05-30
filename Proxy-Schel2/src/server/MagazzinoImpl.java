package server;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import coda.Coda;
import codaimpl.CodaCircolare;
import codaimpl.CodaWrapperLock;

public class MagazzinoImpl extends MagazzinoSkelE {


    Coda laptopCoda;
    Coda smartphoneCoda;

    MagazzinoImpl(){

        laptopCoda = new CodaWrapperLock(new CodaCircolare(5));
        smartphoneCoda = new CodaWrapperLock(new CodaCircolare(5));
    }

    @Override
    public int preleva(String articolo) {
        int id =0;
        if(articolo.compareTo("smartphone")==0){

            id= smartphoneCoda.preleva();

        }else if(articolo.compareTo("laptop")==0){
            id = laptopCoda.preleva();
        }


        try {
            String filePath =".\\"+articolo+".txt";
          
            FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw); 
			PrintWriter pw = new PrintWriter(bw); 
			
			pw.println(id);
			pw.flush();
			
			pw.close();
			bw.close();
			fw.close();
    
        } catch (Exception e) {
            e.printStackTrace();
        }










        return id;
    }

    @Override
    public void deposita(String articolo, int id) {

        if(articolo.compareTo("smartphone")==0){

            smartphoneCoda.inserisci(id);
        }else if(articolo.compareTo("laptop")==0){
            laptopCoda.inserisci(id);
        }
        
        
    }
    
}
