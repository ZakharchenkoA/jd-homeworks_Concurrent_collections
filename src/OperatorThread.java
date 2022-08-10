import java.util.concurrent.ConcurrentLinkedQueue;

public class OperatorThread extends Thread {

    private final ConcurrentLinkedQueue<Call> queue;
    private final static int ANSWER = 3000;

    public OperatorThread(ConcurrentLinkedQueue<Call> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            if (isInterrupted()) return;
            if (!queue.isEmpty()) {
                Call call = queue.poll();
                if (call != null) {
                    System.out.println(currentThread().getName() + " приступил к звонку номер " + call.getId());
                    try {
                        sleep(ANSWER);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(currentThread().getName() + " завершил работу со звонком");
                }
            }
        }
    }
}