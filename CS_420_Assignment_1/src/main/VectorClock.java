package main;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VectorClock extends Remote {
	int[] getClock() throws RemoteException;
	void increment(int processId) throws RemoteException;
	void update(int[] remoteClock) throws RemoteException;
}