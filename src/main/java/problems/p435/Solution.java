package problems.p435;

import java.util.Arrays;

class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {
        int ans = 0;
        Arrays.sort(intervals, (a, b) -> {
            return Double.compare(a[1], b[1]);
        });

        /*
            {1, 2},
            {1, 3}
            {2, 3},
            {3, 4},
         */

        int lastValidPointer = 0;
        for(int i = 0; i < intervals.length; i++){
            if(i != 0){
                int[] prev = intervals[lastValidPointer];
                int[] curr = intervals[i];
                if(intersects(prev, curr)){
                    ans++;
                    continue;
                }
            }
            lastValidPointer= i;

        }

        return ans;
    }

    private boolean intersects(int[] int1, int[] int2){
        // 1,3 -> 2,3 = true
        // 2,3 -> 1,3 = true
        return int1[0] >= int2[0] && int1[0] < int2[1] ||
                int2[0] >= int1[0] && int2[0] < int1[1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().eraseOverlapIntervals(
                new int[][]{
                        {1, 2},
                        {2, 3},
                        {3, 4},
                        {1, 3}
                }
        ));

        System.out.println(new Solution().eraseOverlapIntervals(
                new int[][]{
                        {1, 2},
                        {1, 2},
                        {1, 2},
                }
        ));

        System.out.println(new Solution().eraseOverlapIntervals(
                new int[][]{
                        {1, 2},
                        {2, 3},
                }
        ));

        System.out.println(new Solution().eraseOverlapIntervals(
                new int[][]{
                        {1, 2},
                        {1, 3},
                        {2, 4},
                        {4, 6},
                        {4, 5},
                }
        ));
    }
}