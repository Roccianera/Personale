package magazzino;

import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import coda.Coda;

public class MagazzinoThread extends Thread {

    private Coda coda;
    private MapMessage mapMessage;
    private QueueConnection queueConnection;
    

    public MagazzinoThread(Coda coda , MapMessage mapMessage, QueueConnection queueConnection){
        this.coda= coda;
        this.mapMessage= mapMessage;
        this.queueConnection = queueConnection;
    }


    @Override
    public void run() {

        try {
            

            String  operation= mapMessage.getString("operazione");
            int val = mapMessage.getInt("valore");


            if( operation.compareTo("deposita")==0){
                System.out.println("[MAGAZZINO-THREAD]: operazione = " +operation
                + " , valore = " + val);
                coda.inserisci(val);
                
            }else{

                /*
				 * Nel caso in cui l'operazione è di prelievo, prelevo l'id_articolo e lo mando al client che mi
				 * ha fatto richiesta. Sfrutto il metodo getJMSReplyTo per rispondere esattamente al client
				 * che mi ha fatto la richiesta (nel client userò il metodo setJMSReplyTo() per settare la proprietà
				 * JMSReplyTo). 
				 */

                System.out.println("[MAGAZZINO-THREAD]: operazione = "+  operation );
                val = coda.preleva();
            

                /*
				 * Attenzione, la sessione la devo creare dentro il Thread perchè la session 
				 * è sempre single-threaded. Se creassi la sessione fuori il thread potrei
				 * avere problemi di concorrenza
 				*/

                QueueSession queueSession = queueConnection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE );
                

                				/*
				 * Il metodo getJMSReplyTo consente ad un receiver di ottenere
				 * il riferimento ad una Destination (in questo caso la coda 'Risposta'
				 * impostata dal Client) scelta da un sender.
				 * 
				 *  NOTA: Si veda anche setJMSReplyTo() nel Client
				 * 
				 */
                
                 QueueSender queueSender = queueSession.createSender((Queue)mapMessage.getJMSReplyTo());

                 				
				/*
				 * Creazione-invio di un MapMessage che restituisce al Client 
				 * l'id numerico nel caso di una richiesta di tipo preleva. 
				 */

                 MapMessage reply = queueSession.createMapMessage();
                 
                reply.setString("operazione", operation);
                reply.setInt("valore", val);
                
                queueSender.send(reply);

                System.out.println("[MAGAZZINO-THREAD] mesaggio inviato --> "+ val);
                queueSender.close();
                queueSession.close();



            }







        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    
}
