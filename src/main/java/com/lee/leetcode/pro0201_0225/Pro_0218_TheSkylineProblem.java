package com.lee.leetcode.pro0201_0225;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A),
 <img href="https://leetcode.com/static/images/problemset/skyline1.jpg" />
 write a program to output the skyline formed by these buildings collectively (Figure B).
 <img href="https://leetcode.com/static/images/problemset/skyline2.jpg" />

 The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
 where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height.
 It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
 You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

 For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ].

 The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline.
 A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends,
 is merely used to mark the termination of the skyline, and always has zero height.
 Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

 For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

 Notes:
    The number of buildings in any input list is guaranteed to be in the range [0, 10000].
    The input list is already sorted in ascending order by the left x position Li.
    The output list must be sorted by the x position.
    There must be no consecutive horizontal lines of equal height in the output skyline.
    For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable;
    the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 *
 */
public class Pro_0218_TheSkylineProblem {

    public static void main(String[] args) {
        int[][] buildings = {
                { 2, 9,10},
                { 3, 7,15},
                { 5,12,12},
                {15,20,10},
                {19,24, 8}
        };
        List<List<Integer>> result = getSkyline(buildings);
        System.out.println(result);
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        if(buildings.length == 0) { return Collections.emptyList(); }
        int[] xPoints = xPoints(buildings);
        int[] heights = calcHeights(xPoints, buildings);
        int count = mergeHeights(xPoints, heights);
        List<List<Integer>> result = new ArrayList<>(count);
        for(int i=0; i<count; i++) {
            result.add(Arrays.asList(xPoints[i], heights[i]));
        }
        return result;
    }

    private static int[] xPoints(int[][] buildings) {
        int[] xPoints = new int[buildings.length*2];
        int i = 0;
        for(int[] building : buildings) {
            xPoints[i++] = building[0];
            xPoints[i++] = building[1];
        }
        Arrays.sort(xPoints);
        // remove duplicates
        int prev = 0, cur = 1, len = xPoints.length;
        while(cur < len) {
            if(xPoints[cur] == xPoints[prev]) {
                cur++;
            }else {
                if(cur == prev+1) {
                    prev++;
                    cur++;
                }else {
                    xPoints[++prev] = xPoints[cur];
                    cur++;
                }
            }
        }
        if(prev < len-1) {
            xPoints = Arrays.copyOf(xPoints, prev+1);
        }
        return xPoints;
    }

    private static int[] calcHeights(int[] xPoints, int[][] buildings) {
        int count = xPoints.length;
        int[] heights = new int[count];
        int begin = 0;
        for(int[] building : buildings) {
            int leftIdx = find(xPoints, begin, building[0]);
            int rightIdx = find(xPoints, leftIdx+1, building[1]);
            int height = building[2];
            begin = leftIdx;
            for(int i=leftIdx; i<rightIdx; i++) {
                if(height > heights[i]) {
                    heights[i] = height;
                }
            }
        }
        heights[count-1] = 0;
        return heights;
    }

    private static int find(int[] xPoints, int begin, int target) {
        int b = begin, e = xPoints.length-1;
        while(b <= e) {
            int m = b + (e-b)/2;
            int mp = xPoints[m];
            if(target == mp) {
                return m;
            }else if(target < mp) {
                e = m - 1;
            }else {
                b = m + 1;
            }
        }
        return b;
    }

    private static int mergeHeights(int[] xPoints, int[] heights) {
        int prev = 0, cur = 1, end = xPoints.length - 1;
        while(cur < end) {
            if(heights[cur] == heights[prev]) {
                cur++;
            }else {
                if(cur == prev+1) {
                    prev++;
                    cur++;
                }else {
                    xPoints[++prev] = xPoints[cur];
                    heights[prev] = heights[cur];
                    cur++;
                }
            }
        }
        xPoints[++prev] = xPoints[end];
        heights[prev] = heights[end];
        return prev+1;
    }
}
