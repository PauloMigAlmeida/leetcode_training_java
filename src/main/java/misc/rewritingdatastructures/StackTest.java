package misc.rewritingdatastructures;


public class StackTest {
    @SuppressWarnings("unchecked")
    static class Stack<T> {
        private T[] values;
        private int size;
        private int pointer;


        public Stack() {
            this.values = (T[]) new Object[16];
            this.pointer = -1;
        }

        public void push(T element) {
            this.size++;
            this.values[++pointer] = element;
            resizeIfNeeded();
        }

        public T pop() {
            if(!isEmpty()){
                this.size--;
                T element = this.values[pointer];
                this.values[pointer] = null;
                pointer--;
                return element;
            }else{
                throw new RuntimeException("Stack is empty buddy");
            }
        }

        private void resizeIfNeeded() {
            if(((float)size)/values.length >= 0.75f){
                System.out.println("Resizing to a stack with capacity " + (values.length << 1));
                T[] newValues = (T[]) new Object[values.length << 1];
                System.arraycopy(this.values,0, newValues,0, this.values.length);
                this.values = newValues;
            }
        }

        public boolean isEmpty() {
            return this.size == 0;
        }
    }

    public static void main(String[] args) {
        int maxLength = 10 << 15;
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < maxLength; i++){
            stack.push(i);
        }

        for(int i = maxLength-1; i >= 0; i--){
            int elem = stack.pop();
            assert elem == i;
        }

        stack.pop(); // Expected to return an exception saying that the stack is empty.
    }
}
