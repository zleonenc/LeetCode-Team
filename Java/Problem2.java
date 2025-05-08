import java.util.HashMap;

class LRUCache {
    class Nodo{
        int key, value;
        Nodo prev, next;

        public Nodo(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<Integer, Nodo> map;
    private Nodo head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;    
        this.map = new HashMap<>();

        
        head = new Nodo(0,0);
        tail = new Nodo(0,0);
        head.next = tail;
        tail.prev = head;
    }


    /*
    Removemos el nodo:
    nodo.prev -> nodo <- nodo.next
    nodo.prev <-> nodo.next
     */
    public void remove(Nodo nodo){
        nodo.prev.next = nodo.next;
        nodo.next.prev = nodo.prev;
    }

    public void insertFirst(Nodo nodo){
        nodo.next = head.next;  // nodo.next apunta al que va despues de la cabeza
        nodo.prev = head;       // nodo.prev apunta a la cabeza

        head.next.prev = nodo;  // el (segundo_nodo).prev apunta al nodo
        head.next = nodo;       // cabeza.next apunta a nodo
    }
    
    public int get(int key) {
        int value = -1;
        
        if(map.containsKey(key)){
            Nodo nodo = map.get(key);
            remove(nodo);
            insertFirst(nodo);

            value = nodo.value;
        }

        return value;
    }

    
    public void put(int key, int value) {
        if (map.containsKey(key)){
            remove(map.get(key));
        }

        Nodo nuevo = new Nodo(key, value);
        insertFirst(nuevo);
        map.put(key, nuevo);

        if (map.size() > capacity){
            // remover ultimo
            Nodo ultimo = tail.prev;
            remove(ultimo);
            map.remove(ultimo.key);
        }
    }
}