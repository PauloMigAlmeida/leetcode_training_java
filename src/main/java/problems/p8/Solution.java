package problems.p8;

import java.util.Stack;

public class Solution {
    public int myAtoi(String str) {
        Stack<Character> stack = new Stack<>();

        boolean foundFirstChar = false;
        for(int i =0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == ' '){
                if(foundFirstChar){
                    break; // Reconstruir o numero
                }
            }else if (Character.isDigit(c)){
                if(!foundFirstChar)
                    foundFirstChar = true;
                stack.push(c);
            }else if (c == '-' || c == '+'){
                if(!foundFirstChar){
                    foundFirstChar = true;
                    stack.push(c);
                }else
                    break;
            }else{
                break;
            }
        }

        if(!stack.isEmpty()){
            int total = 0;
            int times = 0;
            while(!stack.isEmpty()){
                char c = stack.pop();
                if(Character.isDigit(c)){
                    if(times == 0)
                        total = c - '0';
                    else{
                        try{
                            total = Math.addExact(total, Math.multiplyExact((c - '0'), (int)Math.pow(10,times)));
                        }catch (ArithmeticException e){
                            return stack.search('-') != -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                        }

                    }
                    times++;
                }else{
                    if(c == '-')
                        try{
                            total = Math.multiplyExact(total, -1);
                        }catch (ArithmeticException e){
                            return Integer.MIN_VALUE;
                        }
                }
            }
            return (int)total;
        }

        return 0;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().myAtoi("4193 with words"));
        System.out.println(new Solution().myAtoi("-6147483648"));
    }
}
