package contest.c194;

import java.util.Objects;

public class Q1 {


    public int xorOperation(int n, int start) {
        int[] arr = new int[n];
        for(int i =0; i < arr.length; i++){
            arr[i] = start + 2*i;
        }

        int result = 0;
        for(int i =0; i < arr.length; i++){
            if(i == 0)
                result = arr[i];
            else
                result = result ^ arr[i];
        }

        return result;
    }

    public static void main(String[] args) {
//        System.out.println(new Q1().method());
    }
}
