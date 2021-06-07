package mock.google;

import java.util.*;

public class P2 {

    static class Holder {
        private int node;
        private int target;
        private int time;

        Holder(int node,int target, int time) {
            this.node = node;
            this.target = target;
            this.time = time;
        }

        public int hashCode() {
            return Objects.hash(this.node,this.target, this.time);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Holder holder = (Holder) o;
            return node == holder.node &&
                    target == holder.target &&
                    time == holder.time;
        }

        @Override
        public String toString() {
            return "Holder{" +
                    "node=" + node +
                    ", target=" + target +
                    ", time=" + time +
                    '}';
        }
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        int requiredTime = 0;
        Map<Integer, List<Holder>> map = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int node = times[i][0];

            if (map.containsKey(node)) {
                List<Holder> tmp = map.get(node);
                tmp.add(new Holder(node,times[i][1], times[i][2]));
                map.replace(node, tmp);
            } else {
                List<Holder> tmp = new ArrayList<>();
                tmp.add(new Holder(node,times[i][1], times[i][2]));
                map.put(node, tmp);
            }
        }

        Set<Integer> visited = new HashSet<>();

        if(!map.containsKey(K)){
            return -1;
        }
        List<Holder> initialNodeList = map.get(K);
        Queue<Holder> queue = new LinkedList<>(initialNodeList);
        int maxTime = 0;
        Holder rootNode = null;
        while(!queue.isEmpty()){
            Holder h = queue.poll();
            if(rootNode == null)
                rootNode = h;
           if(!visited.contains(h.node)){
               visited.add(h.node);

               if(h.node == rootNode.node){
                   if(h.time > maxTime)
                       maxTime = h.time;
               }else{
                   map.remove(rootNode.node);

                   rootNode = h;
                   requiredTime += maxTime;
                   if(!visited.contains(h.target)) {
                       maxTime = h.time;
                   }else{
                       maxTime = 0;
                   }

               }

               List<Holder> tmp = map.get(h.target);
               if(tmp != null)
                   tmp.forEach(queue::offer);
           }
        }

        if(rootNode != null){
            map.remove(rootNode.node);
            requiredTime += maxTime;
        }


        return map.size() == 0 ? requiredTime : -1;
    }

    public static void main(String[] args) {
        System.out.println(new P2().networkDelayTime(new int[][]{
                        {1, 2, 1},
                        {2,1,3},

                }, 1, 2
        ));
        System.out.println(new P2().networkDelayTime(new int[][]{
                        {2, 1, 1},
                        {2, 3, 1},
                        {3, 4, 1}

                }, 4, 2
        ));


//        [[1,2,1],[2,1,3]]
//        2
//        2
    }
}
