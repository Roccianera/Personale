package disk;

import interfaccia.ILogger;

public class Worker  extends Thread{

    
    private int dato ;
    private int porta;
    private ILogger logger;
    public Worker (int dato , int porta , ILogger logger){
        this.dato = dato;
        this.porta = porta;
        this.logger=logger;
    }




    @Override
    public void run() {

        System.out.println("[THREAD-MYL]  Argomenti ricevuti ...  Dato : "+ dato +" Porta: "+porta);
        logger.registraDato(dato);
    }





}
