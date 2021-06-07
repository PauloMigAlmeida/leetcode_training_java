package contest.c193;

import java.util.*;

public class Q3 {

    public int minDays(int[] bloomDay, int m, int k) {
        int result = -1;
        int smallest = Integer.MAX_VALUE;
        int biggest = Integer.MIN_VALUE;
        Map<Integer, List<Integer>> map = new HashMap<>(bloomDay.length);
        for(int i=0; i < bloomDay.length; i++){
            if(map.containsKey(bloomDay[i])){
                List<Integer> list = map.get(bloomDay[i]);
                list.add(i);
                map.replace(bloomDay[i], list);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(bloomDay[i], list);
            }
            if(bloomDay[i] > biggest)
                biggest = bloomDay[i];
            if(bloomDay[i] < smallest)
                smallest = bloomDay[i];
        }

        Arrays.fill(bloomDay,0);
        int boquetsLeft = m;
        for(int i = smallest; i <= biggest; i++){
            if(map.containsKey(i)){
                for(int j : map.get(i)){
                    bloomDay[j] = 1;
                }

                int tmp = k;
                boolean first = true;
                int lastFlour = -1;
                boquetsLeft = m;
                for(int j = 0; j < bloomDay.length; j++){
                    boolean valid = false;
                    if(first && bloomDay[j] != 0){
                        first = false;
                        lastFlour = j;
                        valid = true;

                    }else if(bloomDay[j] != 0 && lastFlour == (j-1)){
                        valid = true;
                        lastFlour = j;
                    }else {
                        tmp = k;
                        first = true;
                    }

                    if(valid){
                        tmp--;
                        if(tmp == 0){
                            boquetsLeft--;
                            tmp = k;
                        }
                        if(boquetsLeft == 0){
                            return i;
                        }
                    }

                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Q3().minDays(new int[]{1,10,2,9,3,8,4,7,5,6}, 4, 2));
        System.out.println(new Q3().minDays(new int[]{7,7,7,7,12,7,7}, 2, 3));
        System.out.println(new Q3().minDays(new int[]{1,10,3,10,2}, 3, 1));
        System.out.println(new Q3().minDays(new int[]{1,10,3,10,2}, 3, 2));
    }
}
