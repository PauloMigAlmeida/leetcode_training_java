package problems.p957;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    // TLE -> Bitwise
//    public int[] prisonAfterNDays(int[] cells, int N) {
//        for(int i = 0; i < N; i++){
//            int[] previousDayCells = Arrays.copyOfRange(cells, 0, cells.length);
//            for(int j = 0; j < cells.length; j++){
//                if(j==0 || j == (cells.length - 1))
//                    cells[j] = 0;
//                else{
//                    cells[j] = ~((previousDayCells[j-1] ^ previousDayCells[j+1])) & 0x01;
//                }
//            }
//        }
//        return cells;
//    }

    public int getState(int[] cells) {
        int ret = 0;
        for (int i = 0; i < cells.length; i++) {
            ret <<= 1;
            ret += cells[i];
        }
        return ret;
    }

    public int[] prisonTomorrow(int[] cells) {
        int[] previousDayCells = Arrays.copyOfRange(cells, 0, cells.length);
        for (int j = 0; j < cells.length; j++) {
            if (j == 0 || j == (cells.length - 1))
                cells[j] = 0;
            else
                cells[j] = ~((previousDayCells[j - 1] ^ previousDayCells[j + 1])) & 0x01;

        }
        return cells;
    }

    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, int[]> statesMap = new HashMap<>(256);
        for (int i = 0; i < N; i++) {
            int state = getState(cells);
            if (statesMap.containsKey(state)){
                if((i + statesMap.size()) < N){
                    i += statesMap.size();
                }
                // TO FAZENDO ALGO errado COM esse state management
            } else statesMap.put(state, prisonTomorrow(cells));
        }
        return cells;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().prisonAfterNDays(new int[]{1,0,0,1,0,0,1,0}, 1000000000))); //[0,0,1,1,1,1,1,0]
    }
}
