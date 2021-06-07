package problems.p983;

public class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[366];
        int lastTimeBought7Day = -1,lastTimeBought30Day = -1;
        for(int i =0; i < days.length; i++){
            int travelDay = days[i];
            if(i == 0) {
                lastTimeBought7Day = lastTimeBought30Day = i;
                dp[travelDay] = min(costs[0],costs[1],costs[2]);
            }else{
                /* new int[]{
                        4,5,9,11,14,16,17,19,21,22,24
                   }
                */
                dp[travelDay] = min(
                        dp[days[i-1]] + costs[0] , //1
                        dp[days[lastTimeBought7Day]] + costs[1] ,
//                        lastTimeBought7Day > 0 ? dp[days[lastTimeBought7Day-1]] + costs[1] + min(costs[0],costs[1],costs[2]) : Integer.MAX_VALUE ,//7
                        dp[days[lastTimeBought30Day]] + costs[2]  //30
//                        lastTimeBought30Day > 0 ? dp[days[lastTimeBought30Day-1]] + costs[2] + min(costs[0],costs[1],costs[2])  : Integer.MAX_VALUE  //30
                );

                if((travelDay - days[lastTimeBought7Day]) >= 7)
                    lastTimeBought7Day = i;

                if((travelDay - days[lastTimeBought30Day]) >= 30)
                    lastTimeBought30Day = i;
            }
        }
        return days.length == 0 ? 0 : dp[days[days.length -1]];
    }

    private int min(int ...args){
        int minValue = Integer.MAX_VALUE;
        for(int i : args){
            minValue = Math.min(minValue, i);
        }
        return minValue;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().mincostTickets(
                new int[]{1,4,6,7,8,20},
                new int[]{2,7,15}
        ));
        System.out.println(new Solution().mincostTickets(
                new int[]{1,2,3,4,5,6,7,8,9,10,30,31},
                new int[]{2,7,15}
        ));
        System.out.println(new Solution().mincostTickets(
                new int[]{1},
                new int[]{2,7,15}
        ));

        System.out.println(new Solution().mincostTickets(
                new int[]{},
                new int[]{2,7,15}
        ));


        System.out.println(new Solution().mincostTickets(
                new int[]{1,4,6,7,8,20},
                new int[]{7,2,15}
        ));

        System.out.println(new Solution().mincostTickets(
                new int[]{4,5,9,11,14,16,17,19,21,22,24},
                new int[]{1,4,18}
        ));

        /*
            [1,4,6,9,10,11,12,13,14,15,16,17,18,20,21,22,23,27,28]
            [3,13,45]
         */
        System.out.println(new Solution().mincostTickets(
                new int[]{1,4,6,9,10,11,12,13,14,15,16,17,18,20,21,22,23,27,28},
                new int[]{3,13,45}
        ));


    }
}
