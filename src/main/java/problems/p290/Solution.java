package problems.p290;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /*
        You may assume pattern contains only lowercase letters,
        and str contains lowercase letters that may be separated by a single space.
     */

    public boolean wordPattern(String pattern, String str) {
        if(pattern.length() == 0 && str.length() == 0)
            return true;
        else if(pattern.length() == 0 || str.length() == 0)
            return false;

        var words = str.split("\\s");
        if(pattern.length() != words.length)
            return false;
        else{
           Map<Character, String> charMap = new HashMap<>();
           Map<String, Character> wordMap = new HashMap<>();
           for(var i = 0; i < words.length; i++){
               var w  = words[i];
               char c = pattern.charAt(i);

               if(!charMap.containsKey(c) && !wordMap.containsKey(w)){
                   charMap.put(c, w);
                   wordMap.put(w, c);
               }else{
                   var tmpCharValue = charMap.get(c);
                   if( (tmpCharValue != null && !tmpCharValue.equals(w)) || wordMap.get(w) != c)
                       return false;
               }
           }
           return true;
        }

    }

    public static void main(String[] args) {
        var instance = new Solution();
        assert instance.wordPattern("abba", "dog cat cat dog");
        assert !instance.wordPattern("abba", "dog cat cat fish");
        assert !instance.wordPattern("aaaa", "dog cat cat dog");
        assert !instance.wordPattern("abba", "dog dog dog dog");
        assert !instance.wordPattern("abba", "");
        assert !instance.wordPattern("", "efasdf asda");
        assert instance.wordPattern("", "");
        /*

         Example 1:

            Input: pattern = "abba", str = "dog cat cat dog"
            Output: true
            Example 2:

            Input:pattern = "abba", str = "dog cat cat fish"
            Output: false
            Example 3:

            Input: pattern = "aaaa", str = "dog cat cat dog"
            Output: false
            Example 4:

            Input: pattern = "abba", str = "dog dog dog dog"
            Output: false

         */
    }
}
