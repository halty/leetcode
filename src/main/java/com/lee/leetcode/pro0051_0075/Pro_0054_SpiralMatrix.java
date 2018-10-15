package com.lee.leetcode.pro0051_0075;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 */
public class Pro_0054_SpiralMatrix {

    public static void main(String[] args) {
        /*int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };*/
        int[][] matrix = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20}
        };
        List<Integer> result = null;
//        result = spiralOrder1(matrix);
        result = spiralOrder2(matrix);
//        result = spiralOrder3(matrix);
        System.out.println(result);
    }

    public static List<Integer> spiralOrder1(int[][] matrix) {
        if(matrix == null || matrix.length == 0) { return Collections.emptyList(); }
        if(matrix.length == 1) {
            int[] array = matrix[0];
            List<Integer> list = new ArrayList<>(array.length);
            for(int i=0; i<array.length; i++) {
                list.add(array[i]);
            }
            return list;
        }

        int m = matrix.length, n = matrix[0].length;
        if(n == 0) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>(m * n);
        int rb = 0, re = m-1, cb = 0, ce = n-1;
        int flag = 0;
        while(rb<=re && cb<=ce) {
            switch(flag) {
                case 0: // left to right
                    for(int i=cb; i<=ce; i++) {
                        list.add(matrix[rb][i]);
                    }
                    rb += 1;
                    break;
                case 1: // up to down
                    for(int i=rb; i<=re; i++) {
                        list.add(matrix[i][ce]);
                    }
                    ce -= 1;
                    break;
                case 2: // right to left
                    for(int i=ce; i>=cb; i--) {
                        list.add(matrix[re][i]);
                    }
                    re -= 1;
                    break;
                case 3: // down to up
                    for(int i=re; i>=rb; i--) {
                        list.add(matrix[i][cb]);
                    }
                    cb += 1;
                    break;
                default:    // nop
                    ;
            }
            flag = (flag + 1) % 4;
        }
        return list;
    }

    public static List<Integer> spiralOrder2(int[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return Collections.emptyList();
        }
        int m = matrix.length, n = matrix[0].length;
        if(n == 0) {
            return Collections.emptyList();
        }
        if(m == 1) {
            int[] array = matrix[0];
            List<Integer> list = new ArrayList<>(n);
            for(int i=0; i<array.length; i++) {
                list.add(array[i]);
            }
            return list;
        }

        int size = m * n;
        List<Integer> list = new ArrayList<>(m * n);
        int rb = 0, re = m-1, cb = 0, ce = n-1;
        int count = 0;
        while(count < size) {
            // left to right
            for(int i=cb; i<=ce; i++) {
                list.add(matrix[rb][i]);
                count++;
            }
            rb += 1;
            if(count == size) { break; }
            // up to down
            for(int i=rb; i<=re; i++) {
                list.add(matrix[i][ce]);
                count++;
            }
            ce -= 1;
            if(count == size) { break; }
            // right to left
            for(int i=ce; i>=cb; i--) {
                list.add(matrix[re][i]);
                count++;
            }
            re -= 1;
            if(count == size) { break; }
            // down to up
            for(int i=re; i>=rb; i--) {
                list.add(matrix[i][cb]);
                count++;
            }
            cb += 1;
        }
        return list;
    }
}
