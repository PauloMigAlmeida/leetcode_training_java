package concurrency.visibility;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObjectCounterExample {
    private static void threadMessage(String message){
        System.out.printf("%s: %s\n", Thread.currentThread().getName(), message);
    }
    private static class ObjectCounter{
        private static int objectCounter = 0;

        ObjectCounter(){
            synchronized (ObjectCounter.class){
                objectCounter++;
            }
        }

        public static int getObjectCounter() {
            return objectCounter;
        }
    }

    public static void main(String[] args) {
        int threadPoolSize = 500;

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        for(int i=1; i <= threadPoolSize; i++){
            final var tmpI = i;
            executorService.submit(()->{
                ObjectCounter obj = new ObjectCounter();
                int counter = ObjectCounter.getObjectCounter();
                if(tmpI != counter)
                    threadMessage(tmpI + " ObjectCounter.getObjectCounter(): " + counter);
            });
        }

        executorService.shutdown();


    }
}
