package com.lee.leetcode.pro0301_0325;

import java.util.*;

/**
 *
 For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree.
 Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
 Given such a graph, write a function to find all the MHTs and return a list of their root labels.

 Format
 The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
 You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

 Example 1 :
 Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
       0
       |
       1
      / \
     2   3
 Output: [1]

 Example 2 :
 Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
     0  1  2
      \ | /
        3
        |
        4
        |
        5
 Output: [3, 4]

 Note:
    1. According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words,
    any connected graph without simple cycles is a tree.”
    2. The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 */
public class Pro_0310_MinimumHeightTrees {

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {
                {1,0},
                {1,2},
                {1,3}
        };
//        List<Integer> result = findMinHeightTrees(n, edges);
//        List<Integer> result = findMinHeightTrees1(n, edges);
        List<Integer> result = findMinHeightTrees2(n, edges);
        System.out.println(result);
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n <= 0) { return Collections.emptyList(); }
        if(edges.length == 0) {
            List<Integer> result = new ArrayList<>(n);
            for(int i=0; i<n; i++) {
                result.add(i);
            }
            return result;
        }
        if(edges.length == 1) { return Arrays.asList(edges[0][0], edges[0][1]); }
        List<Integer>[] matrix = adjacencyMatrix(n, edges);
        return minHeightTrees(n, matrix);
    }

    private static List<Integer>[] adjacencyMatrix(int n, int[][] edges) {
        List<Integer>[] matrix = new List[n];
        for(int i=0; i<n; i++) { matrix[i] = new ArrayList<>(); }
        for(int[] edge : edges) {
            int a = edge[0], b = edge[1];
            matrix[a].add(b);
            matrix[b].add(a);
        }
        return matrix;
    }

    private static List<Integer> minHeightTrees(int n, List<Integer>[] matrix) {
        List<Integer> result = null;
        int minHeight = Integer.MAX_VALUE;
        boolean[] set = new boolean[n];
        boolean flag = true;
        int[] queue = new int[n];
        for(int i=0; i<n; i++) {
            set[i] = flag;
            queue[0] = i;
            int idx = 0, h = 0, e = -1, b;
            do {
                b = e + 1;
                e = idx;
                h += 1;
                for(int j=b; j<=e; j++) {
                    int v = queue[j];
                    List<Integer> next = matrix[v];
                    for(int k : next) {
                        if(set[k] != flag) {
                            set[k] = flag;
                            queue[++idx] = k;
                        }
                    }
                }
            }while(e < idx);
            if(h == minHeight) {
                result.add(i);
            }else if(h < minHeight) {
                minHeight = h;
                result = new ArrayList<>();
                result.add(i);
            }
            flag = !flag;
        }
        return result;
    }

    public static List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        /*
         * remove leaf node iterable until 1 or 2 nodes left, which are root node.
         * because the root node of MHT must be the longest distance from leaf node.
         */
        if(n <= 0) { return Collections.emptyList(); }
        if(edges.length == 0) {
            List<Integer> result = new ArrayList<>(n);
            for(int i=0; i<n; i++) {
                result.add(i);
            }
            return result;
        }
        if(edges.length == 1) { return Arrays.asList(edges[0][0], edges[0][1]); }
        Map<Integer, Set<Integer>> map = adjacencyMap(n, edges);
        return minHeightTreeRoots(map);
    }

    private static Map<Integer, Set<Integer>> adjacencyMap(int n, int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>(n);
        for(int i=0; i<n; i++) {
            map.put(i, new HashSet<>());
        }
        for(int[] edge : edges) {
            int a = edge[0], b = edge[1];
            map.get(a).add(b);
            map.get(b).add(a);
        }
        return map;
    }

    private static List<Integer> minHeightTreeRoots(Map<Integer, Set<Integer>> map) {
        Queue<Integer> q = new ArrayDeque<>(map.size());
        while(map.size() > 2) {
            for(Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
                if(entry.getValue().size() == 1) {
                    q.offer(entry.getKey());
                }
            }
            Integer node;
            while((node=q.poll()) != null) {
                Integer other = map.remove(node).iterator().next();
                map.get(other).remove(node);
            }
        }
        return new ArrayList<>(map.keySet());
    }

    public static List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        if(n == 1) { return Collections.singletonList(0); }
        int[] edgeCount = new int[n];
        int[] verts = new int[n];
        for(int[] edge : edges) {
            int a = edge[0], b = edge[1];
            edgeCount[a]++;
            edgeCount[b]++;
            verts[a] ^= b;
            verts[b] ^= a;
        }
        LinkedList<Integer> q = new LinkedList<Integer>();
        for(int i=0; i<n; i++) {
            if(edgeCount[i] == 1) {
                q.offer(i);
            }
        }
        int count = n;
        while(count > 2) {
            for(int cnt=q.size(); cnt>0; cnt--) {
                int v = q.poll();
                int other = verts[v];
                verts[other] ^= v;
                if (--edgeCount[other] == 1) {
                    q.offer(other);
                }
                count--;
            }
        }
        return q;
    }
}
