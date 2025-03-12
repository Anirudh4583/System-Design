public class Node<K, V> {
    K key;
    V value;

    // pointers
    Node<K, V> prev;
    Node<K, V> next;

    // constructor
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
