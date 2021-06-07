package concurrency.interrupt.example1;

public class InterruptTest {


    public static void main(String[] args) throws InterruptedException {
        System.out.println(InterruptTest.class.getSimpleName() + " " + Thread.currentThread().toString() + " entering main()");

        Thread t1 = new Thread(new InterruptProactiveStateCheckerThread());
        t1.start();
        Thread t2 = new Thread(new InterruptPassiveStateCheckerThread());
        t2.start();

        Thread.sleep(5000);
        t1.interrupt();
        t2.interrupt();

        System.out.println(InterruptTest.class.getSimpleName() + " " + Thread.currentThread().toString() + " leaving main()");

    }
}
