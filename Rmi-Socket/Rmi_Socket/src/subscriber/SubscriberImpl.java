package subscriber;

import intefaccia.ISubscriber;

public class SubscriberImpl implements ISubscriber {


    private int porta;
    private int compenentID;

    public SubscriberImpl(int compenentID , int porta){
        this.compenentID= compenentID;
        this.porta=porta;
    }


    @Override
    public int getPorta() {

        return porta;
    }

    @Override
    public int getComponentID() {

        return  compenentID;
    }




    
}
