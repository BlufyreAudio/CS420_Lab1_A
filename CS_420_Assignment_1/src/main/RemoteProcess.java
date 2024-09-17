package main;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteProcess extends Remote {
    int[] getVectorClock() throws RemoteException;
    void sendEvent(int eventId, String data, RemoteProcess targetProcess) throws RemoteException;
}
