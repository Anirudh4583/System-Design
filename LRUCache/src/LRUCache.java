import java.util.*;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final DoublyLinkedList<K, V> dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }

    public synchronized void put(K key, V value) {
        if (cache.containsKey(key)) { // key already present
            Node<K, V> node = cache.get(key);
            node.value = value;
            dll.moveToHead(node);
        } else { // key not present, add
            if (cache.size() >= capacity) { // check capacity
                Node<K, V> tailNode = dll.removeTail();
                if (tailNode != null)
                    cache.remove(tailNode.key);
            }
            Node<K, V> newNode = new Node<>(key, value);
            dll.addHead(newNode);
            cache.put(key, newNode);
        }
    }

    public synchronized V get(K key) {
        if (!cache.containsKey(key))
            return null;
        Node<K, V> node = cache.get(key);
        dll.moveToHead(node);
        return node.value;
    }
}

/*
 * mru, lru -> lookups should be constant => circular ds (doubly-linkedlist)
 * cache to be type agnostic => generics
 */