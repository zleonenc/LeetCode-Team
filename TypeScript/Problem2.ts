class Node {
    key: number;
    value: number;
    prev: Node | null  = null;
    next: Node | null = null;

    constructor(key: number, value: number) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    private capacity: number;
    private map: Map<number, Node>;
    private head: Node;
    private tail: Node;

    constructor(capacity: number) {
        this.capacity = capacity;
        this.map = new Map();

        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    private remove(node: Node): void {
        if (node.prev && node.next) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private insertFirst(node: Node): void {
        node.next = this.head.next;
        node.prev = this.head;
        if (this.head.next) this.head.next.prev = node;
        this.head.next = node;
    }
    
    get(key: number): number {
        if (!this.map.has(key)) return -1;
    
        const node = this.map.get(key)!;
        this.remove(node);
        this.insertFirst(node);
        return node.value;
    }
    
    put(key: number, value: number): void {
        if (this.map.has(key)){
            this.remove(this.map.get(key)!);
        }

        const node = new Node(key, value);
        this.insertFirst(node);
        this.map.set(key, node);

        if (this.map.size > this.capacity) {
            const last = this.tail.prev!;
            this.remove(last);
            this.map.delete(last.key);
        }
    }
}

