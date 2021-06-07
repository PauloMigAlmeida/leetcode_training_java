package meetup.recursion.backup;

public class Example3 {

    static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public int findValue(Node head, int pos, int value) {
        if (head == null) return -1;
        if (head.value == value) return pos;
        return findValue(head.next, ++pos, value);
    }

    public static void main(String[] args) {
        Node head = new Node(1,
                new Node(2,
                        new Node(3,
                                new Node(4, null))));
        System.out.println(new Example3().findValue(head, 0, 3));
        System.out.println(new Example3().findValue(head, 0, 4));
        System.out.println(new Example3().findValue(head, 0, 5));
        System.out.println(new Example3().findValue(head, 0, 1));

    }


}

