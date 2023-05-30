import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            // Creazione e avvio del registro RMI sulla porta desiderata
            Registry registry = LocateRegistry.createRegistry(1099);

            // Creazione dell'oggetto remoto
            Calculator calculator = new CalculatorImpl();

            // Registrazione dell'oggetto remoto nel registro
            registry.rebind("CalculatorService", calculator);

            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
