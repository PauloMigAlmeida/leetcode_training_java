package problems.p53;

public class Solution {
    public int maxSubArray(int[] nums) {
        int max;
        int[] dp = new int[nums.length];
        dp[0] = max = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i-1] + nums[i],nums[i]);
            if(dp[i] > max)
                max = dp[i];
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
