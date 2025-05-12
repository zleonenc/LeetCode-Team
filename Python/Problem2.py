class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.map = {}

        self.head = Node(0,0)
        self.tail = Node(0,0)

        self.head.next = self.tail
        self.tail.prev = self.head

    def remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def insert_first(self, node):
        node.next = self.head.next
        node.prev = self.head

        self.head.next.prev = node
        self.head.next = node

    def get(self, key: int) -> int:
        value = -1

        if key in self.map:
            node = self.map[key]
            self.remove(node)
            self.insert_first(node)
            value = node.value

        return value


    def put(self, key: int, value: int) -> None:
        if key in self.map:
            self.remove(self.map[key])
        
        first = Node(key, value)
        self.insert_first(first)
        self.map[key] = first

        if len(self.map) > self.capacity:
            last = self.tail.prev
            self.remove(last)
            del self.map[last.key]