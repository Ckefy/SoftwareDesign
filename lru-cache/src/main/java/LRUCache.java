import java.util.ArrayList;
import java.util.HashMap;

public class LRUCache<K, V> {

    //null <- first (recently used) -> next -> next -> last (to delete) -> null
    private CachedElement<K, V> first, last;
    private int cap, size;
    private HashMap <K, CachedElement<K, V>> elements;

    static class CachedElement<K, V> {

        private CachedElement<K, V> prev, next;
        private K key;
        private V value;

        CachedElement(K key, V value, CachedElement<K, V> prev, CachedElement<K, V> next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        CachedElement(K key, V value) {
            this(key, value, null, null);
        }
    }

    LRUCache(int cap) {
        assert cap >= 1: "Capacity must be greater than 0";
        this.cap = cap;
        this.size = 0;
        this.first = this.last = null;
        this.elements = new HashMap<>();
    }

    public int size() {
        return this.size;
    }

    public int cap() {
        return this.cap;
    }

    public ArrayList<K> getAllKeys() {
        ArrayList<K> list = new ArrayList<>();
        CachedElement<K, V> currentElement = this.first;
        while (currentElement != null) {
            list.add(currentElement.key);
            currentElement = currentElement.next;
        }
        return list;
    }

    public V put(K key, V value) {
        if (elements.containsKey(key)) {
            CachedElement<K, V> element = elements.get(key);
            V oldValue = element.value;
            element.value = value;
            moveToFirst(element);
            assert this.first == element: "Updated element in cache must be first";
            return oldValue;
        }
        if (size == cap) {
            remove(this.last.key);
        }
        CachedElement<K, V> newElement = new CachedElement<>(key, value);
        insertFirst(newElement);
        assert this.first == newElement: "New element in cache must be first";
        elements.put(key, newElement);
        int oldSize = size;
        size++;
        assert size == oldSize + 1;
        return null;
    }

    public V remove(K key) {
        if (elements.containsKey(key)) {
            CachedElement<K, V> removed = elements.get(key);
            eraseFromList(removed);
            elements.remove(key);
            int oldSize = size;
            size--;
            assert size == oldSize - 1;
            return removed.value;
        }
        return null;
    }

    public V get(K key) {
        if (elements.containsKey(key)) {
            CachedElement<K, V> element = elements.get(key);
            moveToFirst(element);
            assert this.first == element: "When element was looked up, it must become first in cache";
            return element.value;
        }
        return null;
    }

    private void insertIntoList(CachedElement<K, V> element) {
        element.prev = null;
        if (size == 0) {
            element.next = null;
            this.first = this.last = element;
            return;
        }
        element.next = this.first;
        this.first.prev = element;
        this.first = element;
    }

    private void eraseFromList(CachedElement<K, V> element) {
        CachedElement<K, V> prev = element.prev;
        CachedElement<K, V> next = element.next;
        if (prev == null && next == null) {
            this.first = this.last = null;
        } else if (prev == null) {
            assert next.prev == element;
            this.first = next;
            next.prev = null;
        } else if (next == null) {
            assert prev.next == element;
            this.last = prev;
            prev.next = null;
        } else {
            assert next.prev == element;
            prev.next = next;
            next.prev = prev;
        }
    }

    private void moveToFirst(CachedElement<K, V> element) {
        eraseFromList(element);
        insertIntoList(element);
    }

    private void insertFirst(CachedElement<K, V> element) {
        insertIntoList(element);
    }
}
