package problems.p779;

public class Solution {
    // MLE
    /*
    public int kthGrammar(int N, int K) {
        String ret = helper(1, N, "");
        return ret.charAt(K-1) - '0';
    }

    private String helper(int curr, int n, String rowContent){
        if(curr > n)
            return rowContent;
        else if(curr == 1)
            return helper(++curr, n, "0");
        else{
            StringBuilder sb = new StringBuilder((int)Math.pow(2, curr - 1));
            for(int i=0; i < rowContent.length(); i++){
                char c = rowContent.charAt(i);
                if(c == '0')
                    sb.append("01");
                else
                    sb.append("10");
            }
//            System.out.println("Row: " + curr + " - " + sb.toString());
            return helper(++curr, n, sb.toString());
        }
    }
 */

    //TLE
    /*
    public int kthGrammar(int N, int K) {
        return helper(1, N, new StringBuilder()).charAt(K-1) - '0';
    }

    private StringBuilder helper(int curr, int n, StringBuilder sb){
        if(curr > n)
            return sb;
        else if(curr == 1)
            return helper(++curr, n, sb.append('0'));
        else{
            // StringBuilder sb = new StringBuilder((int)Math.pow(2, curr - 1));
            int size = sb.length();
            for(int i=0; i < size; i++){
                char c = sb.charAt(i);
                if(c == '0')
                    sb.append("01");
                else
                    sb.append("10");
            }
            return helper(++curr, n, sb);
        }

    }
     */

    public int kthGrammar(int N, int K) {
        return helper(1, N, new StringBuilder()).charAt(K-1) - '0';
    }

    private StringBuilder helper(int curr, int n, StringBuilder sb){
        if(curr > n)
            return sb;
        else if(curr == 1)
            return helper(++curr, n, sb.append('0'));
        else{
            // StringBuilder sb = new StringBuilder((int)Math.pow(2, curr - 1));
            int size = sb.length();
            for(int i=0; i < size; i++){
                char c = sb.charAt(i);
                if(c == '0')
                    sb.append("01");
                else
                    sb.append("10");
            }
            return helper(++curr, n, sb);
        }
    }

//    Try to represent the current (N, K) in terms of some (N-1, prevK). What is prevK ?

    public static void main(String[] args) {
        System.out.println(new Solution().kthGrammar(30, 434991989));
    }
}
