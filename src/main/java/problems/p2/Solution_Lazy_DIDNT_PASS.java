package problems.p2;

public class Solution_Lazy_DIDNT_PASS {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long number1 = readNumber(l1);
        long number2 = readNumber(l2);
        return writeNumber(number1 + number2);
    }

    private long readNumber(ListNode l){
        long total = 0;
        total = l.val;
        long multFactor = 10;
        ListNode tmp = l;
        while((tmp = tmp.next) != null){
            total = (tmp.val * multFactor) + total;
            multFactor *= 10;
        }
        return total;
    }

    private ListNode writeNumber(long number){
        ListNode ret = null;
        ListNode tmp = null;

        if(number > 9){
            while(number > 9){
                long mod = number % 10;
                if(ret == null){
                    ret = new ListNode((int)mod);
                    tmp = ret;
                }else{
                    tmp.next = new ListNode((int)mod);
                    tmp = tmp.next;
                }
                number = number / 10;
            }

            if(number >= 0){
                tmp.next = new ListNode((int)number);
            }
        }else{
            ret = new ListNode((int)number);
        }

        return ret;
    }
}
