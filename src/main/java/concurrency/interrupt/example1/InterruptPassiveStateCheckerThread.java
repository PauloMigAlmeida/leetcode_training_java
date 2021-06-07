package concurrency.interrupt.example1;

public class InterruptPassiveStateCheckerThread implements Runnable {

    private final String[] messages = {"do", "re", "mi", "fa", "sol", "la", "si"};

    @Override
    public void run() {
        System.out.println(getClass().getSimpleName() + " " + Thread.currentThread().toString() + " entering run()");
        try {
            while (true) {
                for (String m : messages) {
                    System.out.println(getClass().getSimpleName() + " " + Thread.currentThread().toString() + " " + m);
                    // When the thread starts to sleep, its state changes from active... so essentially if something
                    // interrupts this thread then the natural flow is for thread.sleep to thrown InterruptedException
                    // Take away lesson: If using `busy waiting` methods like sleep, you will rarely will see
                    // if(Thread.isInterrupted()) on the same piece of code...
                    Thread.sleep(10);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(getClass().getSimpleName() + " " + Thread.currentThread().toString() + " leaving run() - exception");
        }
    }
}
