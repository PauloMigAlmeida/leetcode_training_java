package problems.p78;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        set.add(new ArrayList<>()); //empty

        List<Integer> decisionSpace = Arrays.stream(nums).boxed().collect(Collectors.toList());
        for(int i = 0 ; i < decisionSpace.size(); i++){
            List<Integer> current = new ArrayList<>();
            current.add(decisionSpace.get(i));
            set.add(current);

            dfs(new ArrayList<>(current), newDecisionSpace(decisionSpace,i), set);
        }
        return new ArrayList<>(set);
    }

    private List<Integer> newDecisionSpace(List<Integer> source, int removePos){
        List<Integer> ret = new ArrayList<>(source.size() - 1);
        for(int i = 0; i < source.size(); i++){
            if(i > removePos)
                ret.add(source.get(i));
        }
        return ret;
    }

    private void dfs(List<Integer> current, List<Integer> nums, Set<List<Integer>> set){
        for(int i = 0 ; i < nums.size(); i++){
            List<Integer> newCurr = new ArrayList<>(current);
            newCurr.add(nums.get(i));
            set.add(newCurr);

            dfs(new ArrayList<>(newCurr), newDecisionSpace(nums,i), set);
        }
    }

    public static void main(String[] args) {
        System.out.println((new Solution().subsets(new int[]{1,2,3})));
    }
}
