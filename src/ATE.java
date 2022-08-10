import java.util.concurrent.ConcurrentLinkedQueue;

public class ATE extends Thread {

    private final ConcurrentLinkedQueue<Call> queue;
    private static final int WAITING = 1000;
    private static final int CALLS = 60;

    public ATE(ConcurrentLinkedQueue<Call> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        if (isInterrupted()) return;
        for (int i = 1; i <= CALLS; i++) {
            Call call = new Call(i);
            queue.add(call);
            System.out.println("Поступил новый вызов");
            try {
                sleep(WAITING);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentThread().interrupt();
    }
}