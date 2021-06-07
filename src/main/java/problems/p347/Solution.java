package problems.p347;

import javax.swing.*;
import java.util.*;

class Solution {

    static class Holder{
        private int n, times;
        Holder(int n){
            this.n = n;
        }
        Holder(int n, int times){
            this.n = n;
            this.times = times;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Holder holder = (Holder) o;
            return n == holder.n;
        }

        @Override
        public int hashCode() {
            return Objects.hash(n);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
//        PriorityQueue<Holder> pQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.times));
        PriorityQueue<Holder> pQueue = new PriorityQueue<>(new Comparator<Holder>() {
            @Override
            public int compare(Holder o1, Holder o2) {
                return 0;
            }
        });
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            upsert(map, pQueue, nums[i]);
        }

        int[] ret = new int[k];
        for(int i =0; i < k; i++){
            ret[i] = pQueue.poll().n;
        }

        return ret;

    }

    private void upsert(Map<Integer, Integer> map, PriorityQueue<Holder> priorityQueue, int n){
        if(!map.containsKey(n)){
            map.put(n,-1);
            priorityQueue.add(new Holder(n,-1));
        }else{
            int total = map.get(n) - 1;
            map.replace(n,total);
            priorityQueue.remove(new Holder(n));
            priorityQueue.add(new Holder(n,total));
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().topKFrequent(new int[]{
                1, 1, 1, 2, 2, 3
        }, 2)));
    }
}