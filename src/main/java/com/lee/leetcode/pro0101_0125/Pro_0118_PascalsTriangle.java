package com.lee.leetcode.pro0101_0125;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.

 In Pascal's triangle, each number is the sum of the two numbers directly above it.

 Example:

 Input: 5
 Output:
 [
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
 ]
 *
 */
public class Pro_0118_PascalsTriangle {

    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> triangle = generate(numRows);
        for(List<Integer> list : triangle) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>(numRows);
        switch(numRows) {
            case 0: return triangle;
            case 1:
                List<Integer> first = new ArrayList<>(1);
                first.add(1);
                triangle.add(first);
                return triangle;
            case 2:
                first = new ArrayList<>(1);
                first.add(1);
                triangle.add(first);
                List<Integer> second = new ArrayList<>(2);
                second.add(1);
                second.add(1);
                triangle.add(second);
                return triangle;
            default:
                first = new ArrayList<>(1);
                first.add(1);
                triangle.add(first);
                second = new ArrayList<>(2);
                second.add(1);
                second.add(1);
                triangle.add(second);
                List<Integer> prev = second;
                for(int i=3; i<= numRows; i++) {
                    List<Integer> list = new ArrayList<>(i);
                    list.add(1);
                    int end = i - 1;
                    for(int j=1; j<end; j++) {
                        list.add(prev.get(j-1)+prev.get(j));
                    }
                    list.add(1);
                    triangle.add(list);
                    prev = list;
                }
                return triangle;
        }
    }
}
