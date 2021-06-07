package contest.p197;


import java.util.*;
import java.util.stream.Collectors;

public class P1 {
    public int numIdenticalPairs(int[] nums) {
//        List<Integer> list = new ArrayList<>();
//        List<Integer> decisionSpace = Arrays.stream(nums).boxed().collect(Collectors.toList());
//
//        for(int i = 0 ; i < decisionSpace.size(); i++){
//            dfs(decisionSpace.get(i), newDecisionSpace(decisionSpace,i), list);
//        }
//        return list.size();

        int count = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                if(i < j && nums[i] == nums[j])
                    count++;
            }
        }
        return count;

    }

//    private List<Integer> newDecisionSpace(List<Integer> source, int removePos){
//        List<Integer> ret = new ArrayList<>(source.size() - 1);
//        for(int i = 0; i < source.size(); i++){
//            if(i > removePos)
//                ret.add(source.get(i));
//        }
//        return ret;
//    }
//
//    private void dfs(int current, List<Integer> nums, List<Integer> set){
//        for(int i = 0 ; i < nums.size(); i++){
//            if(current == nums.get(i))
//                set.add(1);
//            else{
//                dfs(nums.get(i), newDecisionSpace(nums,i), set);
//            }
//        }
//    }

    public static void main(String[] args) {
        System.out.println((new P1().numIdenticalPairs(new int[]{1,2,3,1,1,3})));
        System.out.println((new P1().numIdenticalPairs(new int[]{1,1,1,1})));
        System.out.println((new P1().numIdenticalPairs(new int[]{1,2,3})));
//        System.out.println((new P1().numIdenticalPairs(new int[]{1,2,3,1,1,3})));
    }
}
