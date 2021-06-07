package problems.p276;


public class Solution {
    // There is a fence with n posts, each post can be painted with one of the k colors
    // You have to paint all the posts such that no more than two adjacent fence posts have the same color.

    /*
        Input: n = 3, k = 2
        Output: 6
        Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

                    post1  post2  post3
         -----      -----  -----  -----
           1         c1     c1     c2
           2         c1     c2     c1
           3         c1     c2     c2
           4         c2     c1     c1
           5         c2     c1     c2
           6         c2     c2     c1

     */
    public int numWays(int n, int k) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numWays(3,2));
        System.out.println(new Solution().numWays(4,2));
    }
}
