package contest.c193;

import java.util.*;

public class Q2 {

    static class Holder {
        private int value;
        private int key;

        Holder(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public int findLeastNumOfUniqueInts(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.replace(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        List<Holder> holders = new ArrayList<>(map.size());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            holders.add(new Holder(entry.getKey(), entry.getValue()));
        }
        holders.sort(Comparator.comparingInt(o -> o.value));

        if (holders.size() > 0) {
            int pointer = 0;

            for (int i = 0; i < k; i++) {
                Holder h = holders.get(pointer);

                if ((map.get(h.key) - 1) == 0) {
                    map.remove(h.key);
                    pointer++;
                } else
                    map.replace(h.key, (map.get(h.key) - 1));
            }
        }


        return map.size();

    }

    public static void main(String[] args) {
        System.out.println(new Q2().findLeastNumOfUniqueInts(new int[]{4, 3, 1, 1, 3, 3, 2}, 3));
        System.out.println(new Q2().findLeastNumOfUniqueInts(new int[]{5, 5, 4}, 1));
    }
}
