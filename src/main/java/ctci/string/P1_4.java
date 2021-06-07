package ctci.string;

import java.util.HashMap;

public class P1_4 {

    /*
        BF: O(N!) -> Compute all permutations and check if any of them is a palindrome
        Opt: O(N) (lowercase) + O(N log N) (Sort) + O(N) iterate to check for diff > 1 => O(n log n)
        BCR: O(N)
     */

    public boolean hasPalindromePermutation(String s) {
        var tmp = s.toLowerCase();
        var map = new HashMap<Character, Integer>();

        for (var i = 0; i < tmp.length(); i++) {
            var c = tmp.charAt(i);
            if(Character.isLetter(c)){
                var val = map.getOrDefault(c, 0);
                if(val + 1 == 2)
                    map.remove(c);
                else
                    map.put(c, map.getOrDefault(c, 0)+1);
            }
        }

        boolean foundOddValue = false;
        for(var entry : map.entrySet()){
            if(entry.getValue() % 2 != 0)
                if(!foundOddValue)
                    foundOddValue = true;
                else
                    return false;

        }
        return true;
    }


    public static void main(String[] args) {
        var instance = new P1_4();

        assert instance.hasPalindromePermutation("act oATc");
        assert !instance.hasPalindromePermutation("acta oATc");
        assert instance.hasPalindromePermutation("");
        assert instance.hasPalindromePermutation("   a");
        assert instance.hasPalindromePermutation("A   a");
        assert instance.hasPalindromePermutation(" a  ");
        assert instance.hasPalindromePermutation(" aAa  ");
        assert !instance.hasPalindromePermutation(" aAa  bBb");
    }
}
