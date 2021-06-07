package problems.p746;

public class Solution {

    public int minCostClimbingStairs(int[] cost) {
        if(cost.length > 0){
            int[] dp = new int[cost.length];
            return Math.min(cost(0,cost, dp),cost(1,cost, dp));
        }
        return cost.length;
    }

    private int cost(int current, int[] cost, int[]dp){
        if(current == cost.length -1)
            return cost[current];
        else if(current >= cost.length)
            return 0;
        else if(dp[current] != 0){
            return dp[current];
        }
        else{
            int cost1 = cost(current + 1,cost, dp);
            int cost2 = cost(current + 2,cost, dp);
            dp[current] = cost[current] + Math.min(cost1, cost2);;
            return dp[current];
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minCostClimbingStairs(new int[]{
                1, 100, 1, 1, 1, 100, 1, 1, 100, 1
        }));

        System.out.println(new Solution().minCostClimbingStairs(new int[]{
                1, 1, 1, 1, 100, 1, 1, 100, 1
        }));

        System.out.println(new Solution().minCostClimbingStairs(new int[]{
                1, 100, 1, 5, 6, 2, 1, 1, 100, 1
        }));

        System.out.println(new Solution().minCostClimbingStairs(new int[]{

        }));
    }
}
