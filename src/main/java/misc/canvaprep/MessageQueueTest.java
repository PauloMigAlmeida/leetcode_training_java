package misc.canvaprep;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MessageQueueTest {
    static void threadMessage(String message) {
        System.out.printf("%s: %s\n", Thread.currentThread().getName(), message);
    }

    private static class MessageQueue<T> {
        private final Deque<T> data;
        private int capacity;

        public MessageQueue(int capacity) {
            this.data = new LinkedList<>();
            this.capacity = capacity;
        }

        void enqueue(T element) throws InterruptedException {
            synchronized (this) {
                while (this.data.size() == capacity)
                    wait();
                data.addLast(element);
                threadMessage("enqueue: " + element);
                notifyAll();
            }
        }

        T dequeue() throws InterruptedException {
            synchronized (this) {
                while (this.data.size() == 0)
                    wait();

                T ret = data.pollFirst();
                threadMessage("dequeued: " + ret);
                notifyAll();
                return ret;
            }
        }

        int getSize() {
            return this.data.size();
        }

    }

    private static class Producer implements Runnable {
        private MessageQueue<Integer> queue;

        Producer(MessageQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(10);
                    queue.enqueue(i);
                }
                threadMessage("I am done");
            } catch (InterruptedException ex) {
                threadMessage("I wasn't done yet");
            }
        }
    }

    private static class Consumer implements Runnable {
        private MessageQueue<Integer> queue;

        private Consumer(MessageQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(10);
                    Integer ret = queue.dequeue();
                }
                threadMessage("I am done");
            } catch (InterruptedException ex) {
                threadMessage("I wasn't done yet");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var queue = new MessageQueue<Integer>(5);

        var producerCounter = 50;
        var consumerCounter = 50;

        var executorService = Executors.newFixedThreadPool(producerCounter + consumerCounter);

        for (int i = 0; i < producerCounter; i++)
            executorService.execute(new Producer(queue));
        for (int i = 0; i < consumerCounter; i++)
            executorService.execute(new Consumer(queue));

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                threadMessage("Giving up.. killing threads now");
                executorService.shutdownNow();
            } else
                assert queue.getSize() == 0;

        }
    }

}
