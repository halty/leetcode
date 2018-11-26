package com.lee.leetcode.pro0126_0150;

import com.lee.leetcode.common.UndirectedGraphNode;

import java.util.*;

/**
 *
 Given the head of a graph, return a deep copy (clone) of the graph.
 Each node in the graph contains a label (int) and a list (List[UndirectedGraphNode]) of its neighbors.
 There is an edge between the given node and each of the nodes in its neighbors.

 OJ's undirected graph serialization (so you can understand error output):
 Nodes are labeled uniquely.

 We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.

 As an example, consider the serialized graph {0,1,2#1,2#2,2}.

 The graph has a total of three nodes, and therefore contains three parts as separated by #.
    1. First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
    2. Second node is labeled as 1. Connect node 1 to node 2.
    3. Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.

 Visually, the graph looks like the following:

    1
   / \
  /   \
 0 --- 2
      / \
      \_/
 Note: The information about the tree serialization is only meant so that you can understand error output if you get a wrong answer.
 You don't need to understand the serialization to solve the problem.
 *
 */
public class Pro_0133_CloneGraph {

    public static void main(String[] args) {
        String str = "0,1,2#1,2#2,2";
        UndirectedGraphNode node = UndirectedGraphNode.build(str);
        UndirectedGraphNode copy = cloneGraph(node);
        UndirectedGraphNode.print(copy);
    }

    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) { return null; }
        Map<Integer, UndirectedGraphNode> m = new HashMap<>();
        Set<UndirectedGraphNode> s = new HashSet<>();
        Queue<UndirectedGraphNode> q = new ArrayDeque<>();
        q.offer(node);
        while(!q.isEmpty()) {
            UndirectedGraphNode current = q.poll();
            if(s.contains(current)) { continue; }
            s.add(current);
            cloneNode(current, q, m);
        }
        return m.get(node.label);
    }

    private static void cloneNode(UndirectedGraphNode node, Queue<UndirectedGraphNode> q, Map<Integer, UndirectedGraphNode> m) {
        Integer label = node.label;
        UndirectedGraphNode copy = m.get(label);
        if(copy == null) {
            copy = new UndirectedGraphNode(label);
            m.put(label, copy);
        }
        for(UndirectedGraphNode neighbor : node.neighbors) {
            UndirectedGraphNode copyNeighbor = m.get(neighbor.label);
            if(copyNeighbor == null) {
                copyNeighbor = new UndirectedGraphNode(neighbor.label);
                m.put(neighbor.label, copyNeighbor);
            }
            copy.neighbors.add(copyNeighbor);
            q.add(neighbor);
        }
    }
}
