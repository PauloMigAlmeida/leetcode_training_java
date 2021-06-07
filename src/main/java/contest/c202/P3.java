package contest.c202;

import java.util.Arrays;

public class P3 {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);

        int left = 0;
        int right = position[position.length - 1];
        int mid, force = Integer.MAX_VALUE;
        while(left <= right){
            mid = left + (right-left)/2;
            int res = check(position, m, mid);
            if(res != -1){
                force = res;
                left = mid+1;
            }else{
                right = mid -1;
            }
        }

        return force;
    }

    private int check(int[] position, int m, int mid){
        int force = Integer.MAX_VALUE;
        int prev = -1;
        for(int i = 0; i < position.length; i++){
            if(i == 0){
                m--;
                prev = i;
            }else if((position[i] - position[prev]) >= mid){
                m--;
                force = Math.min(force, mid);
                prev = i;
            }

            if(m == 0) break;
        }
        return m == 0 ? force : -1;
    }

    public static void main(String[] args) {
//        System.out.println(
//                new P3().maxDistance(new int[]{1,2,3,4,7}, 2)
//        );
        System.out.println(
                new P3().maxDistance(new int[]{1,2,3,4,7}, 3)
        );
        System.out.println(
                new P3().maxDistance(new int[]{1,2,3,4,7}, 4)
        );
        System.out.println(
                new P3().maxDistance(new int[]{5,4,3,2,1,1000000000}, 2)
        );

//        System.out.println(
//                new P3().maxDistance(new int[]{5,4,3,2,1,6}, 2)
//        );
    }
}
