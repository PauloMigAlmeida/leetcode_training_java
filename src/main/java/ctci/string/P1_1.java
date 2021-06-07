package ctci.string;

// Determime if the string has all unique characters.. (what if you can't use additional data structures)

import java.util.Arrays;

public class P1_1 {

    // BR: O(n^2)
    // BCR: O(n).. this isn't goona fly because we can't use additional data structures
    // Optimised: O(n Log n + n) -> Sorting is predominant -> O(n log n)
    public boolean isUnique(String str){
        if(str.length() < 2)
            return true;

        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        for(int i = 1; i < chars.length; i++){
            if(chars[i] == chars[i-1])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        P1_1 instance = new P1_1();
        assert instance.isUnique("abcd");
        assert !instance.isUnique("abcdd");
        assert instance.isUnique("AabcdD");
        assert !instance.isUnique("AabcdDd");
        assert instance.isUnique("");
        assert instance.isUnique("a");
        assert !instance.isUnique("aa");
        assert instance.isUnique("aA");
    }
}
