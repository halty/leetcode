package com.lee.leetcode.pro0201_0225;

import java.util.Arrays;

/**
 *
 There are a total of n courses you have to take, labeled from 0 to n-1.
 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

 There may be multiple correct orders, you just need to return one of them.
 If it is impossible to finish all courses, return an empty array.

 Example 1:
 Input: 2, [[1,0]]
 Output: [0,1]
 Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0.
   So the correct course order is [0,1] .

 Example 2:
 Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 Output: [0,1,2,3] or [0,2,1,3]
 Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
   Both courses 1 and 2 should be taken after you finished course 0.
   So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

 Note:
   1. The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
   2. You may assume that there are no duplicate edges in the input prerequisites.
 *
 */
public class Pro_0210_CourseScheduleII {

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[] result = findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(result));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses < 2) {
            return numCourses == 0 ? new int[0] : new int[] {0};
        }
        if(prerequisites.length < 2) {
            int[] order = new int[numCourses];
            for(int i=0; i<numCourses; i++) {
                order[i] = i;
            }
            if(prerequisites.length == 1) {
                int before = prerequisites[0][1];
                int after = prerequisites[0][0];
                if (before > after) {
                    order[after] = before;
                    order[before] = after;
                }
            }
            return order;
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
        int[] order = new int[numCourses];
        int begin = 0, end = 0;
        for(int i=0; i<numCourses; i++) {
            if(!toBeFinishedCourses[i]) {
                order[end++] = i;
            }
        }
        while(begin < end) {
            Integer i = order[begin++];
            int[] nextCourses = derivation[i];
            if(nextCourses != null) {
                for(int next : nextCourses) {
                    removeDependency(dependencies, next, i);
                    if(dependencies[next] == null) {
                        order[end++] = next;
                    }
                }
            }
        }
        return end == numCourses ? order : new int[0];
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
