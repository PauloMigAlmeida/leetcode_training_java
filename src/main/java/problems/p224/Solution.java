package problems.p224;

import java.util.Stack;

public class Solution {
    public int calculate(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c != ' '){
                if(c == ')'){
                    int operandi1 = 0, operandi2 = 0;
                    boolean operandi1Set = false, operandi2Set= false;
                    char operator = '\0';
                    char temp;
                    while((temp = stack.pop()) != '('){
//                        "(1+(14+5+2)-3)+(6+8)"
                        if(Character.isDigit(temp)){

//                            if(operator == '\0' && !operandi2Set){
//                                operandi2 = temp - '0';
//
//                            }else if(){
//                                operandi2Set = true;
//                            }else{
//                                operandi1 = temp - '0';
//                                operandi1Set = true;
//                            }
                        }else if(temp != ' '){
                            operator = temp;
                        }

                        if(operandi1Set && operandi2Set){
                            if(operator == '+'){
                                pushResultToStack(operandi1 + operandi2, stack);
                            }else{
                                pushResultToStack(operandi1 - operandi2, stack);
                            }

                            //reset
                            operandi1Set = operandi2Set = false;
                        }
                    }
                }else{
                    stack.push(c);
                }
            }
        }

        return stack.pop() - '0';
    }

    public void pushResultToStack(int value, Stack<Character> stack){
        Stack<Character> tempStack = new Stack<>();
        while(value > 10){
            value = value / 10;
            tempStack.push((char) ((value % 10) +'0'));
        }
        if(value > 0)
            tempStack.push((char) (value +'0'));

        while(!tempStack.isEmpty()){
            stack.push(tempStack.pop());
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().calculate("(1+(14+5+2)-3)+(6+8)"));
    }
}
