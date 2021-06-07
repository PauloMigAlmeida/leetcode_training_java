package concurrency.interrupt.example2;

public class MessageLoopExample {
    static void threadMessage(String message) {
        System.out.printf("%s: %s\n", Thread.currentThread().getName(), message);
    }

    private static class MessageLoopThread extends Thread {
        @Override
        public void run() {
            String[] importantInfo = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };

            try {
                for (var info : importantInfo) {
                    Thread.sleep(4000);
                    threadMessage(info);
                }
                threadMessage("I finished!!!");
            } catch (InterruptedException e) {
                threadMessage("I wasn't done yet");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        long patience = 4000 * 2;
        var startTime = System.currentTimeMillis();

        var loopThread = new MessageLoopThread();
        loopThread.start();

        threadMessage("Launched MessageLoopThread thread");

        while (loopThread.isAlive()){
            threadMessage("Still waiting for loopThread to finish");
            /*
            Take away lesson: join(mill) waits for a specific time for the thread to finish willingly before giving up
             */
            loopThread.join(1000);

            if(System.currentTimeMillis() - startTime > patience){
                threadMessage("Tired of waiting... Will have to force the thread to interrupt");

                loopThread.interrupt();
                threadMessage("Shouldn't take long..");
                loopThread.join(); // as we have requested the thread to be interrupted...this shouldn't take long
                threadMessage("interrupted loopThread successfully");
            }
        }
    }
}
