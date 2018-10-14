package com.lee.leetcode.pro0076_0100;

import java.util.List;

/*
 *
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 *
 */
public class Pro_0090_SubsetsII {

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        List<List<Integer>> result = subsetsWithDup(nums);
        for(List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {

    }
}
