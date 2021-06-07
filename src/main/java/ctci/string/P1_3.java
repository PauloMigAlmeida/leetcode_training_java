package ctci.string;

import java.util.Arrays;

//takeaway lesson: it's often the easiest to change strings from the end to the beginning
public class P1_3 {

    /*
        Approach:
            BF: Couldn't think of any... most likely because it requires things to be done in place.

            BCR: O(N)
     */

    public void urlify(char[] src, int trueLength) {
        int rightPointer = src.length - 1;
        boolean espaceToggle = true;
        for (int i = src.length - 1; i >= 0; i--) {
            char c = src[i];

            if(c != ' '){
                if(espaceToggle){
                    espaceToggle = false;
                }
                if(rightPointer == i)
                    break;

                src[rightPointer--] = c;
                src[i] = ' ';

            }else{
                if(!espaceToggle)
                    rightPointer -= 3;
                espaceToggle = true;
            }
        }

        for(int i=0; i < src.length; i++){
            char c = src[i];
            if(c == ' '){
                src[i] = '%';
                src[i+1] = '2';
                src[i+2] = '0';
            }
        }

    }

    public static void main(String[] args) {
        var instance = new P1_3();

        var charArray = "Mr John Smith    ".toCharArray();
        instance.urlify(charArray, 13);
        assert Arrays.equals(charArray, "Mr%20John%20Smith".toCharArray());

        charArray = " Mr John Smith      ".toCharArray();
        instance.urlify(charArray, 13);
        assert Arrays.equals(charArray, "%20Mr%20John%20Smith".toCharArray());

    }
}
