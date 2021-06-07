package problems.p35;

import java.util.Date;

public class Solution {

    static int[] buildArray(int size) {
        int[] nums = new int[size];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        return nums;
    }

    public int linearSearchInsert(int[] nums, int target) {
        int i = -1;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
            else if (nums[i] > target)
                return i;
        }
        return i;
    }

    public int binarySearchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;
        int middle;
        while(left <= right){
            middle = left + (right - left)/2;
            if(nums[middle] == target)
                return middle;
            else if (nums[middle] > target)
                right = middle - 1;
            else
                left = middle + 1;
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println("Creating the Array");
        int[] nums = buildArray(Integer.MAX_VALUE - 2);
        System.out.println("Array created");
        int target = Integer.MAX_VALUE - 3;
        Date now;

        Solution s = new Solution();

        now = new Date();
        s.linearSearchInsert(nums, target);
        System.out.println("linearSearchInsert: " + (new Date().getTime() - now.getTime()) + "ms");

        now = new Date();
        s.binarySearchInsert(nums, target);
        System.out.println("binarySearchInsert: " + (new Date().getTime() - now.getTime()) + "ms");

    }
}
