package problems.p1482;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length)
            return -1;

        int left = 0;
        int right = (int) 1e9;
        int mid = -1;
        int answer = Integer.MAX_VALUE;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (check(bloomDay, m, k, mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private boolean check(int[] arr, int m, int k, int mid) {
        int c = 0;
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= mid) {
                j++;
                if (j >= k) {
                    c++;
                    j = 0;
                }
            } else {
                j = 0;
            }
        }
        return c >= m;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minDays(
                new int[]{30,49,11,66,54,22,2,57,35},
                3,
                3
        ));

        System.out.println(new Solution().minDays(
                new int[]{1,2,4,9,3,4,1},
                2,
                2
        ));

        System.out.println(new Solution().minDays(
                new int[]{1, 10, 10, 3, 10, 2},
                3,
                1
        ));

        System.out.println(new Solution().minDays(
                new int[]{1, 10, 10, 3, 10, 2},
                3,
                2
        ));

//        System.out.println(new Solution().minDays(
//                new int[]{1, 10, 10, 3, 10, 2},
//                3,
//                3
//        ));
        System.out.println(new Solution().minDays(
                new int[]{1,10,3,10,2},
                3,
                2
        ));

        System.out.println(new Solution().minDays(
                new int[]{7,7,7,7,12,7,7},
                2,
                3
        ));

        System.out.println(new Solution().minDays(
                new int[]{1000000000,1000000000},
                1,
                1
        ));

        System.out.println(new Solution().minDays(
                new int[]{1,10,2,9,3,8,4,7,5,6},
                4,
                2
        ));
    }
}
