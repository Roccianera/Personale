package magazzino;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;

import coda.Coda;

public class MagazzinoListener  implements MessageListener  {

    private Coda coda;
    private QueueConnection queueConnection;

    public MagazzinoListener(Coda coda,QueueConnection queueConnection){
        this.coda = coda;
        this.queueConnection= queueConnection;
    }

    @Override
    public void onMessage(Message message) {

        MapMessage mm= (MapMessage) message;
        MagazzinoThread mt = new MagazzinoThread(coda,mm,queueConnection);

        mt.start();
    }

    


    
}
