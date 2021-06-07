package problems.p912;

import java.util.Arrays;

public class Solution {

    public int[] sortArray(int[] nums) {
        if(nums.length >= 2){
            //divide
            int pivot = nums.length / 2;
            int[] left = sortArray(Arrays.copyOfRange(nums, 0, pivot));
            int[] right = sortArray(Arrays.copyOfRange(nums, pivot, nums.length));
            return mergeArray(left, right);
        }else{
            return nums;
        }
    }

    public int[] mergeArray(int[] n1, int[] n2){
        int pointer1,pointer2, pointerRet;
        pointer1 = pointer2 = pointerRet = 0;
        int[] ret = new int[n1.length + n2.length];
        while(pointer1 < n1.length || pointer2 < n2.length){
            if(pointer1 == n1.length){
                ret[pointerRet++] = n2[pointer2++];
            }else if(pointer2 == n2.length){
                ret[pointerRet++] = n1[pointer1++];
            }else{
                if(n1[pointer1] < n2[pointer2]){
                    ret[pointerRet++] = n1[pointer1++];
                }else{
                    ret[pointerRet++] = n2[pointer2++];
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().sortArray(new int[]{5, 1, 4, 2, 0, 1, 5 ,7 ,9})));
    }
}
