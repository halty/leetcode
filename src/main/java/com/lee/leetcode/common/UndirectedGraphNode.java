package com.lee.leetcode.common;

import java.util.*;

public class UndirectedGraphNode {

    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }

    /**
     * consider a serialized graph {0,1,2#1,2#2,2},
     * '#' as a separator for each node, and ',' as a separator for node label and each neighbor of the node.
     */
    public static UndirectedGraphNode build(String serialization) {
        Map<Integer, UndirectedGraphNode> nodeMap = new HashMap<>();
        String[] segments = serialization.split("#");
        if(segments.length == 0) { return null; }
        UndirectedGraphNode head = buildNode(extractLabels(segments[0]), nodeMap);
        for(int i=1; i<segments.length; i++) {
            buildNode(extractLabels(segments[i]), nodeMap);
        }
        return head;
    }

    private static Integer[] extractLabels(String segment) {
        String[] fields = segment.split(",");
        Integer[] labels = new Integer[fields.length];
        for(int i=0; i<fields.length; i++) {
            labels[i] = Integer.valueOf(fields[i].trim());
        }
        return labels;
    }

    private static UndirectedGraphNode buildNode(Integer[] labels, Map<Integer, UndirectedGraphNode> nodeMap) {
        Integer headLabel = labels[0];
        UndirectedGraphNode head = nodeMap.get(headLabel);
        if(head == null) {
            head = new UndirectedGraphNode(headLabel);
            nodeMap.put(headLabel, head);
        }
        for(int j=1; j<labels.length; j++) {
            Integer label = labels[j];
            UndirectedGraphNode neighbor = nodeMap.get(label);
            if(neighbor == null) {
                neighbor = new UndirectedGraphNode(label);
                nodeMap.put(label, neighbor);
            }
            head.neighbors.add(neighbor);
        }
        return head;
    }

    public static void print(UndirectedGraphNode node) {
        if(node == null) { return; }
        Queue<UndirectedGraphNode> q = new ArrayDeque<>();
        Set<UndirectedGraphNode> s = new HashSet<>();
        q.offer(node);
        StringBuilder buf = new StringBuilder();
        while(!q.isEmpty()) {
            UndirectedGraphNode n = q.poll();
            if(s.contains(n)) { continue; }
            s.add(n);
            buf.setLength(0);
            buf.append(n.label).append(", ");
            buf.append("<");
            for(UndirectedGraphNode neighbor : n.neighbors) {
                q.offer(neighbor);
                buf.append(neighbor.label).append(", ");
            }
            if(n.neighbors.size() > 0) { buf.setLength(buf.length()-2); }
            buf.append(">");
            System.out.println(buf.toString());
        }
    }
}
