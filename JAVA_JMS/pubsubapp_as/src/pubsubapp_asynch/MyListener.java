package pubsubapp_asynch;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage mymsg= (TextMessage) message;
        System.out.println("Receiving messages...");

        try {
            String msg= mymsg.getText();
            System.out.println("I am a subsriber. I read the folliwing message ->"+ msg);
            System.out.println("The int property of the message is: " + mymsg.getIntProperty("propInt"));
            
        } catch (JMSException e) {
            e.printStackTrace();
        }

        

    }
    
}
