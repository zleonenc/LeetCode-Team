import java.util.HashMap;

class LRUCache {
    class Node{
        int key, value;
        Node prev, next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head, tail;
    
    
    public LRUCache(int capacity) {
        this.capacity = capacity;    
        this.map = new HashMap<>();

        
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }


    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    public void insertFirst(Node node){
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    
    public int get(int key) {
        int value = -1;
        
        if(map.containsKey(key)){
            Node node = map.get(key);
            remove(node);
            insertFirst(node);

            value = node.value;
        }

        return value;
    }


    public void put(int key, int value) {
        if (map.containsKey(key)){
            remove(map.get(key));
        }

        Node first = new Node(key, value);
        insertFirst(first);
        map.put(key, first);

        if (map.size() > capacity){
            Node last = tail.prev;
            remove(last);
            map.remove(last.key);
        }
    }
}