package com.lee.leetcode.pro0226_0250;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 Note: The algorithm should run in linear time and in O(1) space.

 Example 1:
 Input: [3,2,3]
 Output: [3]

 Example 2:
 Input: [1,1,1,3,3,2,2,2]
 Output: [1,2]
 *
 */
public class Pro_0229_MajorityElementII {

    public static void main(String[] args) {
        int[] nums = {0,0,0};
        List<Integer> result = majorityElement(nums);
        System.out.println(result);
    }

    public static List<Integer> majorityElement(int[] nums) {
        int a = 0, b = 1;
        int countA = 0, countB = 0;
        // Boyer–Moore majority vote algorithm
        for(int num : nums) {
            if(num == a) {
                countA++;
            }else if(num == b) {
                countB++;
            }else if(countA == 0) {
                a = num;
                countA++;
            }else if(countB == 0) {
                b = num;
                countB++;
            }else {
                countA--;
                countB--;
            }
        }
        countA = 0; countB = 0;
        for(int num : nums) {
            if(num == a) {
                countA++;
            }
            if(num == b) {
                countB++;
            }
        }
        int limit = nums.length / 3;
        List<Integer> list = new ArrayList<>(2);
        if(countA > limit) {
            list.add(a);
        }
        if(countB > limit) {
            list.add(b);
        }
        return list;
    }
}
