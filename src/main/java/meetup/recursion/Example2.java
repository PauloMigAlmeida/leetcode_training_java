package meetup.recursion;


public class Example2 {

    public int recur(int n) {
        // 1. base case
        if (n == 0) return n;
        // 2. work towards base case
        // 3. recursive part.
        return n + recur(n - 1);
    }

    // sum all the numbers between 1 to n.

    public static void main(String[] args) {
        var instance = new Example2();
        System.out.println(instance.recur(3));

    }
}
