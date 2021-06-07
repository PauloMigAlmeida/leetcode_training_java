package problems.p50;


import java.util.Date;

class Solution {

    /*
        Takeaway: ALWAYS test for the most extreme limits
        Ex.: -2^31 -> 2^31
        ..Test if when using abs if you can get it the way you wanted..
     */
    public double myPow(double x, int n) {
        return helper(x, n);
    }

    public double helper(double x, long n){
        double ret;
        if(n == 0){
            ret =  1.0;
        }else if(n == 1){
            ret =  x;
        }else if(n > 0){
            ret = helper(x,n/2);
            ret *= ret;
            if(n % 2 != 0){
                ret *= x;
            }

        }else{
            ret =  1/helper(x,n*-1);
        }
        return ret;
    }

    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(new Solution().myPow(2, Integer.MAX_VALUE/2));
        System.out.println(new Solution().myPow(
                1.00000,
                -2147483648
        ));
        System.out.println(new Date().getTime() - now.getTime());
    }
}
