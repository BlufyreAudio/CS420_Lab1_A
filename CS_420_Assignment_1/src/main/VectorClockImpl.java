package main;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Arrays;

public class VectorClockImpl extends UnicastRemoteObject implements VectorClock {
    private int[] clock;
    private int processCount;

    public VectorClockImpl(int processCount) throws RemoteException {
        this.processCount = processCount;
        clock = new int[processCount];
        Arrays.fill(clock, 0);
    }

    @Override
    public int[] getClock() throws RemoteException {
        return clock;
    }

    @Override
    public void increment(int processId) throws RemoteException {
        clock[processId]++;
    }

    @Override
    public void update(int[] remoteClock) throws RemoteException {
        for (int i = 0; i < clock.length; i++) {
            clock[i] = Math.max(clock[i], remoteClock[i]);
        }
    }
}