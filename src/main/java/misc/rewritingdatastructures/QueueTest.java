package misc.rewritingdatastructures;

public class QueueTest {

    static class Node<T>{
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    static class Queue<T>{
        private Node<T> linkedList, lastNode;
        private int size;

        public Queue(){
        }

        public void offer(T element){
            if(isEmpty()){
                this.linkedList = new Node<>(element);
                this.lastNode = this.linkedList;
            }else{
                this.lastNode.next = new Node<>(element);
                this.lastNode = this.lastNode.next;
            }
            this.size++;
        }

        public T poll(){
            if(!isEmpty()){
                Node<T> ret = linkedList;
                linkedList = linkedList.next;
                this.size--;
                return ret.value;
            }else {
                throw new RuntimeException("The queue is empty man..");
            }
        }

        public T peek(){
            if(!isEmpty()){
                return linkedList.value;
            }else {
                throw new RuntimeException("The queue is empty man..");
            }
        }

        public boolean isEmpty(){
            return this.size == 0;
        }
    }

    public static void main(String[] args) {
        int maxElements = 5000;
        Queue<Integer> queue = new Queue<>();
        assert queue.isEmpty();

        for(int i = 0; i < maxElements; i++){
            queue.offer(i);
        }

        assert !queue.isEmpty();
        assert queue.size == maxElements;

        for(int i = 0; i < maxElements; i++){
            int val = queue.poll();
            assert val == i;
        }

        assert queue.isEmpty();

    }

}
