package main;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Arrays;

public class RemoteProcessImpl extends UnicastRemoteObject implements RemoteProcess {
    private VectorClock vectorClock;
    private int processId;

    public RemoteProcessImpl(int processId, int processCount) throws RemoteException {
        this.processId = processId;
        vectorClock = new VectorClockImpl(processCount);
    }

    @Override
    public int[] getVectorClock() throws RemoteException {
        return vectorClock.getClock();
    }

    @Override
    public void sendEvent(int eventId, String data, RemoteProcess targetProcess) throws RemoteException {
        // Increment the vector clock for the current process
        vectorClock.increment(processId);

        // Get the updated vector clock
        int[] localClock = vectorClock.getClock();
        System.out.println("Process " + processId + " sending event " + eventId + " with data: " + data);
        System.out.println("Current Vector Clock: " + Arrays.toString(localClock));

        // Send the event and clock to the target process
        int[] targetClock = targetProcess.getVectorClock();
        targetProcess.updateClockFromRemote(localClock);

        // Update local clock after the event is sent
        vectorClock.update(targetClock);
    }

    // This is to handle receiving the vector clock update
    public void updateClockFromRemote(int[] remoteClock) throws RemoteException {
        vectorClock.update(remoteClock);
        System.out.println("Process " + processId + " updated vector clock: " + Arrays.toString(vectorClock.getClock()));
    }
}
