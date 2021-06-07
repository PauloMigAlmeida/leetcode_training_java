package misc.rewritingdatastructures;

public class HashFunctionTest {

    static class Node<K,V>{
        K key;
        V value;

        Node<K,V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    static class MyHashMap<K,V>{
        int size;
        Node<K,V>[] values;

        @SuppressWarnings("unchecked")
        MyHashMap(){
            this.values = (Node<K,V>[]) new Node[16];
            this.size = 0;
        }

        public void put(K key, V value){
            int idx = hash(key);
            if(values[idx] == null){
                values[idx] = new Node<>(key,value);
            }else{
                Node<K,V> node = values[idx];
                do{
                    if(node.key.equals(key)){
                        node.value = value; // replace
                        break;
                    }else if(node.next == null){
                        node.next = new Node<>(key, value);
                        break;
                    }

                }while((node = node.next) != null);
            }
            this.size++;
            resizeIfNeeded();
        }

        public V get(K key){
            int idx = hash(key);
            if(values[idx] != null){
                Node<K,V> node = values[idx];
                do{
                    if(node.key.equals(key))
                        return node.value;
                }while ((node = node.next) != null);
            }
            return null;
        }

        private int hash(K key){
            return (key.hashCode() & Integer.MAX_VALUE) % this.values.length;
        }

        @SuppressWarnings("unchecked")
        private void resizeIfNeeded(){
            if(((float)size)/this.values.length >= 0.75f){
                System.out.println("Resizing...");

                this.size = 0;
                Node<K,V>[] olValues = this.values;
                this.values = (Node<K,V>[]) new Node[olValues.length << 1];
                for(Node<K,V> entry : olValues){
                    if(entry != null){
                        this.put(entry.key, entry.value);

                        if(entry.next != null){
                            Node<K,V> tmp = entry.next;
                            do{
                                this.put(tmp.key, tmp.value);
                            }while((tmp = tmp.next) != null);
                        }

                    }
                }
            }
        }

        public boolean containsKey(K key){
            int h = hash(key);
            if(values[h] != null){
                Node<K,V> tmp = values[h];
                do{
                    if(tmp.key.equals(key))
                        return true;
                }while((tmp = tmp.next) != null);
            }
            return false;
        }

        public void remove(K key){
            int h = hash(key);
            if(values[h] != null){
                Node<K,V> tmp = values[h];
                do{
                    if(tmp.key.equals(key)){
                        values[h] = tmp.next;
                    }else if(tmp.next != null && tmp.next.key.equals(key)){
                        tmp.next = tmp.next.next;
                    }
                }while((tmp = tmp.next) != null);
            }
            this.size--;
        }

        public int getSize() {
            return size;
        }
    }

    public static int hashCode(byte[] value) {
        int h = 0;
        for (byte v : value) {
            h = 31 * h + (v & 0xff);
        }
        return h;
    }



    public static void main(String[] args) {

        int LOAD_TEST = 500;
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();

        hashMap.put("Tatiana", 1);
        hashMap.put("Mr Ack", 2);
        hashMap.put("Paulo", 3);

        for(int i = 0; i < LOAD_TEST; i++){
            hashMap.put(String.valueOf(i), i);
        }

        for(int i = 0; i < LOAD_TEST; i++){
            if(hashMap.containsKey(String.valueOf(i))){
                Integer ret = hashMap.get(String.valueOf(i));
                if(ret == null ||  ret != i){
                    System.out.println("Deu Ruim buscando " + i);
                }
            }else{
                System.out.println("Deu Ruim checando " + i);
            }
        }

        for(int i = 0; i < LOAD_TEST; i++){
            hashMap.remove(String.valueOf(i));
        }

        for(int i = 0; i < LOAD_TEST; i++){
            if(hashMap.containsKey(String.valueOf(i))){
                System.out.println("Deu Ruim checando " + i);
            }
        }

        assert hashMap.getSize() == 3;
    }
}
