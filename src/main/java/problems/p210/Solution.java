package problems.p210;

import java.util.*;

public class Solution {
    /*
        Input: 4, [[1,0],[2,0],[3,1],[3,2]]
        Output: [0,1,2,3] or [0,2,1,3]
        Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

     */
    private Map<Integer, List<Integer>> buildMap(int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>(prerequisites.length);

        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int dependency = prerequisites[i][1];

            if (!map.containsKey(dependency)) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(course);
                map.put(dependency, tmp);
            } else {
                List<Integer> tmp = map.get(dependency);
                tmp.add(course);
                map.replace(dependency, tmp);
            }

            if (!map.containsKey(course)) {
                List<Integer> tmp = new ArrayList<>();
                map.put(course, tmp);
            }
        }

        return map;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = buildMap(prerequisites);

        Set<Integer> visited = new HashSet<>();
        List<Integer> ret = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if(!visited.contains(entry.getKey())){
                boolean movedAtLeastOnce = dfs(numCourses, map, entry.getKey(), visited, ret);
                if(movedAtLeastOnce && ret.size() != numCourses)
                    return new int[0];
            }
        }

        return ret.stream().mapToInt(i->i).toArray();
    }

    private boolean dfs(int numCourses, Map<Integer, List<Integer>> map, int startVertice, Set<Integer> visited, List<Integer> ret) {
        if(!visited.contains(startVertice) && ret.size() != numCourses){
            visited.add(startVertice);

            List<Integer> dependentCourses = map.get(startVertice);
            if(dependentCourses.isEmpty()){
                //this is the final dest perhaps?
                ret.add(startVertice);
            }else{
                boolean movedAtLeastOnce = false;
                for(Integer course : dependentCourses){
                    if(!visited.contains(course)){
                        movedAtLeastOnce = true;
                        dfs(numCourses, map, course, visited,ret);
                    }
                }
                ret.add(0,startVertice);
                return movedAtLeastOnce;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().findOrder(
                2,
                new int[][]{
                        {1,0}
                }
        )));
        System.out.println(Arrays.toString(new Solution().findOrder(
                4,
                new int[][]{
                        {1, 0},
                        {2, 0},
                        {3, 1},
                        {3, 2}
                }
        )));
        System.out.println(Arrays.toString(new Solution().findOrder(
                4,
                new int[][]{
                        {1, 0},
                        {2, 1},
                        {0, 1},
                        {3, 1}
                }
        )));
        System.out.println(Arrays.toString(new Solution().findOrder(
                5,
                new int[][]{
                        {1, 0},
                        {2, 1},
                        {0, 1},
                        {4, 3}
                }
        )));
    }
}
