package generator;

import interfaces.IReading;

public class Reading  implements IReading{


    private String type;
    private int value;



    public Reading(String type, int value){
        this.type=type;
        this.value=value;
    }








    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getValue() {
        return value;
    }




    
}
