package problems.p256;

import java.util.Arrays;

public class Solution {
    public int minCost(int[][] costs) {
        int[][] dp = new int[costs.length][costs[0].length];
        int cost = Math.min(
                Math.min(
                        calcCost(0, 0, costs, dp),
                        calcCost(0, 1, costs, dp))
                , calcCost(0, 2, costs, dp));
//        System.out.println(Arrays.deepToString(dp));
        return cost;

    }

    private int calcCost(int house, int colour, int[][] costs, int[][] dp) {
        if (house == costs.length || colour == costs[0].length)
            return 0; ///base case??? Not sure yet tbh

        if(dp[house][colour] != 0)
            return dp[house][colour];
        else if (colour == 0) {
            dp[house][colour] = costs[house][colour] + Math.min(
                    calcCost(house + 1, colour + 1, costs, dp),
                    calcCost(house + 1, colour + 2, costs, dp)
            );
            return dp[house][colour];
        } else if (colour == 2) {
            dp[house][colour] =  costs[house][colour] + Math.min(
                    calcCost(house + 1, colour - 1, costs, dp),
                    calcCost(house + 1, colour - 2, costs, dp)
            );
            return dp[house][colour];
        } else {
            dp[house][colour] =  costs[house][colour] + Math.min(
                    calcCost(house + 1, colour - 1, costs, dp),
                    calcCost(house + 1, colour + 1, costs, dp)
            );
            return dp[house][colour];
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minCost(new int[][]{
                {17, 2, 17},
                {16, 16, 5},
                {14, 3, 19}
        }));

        System.out.println(new Solution().minCost(new int[][]{
                {17, 2, 17},
//                {16, 16, 5},
//                {14, 3, 19}
        }));

        System.out.println(new Solution().minCost(new int[][]{
                {17, 2, 17},
                {16, 16, 5},
                {14, 3, 19},
                {17, 2, 17},
                {16, 16, 5},
                {14, 3, 19},
                {17, 2, 17},
                {16, 16, 5},
                {14, 3, 19},
        }));
    }
}
