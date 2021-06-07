package meetup.recursion.backup;

public class Example1 {

    public int recur(int num){
        if(num == 1) return num;
        return num + recur(num - 1);
    }

    public static void main(String[] args) {
        var instance = new Example1();
        System.out.println(String.format("sumOfNumbers(10): %d", instance.recur(10)));
        System.out.println(String.format("sumOfNumbers(3): %d", instance.recur(3)));
    }
}
