package com.lee.leetcode.pro0101_0125;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 Note that the row index starts from 0.

 In Pascal's triangle, each number is the sum of the two numbers directly above it.

 Example:
 Input: 3
 Output: [1,3,3,1]
 Follow up:
 Could you optimize your algorithm to use only O(k) extra space?
 *
 */
public class Pro_0119_PascalsTriangleII {

    public static void main(String[] args) {
        int rowIndex = 33;
        List<Integer> row = getRow(rowIndex);
        System.out.println(row);
    }

    public static List<Integer> getRow(int rowIndex) {
        switch(rowIndex) {
            case 0:
                return Arrays.asList(1);
            case 1:
                return Arrays.asList(1,1);
            default:
                int count = rowIndex + 1;
                List<Integer> list = new ArrayList<>(count);
                list.add(1);
                list.add(rowIndex);
                int rCount = rowIndex - 1;
                int[] array = new int[rCount];
                for(int i=0; i<rCount; i++) {
                    array[i] = i+1;
                }
                int end = rowIndex / 2;
                for(int i=2,j=rCount-1; i<=end; i++,j--) {
                    for(int k=1; k<=j; k++) {
                        array[k] += array[k-1];
                    }
                    list.add(array[j]);
                }
                for(int i=end+1,k=(rowIndex-1)/2; i<count; i++,k--) {
                    list.add(list.get(k));
                }
                return list;
        }
    }
}
