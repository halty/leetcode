package com.lee.leetcode.pro0201_0225;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 *
 There are a total of n courses you have to take, labeled from 0 to n-1.
 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 Example 1:
 Input: 2, [[1,0]]
 Output: true
 Explanation: There are a total of 2 courses to take.
    To take course 1 you should have finished course 0. So it is possible.

 Example 2:
 Input: 2, [[1,0],[0,1]]
 Output: false
 Explanation: There are a total of 2 courses to take.
    To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

 Note:
   1. The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
   2. You may assume that there are no duplicate edges in the input prerequisites.
 *
 */
public class Pro_0207_CourseSchedule {

    public static void main(String[] args) {
        int numCourses = 5;
        int[][] prerequisites = {{1,0},{2,1},{0,2},{3,2}};
        boolean result = canFinish(numCourses, prerequisites);
        System.out.println(result);

    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses < 2 || prerequisites.length < 2) {
            return true;
        }
        int[][] dependencies = new int[numCourses][];
        int[][] derivation = new int[numCourses][];
        boolean[] toBeFinishedCourses = new boolean[numCourses];
        for(int[] pair : prerequisites) {
            int current = pair[0];
            int prev = pair[1];
            createOrAdd(dependencies, current, prev);
            createOrAdd(derivation, prev, current);
            toBeFinishedCourses[current] = true;
        }
        Queue<Integer> queue = new ArrayDeque<>(numCourses);
        for(int i=0; i<numCourses; i++) {
            if(!toBeFinishedCourses[i]) {
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()) {
            Integer i = queue.poll();
            int[] nextCourses = derivation[i];
            if(nextCourses != null) {
                for(int next : nextCourses) {
                    removeDependency(dependencies, next, i);
                    if(dependencies[next] == null) {
                        toBeFinishedCourses[next] = false;
                        queue.offer(next);
                    }
                }
            }
        }
        for(int i=0; i<numCourses; i++) {
            if(toBeFinishedCourses[i]) {
                return false;
            }
        }
        return true;
    }

    private static void createOrAdd(int[][] matrix, int i, int value) {
        int[] array = matrix[i];
        if(array == null) {
            array = new int[1];
            array[0] = value;
        }else {
            int oldLength = array.length;
            array = Arrays.copyOf(array, oldLength+1);
            array[oldLength] = value;
        }
        matrix[i] = array;
    }

    private static void removeDependency(int[][] matrix, int i, int dependency) {
        int[] dependencies = matrix[i];
        if(dependencies == null) { return; }
        int count = dependencies.length;
        int k = -1;
        for(int j=0; j<count; j++) {
            if(dependencies[j] == dependency) {
                k = j;
                break;
            }
        }
        if(k >= 0) {
            if(count == 1) {
                matrix[i] = null;
            }else {
                int[] newDependencies = new int[count-1];
                System.arraycopy(dependencies, 0, newDependencies, 0, k);
                if(k < count-1) {
                    System.arraycopy(dependencies, k+1, newDependencies, k, count-k-1);
                }
                matrix[i] = newDependencies;
            }
        }
    }
}
