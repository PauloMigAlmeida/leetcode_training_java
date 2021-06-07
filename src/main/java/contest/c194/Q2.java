package contest.c194;

import java.util.*;

public class Q2 {

    public String[] getFolderNames(String[] names) {
        Map<String, Integer> generated = new HashMap<>();
        String[] retArr = new String[names.length];
        int retPointer = 0;

        for(int i = 0; i < names.length; i++){
            if(!generated.containsKey(names[i])){
                generated.put(names[i],0);
                retArr[retPointer++] = names[i]; // happy path
            }else{
                int nextIdx = generated.getOrDefault(names[i], 0) + 1;
                while (true){
                    String tmpName = names[i] + String.format("(%d)",nextIdx);
                    if(!generated.containsKey(tmpName)){
                        retArr[retPointer++] = tmpName;
                        generated.replace(names[i], nextIdx);
                        generated.put(tmpName, 0);
                        break;
                    }
                    nextIdx++;
                }
            }
        }

        return retArr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Q2().getFolderNames(new String[]{"gta", "gta(1)", "gta", "gta(2)", "avalon"}))); // ["gta","gta(1)","gta(2)", "get(2)(1),"avalon"]
        System.out.println(Arrays.deepToString(new Q2().getFolderNames(new String[]{"wano","wano","wano","wano"}))); // ["wano","wano(1)","wano(2)","wano(3)"]
        System.out.println(Arrays.deepToString(new Q2().getFolderNames(new String[]{"kaido","kaido(1)","kaido","kaido(1)","kaido(2)"}))); // ["wano","wano(1)","wano(2)","wano(3)"]

    }
}
