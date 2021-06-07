package ctci.string;

import java.util.HashMap;
import java.util.Map;

public class P1_5 {
    /*
        PS: Check if 2 strings are one or zero edits away from becoming equals

        Approach: iterate through the biggest string and build a charact count hashmap... then iterate through the
                smallest string and the sum of what's left in the hashmap should be the solution to this problem.



        BCR: O(max(s,t))



     */
    public boolean oneAway(String s, String t){
        String big, small;
        if(s.length() > t.length()){
            big = s;
            small = t;
        }else{
            big = t;
            small = s;
        }
        big = big.toLowerCase(); // O(N)
        small = small.toLowerCase(); // O(N)

        Map<Character, Integer> charCountMap = new HashMap<>();
        for(var i = 0; i < big.length(); i++){ // O(N)
            var c = big.charAt(i);
            charCountMap.put(c, charCountMap.getOrDefault(c,0)+1);
        }

        for(var i = 0; i < small.length(); i++){ // O(N)
            var c = small.charAt(i);
            if(charCountMap.containsKey(c)){
                var val = charCountMap.get(c) -1;
                if(val == 0)
                    charCountMap.remove(c);
                else
                    charCountMap.replace(c, val);
            }
        }

        int amountLeft = 0;
        for(var entry : charCountMap.entrySet()){ // O(N)
            amountLeft += entry.getValue();
        }
        return amountLeft < 2;
    }

    public static void main(String[] args) {
        var instance = new P1_5();

        assert instance.oneAway("pale", "ple");
        assert instance.oneAway("pale", "pele");
        assert instance.oneAway("pale", "Pele");
        assert !instance.oneAway("pl", "pele");
        assert instance.oneAway("pl", "p");
        assert instance.oneAway("p", "pl");
        assert instance.oneAway("p", "");
        assert instance.oneAway("p", " ");
        assert instance.oneAway("p ", " ");
        assert !instance.oneAway("pale", "bake");

    }
}
