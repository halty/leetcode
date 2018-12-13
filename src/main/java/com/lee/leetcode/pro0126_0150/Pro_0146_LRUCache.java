package com.lee.leetcode.pro0126_0150;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 Design and implement a data structure for Least Recently Used (LRU) cache.
 It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present.
                   When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache(2);  // capacity
 cache.put(1, 1);
 cache.put(2, 2);
 cache.get(1);       // returns 1
 cache.put(3, 3);    // evicts key 2
 cache.get(2);       // returns -1 (not found)
 cache.put(4, 4);    // evicts key 1
 cache.get(1);       // returns -1 (not found)
 cache.get(3);       // returns 3
 cache.get(4);       // returns 4
 *
 */
public class Pro_0146_LRUCache {

    public static void main(String[] args) {
        Pro_0146_LRUCache cache = new Pro_0146_LRUCache(2);  // capacity
        cache.put(1, 1);
        cache.put(2, 2);
        print(cache.get(1));    // returns 1
        cache.put(3, 3);        // evicts key 2
        print(cache.get(2));    // returns -1 (not found)
        cache.put(4, 4);        // evicts key 1
        print(cache.get(1));    // returns -1 (not found)
        print(cache.get(3));    // returns 3
        print(cache.get(4));    // returns 4
    }

    private static <T> void print(T t) { System.out.println(t); }

    /*private int capacity;
    private Entry[] entries;
    private int count;
    private Entry head, tail;


    public Pro_0146_LRUCache(int capacity) {
        int size = entrySizeFor(capacity);
        this.capacity = capacity;
        this.entries = new Entry[size];
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    private static int entrySizeFor(int capacity) {
        int maxSize = 1 << 30;
        int n = capacity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= maxSize) ? maxSize : n + 1;
    }

    public int get(int key) {
        Entry e = getEntry(key);
        if(e == null) { return -1; }
        transferToTail(e);
        return e.value;
    }

    private Entry getEntry(int key) {
        for(Entry e = entries[indexFor(key)]; e != null; e=e.next) {
            if(e.key == key) { return e; }
        }
        return null;
    }

    private int indexFor(int key) {
        return key & (entries.length - 1);
    }

    private void transferToTail(Entry e) {
        if(e == tail) { return; }
        if(e == head) {
            head = e.after;
            head.before = null;
        }else {
            Entry before = e.before;
            Entry after = e.after;
            before.after = after;
            after.before = before;
        }
        tail.after = e;
        e.before = tail;
        e.after = null;
        tail = e;
    }

    public void put(int key, int value) {
        Entry e = getEntry(key);
        if(e != null) {
            e.value = value;
            transferToTail(e);
        }else {
            if(count == capacity) {
                evict();
            }else {
                count++;
            }
            addEntry(key, value);
        }
    }

    private void evict() {
        Entry e = head;
        if(head == tail) {
            head = tail = null;
        }else {
            head = e.after;
            head.before = null;
        }
        Entry prev = e.prev;
        if(prev != null) {
            Entry next = e.next;
            prev.next = next;
            if(next != null) {
                next.prev = prev;
            }
        }else {
            int index = indexFor(e.key);
            Entry next = e.next;
            entries[index] = next;
            if(next != null) {
                next.prev = null;
            }
        }
    }

    private void addEntry(int key, int value) {
        Entry e = new Entry(key, value);
        int index = indexFor(key);
        Entry next = entries[index];
        entries[index] = e;
        if(next != null) {
            e.next = next;
            next.prev = e;
        }
        if(head == null) {
            head = tail = e;
        }else {
            tail.after = e;
            e.before = tail;
            tail = e;
        }
    }

    private static class Entry {
        int key;
        int value;
        Entry prev, next;   // table
        Entry before, after;    // link

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }*/

    public Pro_0146_LRUCache(int capacity) {
        cache = new Cache(capacity);
    }

    public int get(int key) {
        Integer value = cache.get(key);
        return value == null ? -1 : value;
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

    private Cache cache;

    private static class Cache extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        Cache(int capacity) {
            super(capacity*2 < capacity ? capacity : 2*capacity, 0.5f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }
}
