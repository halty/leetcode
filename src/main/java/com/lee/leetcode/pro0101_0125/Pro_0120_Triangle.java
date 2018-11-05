package com.lee.leetcode.pro0101_0125;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

 For example, given the following triangle
 [
    [2],
   [3,4],
  [6,5,7],
 [4,1,8,3]
 ]

 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 Note:
 Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 *
 */
public class Pro_0120_Triangle {

    public static void main(String[] args) {
        int[][] array = {
                   {1},
                  {2,3}
        };
        List<List<Integer>> triangle = build(array);
        int result  = minimumTotal1(triangle);
//        int result  = minimumTotal2(triangle);
        System.out.println(result);
    }

    public static int minimumTotal1(List<List<Integer>> triangle) {
        int levelCount = triangle.size();
        if(levelCount == 0) { return 0; }
        List<Integer> list = triangle.get(levelCount-1);
        int[] array = new int[list.size()];
        for(int i=0; i<array.length; i++) {
            array[i] = list.get(i);
        }
        for(int i=levelCount-2; i>=0; i--) {
            list = triangle.get(i);
            int size = list.size();
            for(int j=0; j<size; j++) {
                array[j] = list.get(j) + Math.min(array[j], array[j+1]);
            }
        }
        return array[0];
    }

    public static int minimumTotal2(List<List<Integer>> triangle) {
        int levelCount = triangle.size();
        if(levelCount == 0) { return 0; }
        int[] array = new int[levelCount];
        int topCount = triangle.get(0).size();
        int sum = 0, minSum = Integer.MAX_VALUE;
        int level = 0, levelEnd = levelCount - 1;
        while(array[0] < topCount) {
            if(level < levelEnd) {
                int up = array[level];
                sum += triangle.get(level).get(up);
                level++;
                array[level] = up;
            }else {
                int bottom = triangle.get(level).get(array[level]);
                sum += bottom;
                if(minSum > sum) {
                    minSum = sum;
                }
                if(level > 0) {
                    if(array[level] < array[level-1]+1) {
                        sum -= bottom;
                        array[level] += 1;
                    }else {
                        do {
                            sum -= triangle.get(level).get(array[level]);
                            level--;
                        }while(level > 0 && array[level] == array[level-1]+1);
                        if(level == 0) {
                            sum -= triangle.get(level).get(array[level]);
                            array[level] += 1;
                        }else {
                            sum -= triangle.get(level).get(array[level]);
                            array[level] += 1;
                        }
                    }
                }else {
                    sum -= triangle.get(level).get(array[level]);
                    array[level] += 1;
                }
            }
        }
        return minSum;
    }

    private static List<List<Integer>> build(int[][] array) {
        List<List<Integer>> triangle = new ArrayList<>(array.length);
        for(int i=0; i<array.length; i++) {
            int[] level = array[i];
            List<Integer> list = new ArrayList<>(level.length);
            for(int val : level) {
                list.add(val);
            }
            triangle.add(list);
        }
        return triangle;
    }
}