package contest.c202;

public class P2 {
    public int minOperations(int n) {
        int[] arr = new int[n];
        int sum = 0;
        int minOps = 0;

        for(int i=0; i < n; i++){
            arr[i] = (2*i) + 1;
            sum += arr[i];//////
        }

        int avg = sum / n;
        for(int i = 0; i < (n/2); i++){
            minOps += Math.abs(avg - arr[i]);
        }

        return minOps;

    }

    public static void main(String[] args) {
        System.out.println(new P2().minOperations(6));
        System.out.println(new P2().minOperations(3));
        System.out.println(new P2().minOperations(9));
    }
}
