public class DoublyLinkedList<K, V> {
    private Node<K, V> head; // MRU
    private Node<K, V> tail; // LRU

    public DoublyLinkedList() {
        // sentinels, simply null checks
        head = new Node<>(null, null);
        tail = new Node<>(null, null);

        // circular
        head.next = tail;
        tail.next = head;
    }

    void addHead(Node<K, V> node) {
        // head <-> node <-> head.next
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    Node<K, V> removeTail() {
        if (head.next == tail)
            return null;
        Node<K, V> endNode = tail.prev;
        removeNode(endNode);
        return endNode;
    }

    void removeNode(Node<K, V> node) {
        Node<K, V> prevNode = node.prev;
        Node<K, V> nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    void moveToHead(Node<K, V> node) {
        removeNode(node);
        addHead(node);
    }
}
