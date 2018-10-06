package com.lee.leetcode.pro0051_0075;

/*
 *
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
 *
 */
public class Pro_0074_Search2DMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,50}
        };
        int target = 20;
//        boolean result = searchMatrix1(matrix, target);
        boolean result = searchMatrix2(matrix, target);
        System.out.println(result);
    }

    public static boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) {
            return false;
        }
        int n = matrix[0].length;
        if(n == 0) {
            return false;
        }
        int begin = 0, end = m - 1;
        while(begin <= end) {
            int mid = (int)((((long)begin) + end) >>> 1);
            int val = matrix[mid][0];
            if(target < val) {
                end = mid - 1;
            }else if(target > val) {
                val = matrix[mid][n-1];
                if(target < val) {
                    begin = end = mid;
                    break;
                }else if(target > val) {
                    begin = mid + 1;
                }else {
                    return true;
                }
            }else {
                return true;
            }
        }
        if(begin == end) {
            int[] array = matrix[begin];
            begin = 0; end = n - 1;
            while(begin <= end) {
                int mid = (int)((((long)begin) + end) >>> 1);
                int val = array[mid];
                if(target < val) {
                    end = mid - 1;
                }else if(target > val) {
                    begin = mid + 1;
                }else {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) {
            return false;
        }
        int n = matrix[0].length;
        if(n == 0) {
            return false;
        }
        for(int i=0; i<m; i++) {
            int h = matrix[i][0];
            if(target < h) {
                return false;
            }else if(target == h) {
                return true;
            }
            int t = matrix[i][n-1];
            if(target == t) {
               return true;
            }else if(target > t) {
                ;
            }else {
                int[] array = matrix[i];
                int b = 1, e = n - 2;
                while(b <= e) {
                    int mid = (b + e) >>> 1;
                    int v = array[mid];
                    if(target < v) {
                        e = mid - 1;
                    }else if(target > v) {
                        b = mid + 1;
                    }else {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
