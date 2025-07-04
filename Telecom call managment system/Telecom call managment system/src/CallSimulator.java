import com.telecom.model.CallLog;

public class CallSimulator implements Runnable {
    private CallLog callLog;

    public CallSimulator(CallLog callLog) {
        this.callLog = callLog;
    }

    @Override
    public void run() {
        callLog.startCall();
        try {
            Thread.sleep(3000000); // Simulate 3 seconds call
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        callLog.endCall();
        System.out.println("Call ended. Duration: " + callLog.getDurationInSeconds() + " seconds.");
    }
}

