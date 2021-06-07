package problems.p303;

import java.util.Arrays;

public class Solution {

    // Optimal solution
    private int[] dp;

    public Solution(int[] nums) {
        dp = new int[nums.length];
        if(nums.length > 0){
            dp[0] = nums[0];
            for(int i = 1; i < nums.length; i++){
                dp[i] = dp[i-1] + nums[i];
            }
        }
    }

    public int sumRange(int i, int j) {
        if(i == 0)
            return dp[j];
        else{
            //  [-2, 0, 3, -5, 2, -1] -> Input given as example
            //  [-2, -2, 1, -4, -2, -3] -> Sum of each element from 0 to n
            /*
                Note for future Paulo:
                You took a good amount of time to realise that you had to subtract dp[i-1] rather than dp[i]...Not sure if this is a math issue or if this seems to be a pattern for DP problems
             */
            return dp[j] - (dp[i-1]);
        }
    }

    /*
    // Slow Solution -> Attempt to do DP wrong
    private int[][] dp;
    public Solution(int[] nums) {
        dp = new int[nums.length][nums.length];
        if(nums.length > 0){
            for(int i = 0; i < nums.length; i++){
                for(int j = i; j < nums.length; j++){
                    if(j == i){
                        dp[i][j] = nums[j];
                    }else{
                        dp[i][j] = dp[i][j-1] + nums[j];
                    }

                }
            }
        }
    }

    public int sumRange(int i, int j) {
        return dp[i][j];
    }*/

    public static void main(String[] args) {
        Solution s = new Solution(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(s.sumRange(0,2));
        System.out.println(s.sumRange(2,5));
        System.out.println(s.sumRange(0,5));
    }
}
