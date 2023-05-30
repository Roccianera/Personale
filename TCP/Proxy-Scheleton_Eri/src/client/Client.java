package client;

import service.ICounter;

public class Client {

    public static void main(String[] args) {
        /* 
        String host = args[0];
        String operation= args[1];
        */
        String host ="127.0.0.1";
        String operation = "get";

        System.out.println("[Client] HOST : " +host);
        System.out.println("[Client] operation : "+ operation );

        if(!operation.equals("sum")&&
            !operation.equals("get")&&
            !operation.equals("sqr")&&
            !operation.equals("inc")){
                
                System.out.println("Commando ERRATO! \n Commandi accettati :  sum <value> , get , sqr ,inc ");
                return;                

            }

        ICounter counter = new CounterProxy(host);
    
        switch (operation) {
            case "sum":

                try {
                    
                    int value = Integer.parseInt(args[2]);
                    counter.sum(value);


                } catch (NumberFormatException e) {
                    System.out.println("ERRORE l' argomento deve essere un numero ");
                }
                
                break;
        
            case "get":

                System.out.println("Valore letto : "+ counter.get());


                break;
            
            case "sqr":
                
                counter.square();

                break;

            case "inc" :

                counter.inc();

                break;
            
        }

        



    }

    
}
