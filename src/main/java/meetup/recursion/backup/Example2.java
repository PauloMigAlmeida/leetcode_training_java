package meetup.recursion.backup;

public class Example2 {

    public void print(int[] arr, int idx){
        if(idx < 0 || idx >= arr.length) return;
        System.out.printf("arr[%d]: %d\n", idx, arr[idx]);
        print(arr, ++idx);
    }
    public static void main(String[] args) {
        new Example2().print(new int[]{1,2,3},0);
    }


}

