package main;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            // Lookup remote processes
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            RemoteProcess process1 = (RemoteProcess) registry.lookup("Process1");
            RemoteProcess process2 = (RemoteProcess) registry.lookup("Process2");

            // Send event from process1 to process2
            process1.sendEvent(1, "Hello from Process 1", process2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
