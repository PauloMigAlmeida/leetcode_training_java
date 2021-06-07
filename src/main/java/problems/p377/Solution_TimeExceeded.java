package problems.p377;

class Solution_TimeExceeded {
    public int combinationSum4(int[] nums, int target) {
        int total = 0;
        for(int i = 0; i < nums.length; i++){
            total += helper(nums, target, nums[i]);
        }
        return total;
    }

    private int helper(int[] nums, int target, int actual){
        if(actual == target) return 1;
        else{
            int total = 0;
            for(int i = 0; i < nums.length; i++){
                if((actual + nums[i]) <= target)
                    total += helper(nums, target, actual + nums[i]);
            }
            return total;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().combinationSum4(new int[]{1,2,3}, 4));
        System.out.println(new Solution_TimeExceeded().combinationSum4(new int[]{2,1,3}, 35));
    }
}