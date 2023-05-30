package magazzino;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.*;
import javax.naming.*;
// che ha fatto per eliminare il rosso 
public class Magazzino {

    public static void main(String[] args) {
        Hashtable<String,String> p = new Hashtable<String, String >();
        p.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitial");
        p.put(null, null);

        p.put("queue.Richiesta", "Richiesta");
        
        try {
            Context ctx = new InitialContext(p);
            Que
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
}
