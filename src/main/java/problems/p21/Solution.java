package problems.p21;

public class Solution {

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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ListNode tmp = l1.next;
                l1.next = null;
                helper(tmp, l2, l1);
                return l1;
            } else {
                ListNode tmp = l2.next;
                l2.next = null;
                helper(l1, tmp, l2);
                return l2;
            }
        }else if (l1 != null){
            return l1;
        }else return l2;
    }

    private void helper(ListNode l1, ListNode l2, ListNode curr) {
        if (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ListNode tmp = l1.next;
                l1.next = null;
                curr.next = l1;
                helper(tmp, l2, curr.next);

            } else {
                ListNode tmp = l2.next;
                l2.next = null;
                curr.next = l2;
                helper(l1, tmp, curr.next);
            }
        } else if (l1 != null) {
            ListNode tmp = l1.next;
            l1.next = null;
            curr.next = l1;
            helper(tmp, l2, curr.next);
        } else if (l2 != null) {
            //l2 != null
            ListNode tmp = l2.next;
            l2.next = null;
            curr.next = l2;
            helper(l1, tmp, curr.next);
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        ListNode ret = new Solution().mergeTwoLists(l1, l2);

        do {
            System.out.print(ret.val + "->");
        } while ((ret = ret.next) != null);
        System.out.println();
    }
}
