package contest.c203;

import java.util.*;

public class P1 {

    public List<Integer> mostVisited(int n, int[] rounds) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 1; i < rounds.length; i++){
            int tmpI = rounds[i-1];
            while(true){
                if(tmpI != rounds[i]){
                    map.put(tmpI, map.getOrDefault(tmpI,0) + 1);
                    if((tmpI + 1) > n){
                        tmpI = (tmpI+1)  % n;
                        if(tmpI == 0)
                            tmpI =1;
                    }
                    else
                        tmpI++;

                }else{
                    break;
                }

            }
        }

        map.put(rounds[rounds.length - 1], map.getOrDefault(rounds[rounds.length - 1],0) + 1);

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o2) - map.get(o1);
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() != 1){
                queue.offer(entry.getKey());
            }
        };

        List<Integer> ret = new ArrayList<>();
        while(!queue.isEmpty()){
            ret.add(queue.poll());
        }

        return ret;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new P1().mostVisited(4, new int[]{1, 3, 1, 2}).toArray()));
        System.out.println(Arrays.toString(new P1().mostVisited(1, new int[]{2,1,2,1,2,1,2,1,2}).toArray()));
    }
}
