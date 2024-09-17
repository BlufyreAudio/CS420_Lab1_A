package main;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Create 2 remote processes for demonstration
            RemoteProcess process1 = new RemoteProcessImpl(0, 2);
            RemoteProcess process2 = new RemoteProcessImpl(1, 2);

            // Bind processes to RMI registry
            Registry registry = LocateRegistry.createRegistry(1099); // default port
            registry.rebind("Process1", process1);
            registry.rebind("Process2", process2);

            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
