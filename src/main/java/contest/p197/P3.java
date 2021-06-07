package contest.p197;

import java.util.*;

public class P3 {

    /*
        Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
        Output: 0.25000
        Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
     */

    /*
        Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
        Output: 0.30000
     */
    static class Holder {
        int node;
        Map<Integer, Double> connections;

        public Holder(int node, Map<Integer, Double> connections) {
            this.node = node;
            this.connections = connections;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Holder holder = (Holder) o;
            return node == holder.node ;
        }

        @Override
        public int hashCode() {
            return Objects.hash(node);
        }

        @Override
        public String toString() {
            return "Holder{" +
                    "node=" + node +
                    ", connections=" + connections +
                    '}';
        }
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Holder> map = new HashMap<>(n);
        for (int i = 0; i < edges.length; i++) {
            if(!map.containsKey(edges[i][0])){
                Map<Integer,Double> temp = new HashMap<>();
                temp.put(edges[i][1],succProb[i]);
                map.put(edges[i][0], new Holder(edges[i][0],temp)); //source
            }else{
                Holder holder = map.get(edges[i][0]);
                holder.connections.put(edges[i][1],succProb[i]);
                map.replace(edges[i][0], holder);
            }

            //target
            if(!map.containsKey(edges[i][1])){
                Map<Integer,Double> temp = new HashMap<>();
                map.put(edges[i][1], new Holder(edges[i][1],temp)); //target
            }

        }

        if(map.containsKey(start) && map.containsKey(end))
            return dfs(map.get(start),0.0, map.get(end), map);
        else
            return 0.0;
    }

    private double dfs(Holder o, double probSoFar, Holder target, Map<Integer, Holder> map){
        if(o.node == target.node)
            return probSoFar;
        else if(o.connections.isEmpty()){
            return Integer.MIN_VALUE;
        }
        else{
            double max = 0.0;
            for (Map.Entry<Integer, Double> entry : o.connections.entrySet()) {
                Holder newHolder = map.get(entry.getKey());
                double tmp = dfs(
                        newHolder,
                        (probSoFar == 0.0) ? entry.getValue() : probSoFar * entry.getValue(),
                        target,
                        map
                );
                max = Math.max(max, tmp);
            }
            return max;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new P3().maxProbability(
//                3,
//                new int[][]{
//                        {0, 1},
//                        {1, 2},
//                        {0, 2}
//                },
//                new double[]{0.5, 0.5, 0.2},
//                0,
//                2
//        ));
//        System.out.println(new P3().maxProbability(
//                3,
//                new int[][]{
//                        {0, 1},
//                        {1, 2},
//                        {0, 2}
//                },
//                new double[]{0.5, 0.5, 0.3},
//                0,
//                2
//        ));

        /**
         * 5
         * [
           {2,3},
           {1,2},
           {3,4},
           {1,3},
           {1,4},
           {0,1},
           {2,4},
           {0,4},
           {0,2}
         * ]
         * [0.06,0.26,0.49,0.25,0.2,0.64,0.23,0.21,0.77]
         * 0
         * 3
         */
//        System.out.println(new P3().maxProbability(
//                5,
//                new int[][]{
//                        {2,3},
//                        {1,2},
//                        {3,4},
//                        {1,3},
//                        {1,4},
//                        {0,1},
//                        {2,4},
//                        {0,4},
//                        {0,2}
//                },
//                new double[]{0.06,0.26,0.49,0.25,0.2,0.64,0.23,0.21,0.77},
//                0,
//                3
//        ));

        /*
            5
            [
                {1,4},
                {2,4},
                {0,4},
                {0,3},
                {0,2},
                {2,3}
            ]
            [0.37,0.17,0.93,0.23,0.39,0.04]
            3
            4

         */
        System.out.println(new P3().maxProbability(
                5,
                new int[][]{
                        {1,4},
                        {2,4},
                        {0,4},
                        {0,3},
                        {0,2},
                        {2,3}
                },
                new double[]{0.37,0.17,0.93,0.23,0.39,0.04},
                3,
                4
        ));


//        System.out.println(new P3().maxProbability(
//                3,
//                new int[][]{
//                        {0, 1}
//                },
//                new double[]{0.5},
//                0,
//                2
//        ));
    }
}
