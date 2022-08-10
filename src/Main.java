import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.sleep;

public class Main {

    private static final int WAITING = 3000;

    public static void main(String[] args) {

        ConcurrentLinkedQueue<Call> queue = new ConcurrentLinkedQueue<>();

        Thread threadATE = new ATE(queue);

        Thread operator1 = new OperatorThread(queue);
        operator1.setName("Оператор 1");
        Thread operator2 = new OperatorThread(queue);
        operator2.setName("Оператор 2");
        Thread operator3 = new OperatorThread(queue);
        operator3.setName("Оператор 3");

        threadATE.start();
        operator1.start();
        operator2.start();
        operator3.start();

        while (true) {
            if (threadATE.isInterrupted() && queue.isEmpty()) {
                try {
                    sleep(WAITING);
                    operator1.interrupt();
                    operator2.interrupt();
                    operator3.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}