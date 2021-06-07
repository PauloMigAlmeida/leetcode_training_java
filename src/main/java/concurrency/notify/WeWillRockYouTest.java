package concurrency.notify;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class WeWillRockYouTest {

    /*
        synchronized can only be used in these scenarios:
            Instance methods
            Static methods
            Code blocks inside instance methods
            Code blocks inside static methods

          hence, I can't declare a variable that is  'synchronized' BUT I can declare a variable which is volatile
          while they have different purposes, there is some sort of overlapping between them

          synchronized int printedTimes = 0;
     */

    private int printTimes = 0;

    synchronized void incrementPrintTimes() {
        this.printTimes++;
        if(this.printTimes % 2 == 0)
            notifyAll();
    }

    synchronized int getPrintTimes() throws InterruptedException {
        while(this.printTimes == 0 || this.printTimes % 2 != 0)
            wait();
        var ret = this.printTimes;
        this.printTimes = 0;
        return ret;
    }


    static void threadMessage(String message) {
        System.out.printf("%s: %s\n", Thread.currentThread().getName(), message);
    }

    private static class WeWillThread extends Thread {

        /*
         * Take away lesson: I don't need this since I could've transformed the methods above in static method
         * but I kept this way just fo `funsies`
         */
        private WeWillRockYouTest masterInstance;

        public WeWillThread(WeWillRockYouTest masterInstance) {
            this.masterInstance = masterInstance;
        }

        @Override
        public void run() {
            try {
                while(true){
                    masterInstance.incrementPrintTimes();
                    threadMessage("We will...");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                threadMessage("I wasn't done yet");
            }
        }
    }

    private static class RockYouThread extends Thread {
        /*
         * Take away lesson: I don't need this since I could've transformed the methods above in static method
         * but I kept this way just fo `funsies`
         */
        private WeWillRockYouTest masterInstance;

        public RockYouThread(WeWillRockYouTest masterInstance) {
            this.masterInstance = masterInstance;
        }

        @Override
        public void run() {
            try {
                while(true){
                    var printTimes = this.masterInstance.getPrintTimes();
                    threadMessage(String.format("Rock you!!! [printTimes: %d]",printTimes));
                }
            } catch (InterruptedException ex) {
                threadMessage("I wasn't done yet");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var instance = new WeWillRockYouTest();

        var executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new WeWillThread(instance));
        executorService.submit(new RockYouThread(instance));

        while(!executorService.isTerminated()){
            executorService.awaitTermination(10, TimeUnit.SECONDS);

            threadMessage("nahhh... this is taking too long.. I will interrupt those");
            executorService.shutdownNow();
        }
    }
}

