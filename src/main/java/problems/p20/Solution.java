package problems.p20;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {

    // Hash table that takes care of the mappings.
    Map<Character, Character> mappings = Map.of(')', '(','}', '{', ']', '[');

    public boolean isValid(String s) {
        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // If the current character is a closing bracket.
            if (mappings.containsKey(c)) {
                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();
                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != mappings.get(c)) return false;
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }

    public static void main(String[] args) {

    }
}

