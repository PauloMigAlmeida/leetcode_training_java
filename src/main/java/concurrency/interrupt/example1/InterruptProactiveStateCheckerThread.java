package concurrency.interrupt.example1;

public class InterruptProactiveStateCheckerThread implements Runnable {

    private final String[] messages = {"do", "re", "mi", "fa", "sol", "la", "si"};

    @Override
    public void run() {
        System.out.println(getClass().getSimpleName() + " " + Thread.currentThread().toString() + " entering run()");

        while (true) {
            for (var m : messages) {
                System.out.println(getClass().getSimpleName() + " " + Thread.currentThread().toString() + " " + m);

                // Take away home.. if nothing is doing the interrupt monitor for you, then you gotta do it yourself
                if (Thread.interrupted()) {
                    System.out.println(getClass().getSimpleName() + " " + Thread.currentThread().toString() + " leaving run()");
                    return;
                }
            }
        }

    }
}
