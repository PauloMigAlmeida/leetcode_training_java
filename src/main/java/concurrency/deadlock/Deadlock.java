package concurrency.deadlock;

public class Deadlock {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                            + "  has bowed to me!%n",
                    this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                            + " has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    public static void main(String[] args) {

        /*
            Take away lesson:
            This generates a deadlock because a synchronized method acquires the intrinsic lock for the entire object instance.
            That's why bowBack fails to acquire the lock as it's still held by the bow.

            Intrinsic locks for  static methods apply for the Class since static doesn't belong to any particular instance

            The only way to get around the situation above (apart from giving up this stupid logic og bow/bowBack would
            be to implement synchronized statements blocks for each method and giving a different Object lock

         */

        final Friend alphonse =
                new Friend("Alphonse");
        final Friend gaston =
                new Friend("Gaston");
        new Thread(new Runnable() {
            public void run() { alphonse.bow(gaston); }
        }).start();
        new Thread(new Runnable() {
            public void run() { gaston.bow(alphonse); }
        }).start();
    }
}