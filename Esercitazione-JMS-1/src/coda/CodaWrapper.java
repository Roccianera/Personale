package coda;

public abstract  class CodaWrapper implements Coda {

    protected Coda coda;

    public CodaWrapper(Coda coda){
        this.coda = coda;

    }

    public boolean empty(){
        return coda.empty();
    }

    public boolean full(){
        return coda.full();
    }

    public int getSize(){
        return coda.getSize();
    }
    
    
}
