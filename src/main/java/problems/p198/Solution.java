package problems.p198;

public class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0)
            return 0;
        else if(nums.length == 1)
            return nums[nums.length-1];

        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            // 1 - 3 - 4
            // 0 - 2 - 3
            // (i) + (2 || 3)
            // (i -1) + (1 || 2)
            if(i < 2){
                dp[i] = nums[i];
            }else if(i == 2){
                dp[i] = dp[i-2] + nums[i];
            }else{
                dp[i] = Math.max(
                        dp[i-2] + nums[i],
                        dp[i-3] + nums[i]
                );
            }
        }

        return Math.max(dp[nums.length-1],dp[nums.length-2]);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rob(new int[]{ //11
                2,7,9,3,1
        }));
        System.out.println(new Solution().rob(new int[]{ //14
                2,0,1,12,1
        }));
        System.out.println(new Solution().rob(new int[]{ //523
                2,0,9,12,1,5,8,99,10,100,1,5,6,300
        }));
    }
}
