package ctci.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P1_2 {
    /*
        BF:
            Assumptions:
                -   strings have the same length
            Brief Desc:
                - Generate all permutations of s and check for the existence of t in the list
            Time complexity: O(N!)
            Space complexity: O(N!)

         BCR: O(N) -> I'm not using max(s,t) because if strings have different length than by definition s can't be a
            permutation of t and vice versa

         Optimised:
            V1: O(2 * n log n) + O(2n) -> O(n log n)
                Brief Desc: Sort both strings and check if they are equals
            V2: O(N)
                Brief: iterate through s and build hashmap; iterate through t and decrement/remove entries.
     */
    public boolean isPermutationV1(String s, String t){

        if(s.length() != t.length())
            return false;

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }

    public boolean isPermutationV2(String s, String t){
        if(s.length() != t.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);

            if(!map.containsKey(c))
                return false;
            else{
                int val = map.get(c) - 1;
                if(val == 0)
                    map.remove(c);
                else
                    map.put(c, val);
            }
        }

        return map.size() == 0;
    }


    public static void main(String[] args) {
        var instance = new P1_2();
        assert instance.isPermutationV1("aba","aab");
        assert instance.isPermutationV1("baa","aab");
        assert instance.isPermutationV1("aba","aba");
        assert !instance.isPermutationV1("aba","aba1");
        assert instance.isPermutationV1("","");


        assert instance.isPermutationV2("aba","aab");
        assert instance.isPermutationV2("baa","aab");
        assert instance.isPermutationV2("aba","aba");
        assert !instance.isPermutationV2("aba","aba1");
        assert instance.isPermutationV2("","");
    }
}
