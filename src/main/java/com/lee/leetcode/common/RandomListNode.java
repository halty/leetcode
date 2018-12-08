package com.lee.leetcode.common;

import java.util.HashMap;
import java.util.Map;

public class RandomListNode {

    public int label;
    public RandomListNode next, random;

    public RandomListNode(int x) {
        this.label = x;
    }

    /**
     * consider a serialized list {0,2#1,2#2},
     * '#' as a separator for each node, and ',' as a separator for node label and random node index.
     */
    public static RandomListNode build(String serialization) {
        Map<Integer, Integer> mapping = new HashMap<>();
        String[] segments = serialization.split("#");
        if(segments.length == 0) { return null; }
        RandomListNode[] array = new RandomListNode[segments.length];
        RandomListNode head = buildNode(0, segments[0], mapping);
        array[0] = head;
        RandomListNode prev = head;
        for(int i=1; i<segments.length; i++) {
            RandomListNode current = buildNode(i, segments[i], mapping);
            array[i] = current;
            prev.next = current;
            prev = current;
        }
        for(Map.Entry<Integer, Integer> entry : mapping.entrySet()) {
            array[entry.getKey()].random = array[entry.getValue()];
        }
        return head;
    }

    public static RandomListNode buildNode(int index, String segment, Map<Integer, Integer> mapping) {
        String[] fields = segment.split(",");
        int label = Integer.parseInt(fields[0]);
        if(fields.length > 1) {
            int randomIndex = Integer.parseInt(fields[1]);
            mapping.put(index, randomIndex);
        }
        return new RandomListNode(label);
    }

    public static void print(RandomListNode head) {
        if(head == null) { return; }
        Map<RandomListNode, Integer> indexMapping = new HashMap<>();
        RandomListNode current = head;
        int index = 0;
        while(current != null) {
            indexMapping.put(current, index);
            current = current.next;
            index++;
        }
        current = head;
        while(current != null) {
            System.out.print(indexMapping.get(current) + " : <" + current.label + ">");
            if(current.random != null) {
                System.out.print(" -> " + indexMapping.get(current.random));
            }
            System.out.println();
            current = current.next;
        }
    }
}
