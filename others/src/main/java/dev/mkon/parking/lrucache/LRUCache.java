package dev.mkon.parking.lrucache;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final Map<Integer, Integer> cache;
    private final Deque<Integer> idsUpdateOrder;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = HashMap.newHashMap(capacity);
        this.idsUpdateOrder = new ArrayDeque<>(capacity);
    }

    public void put(Integer key, Integer value) {
        if (cache.containsKey(key)) {
            cache.computeIfPresent(key, (k, v) -> value);
            idsUpdateOrder.add(idsUpdateOrder.removeFirst());
        }

        if (idsUpdateOrder.size() >= capacity) {
            cache.remove(idsUpdateOrder.removeFirst());
        }

        idsUpdateOrder.add(key);
        cache.put(key, value);
    }

    public Integer get(Integer key) {
        return key;
    }


}
