package server;


public class CounterImpl extends CounterSkel  {

    private int count;

     public CounterImpl(){
        count=0;
    }

    @Override
    public void inc() {
        count++;
       
    }

    @Override
    public synchronized void sum(int value) {
        count+=value;
    }

    @Override
    public  synchronized int  get() {
        return count;
    }

    @Override
    public synchronized void square() {
        count*=count;
       
    }



    
}
