package com.lee.leetcode.pro0076_0100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 *
 */
public class Pro_0078_Subsets {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        List<List<Integer>> result = subsets(nums);
        print(result);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        if(n == 0) {
            List<List<Integer>> result = new ArrayList<>(1);
            result.add(Collections.<Integer>emptyList());
            return result;
        }
        if(n == 1) {
            List<List<Integer>> result = new ArrayList<>(2);
            result.add(Collections.<Integer>emptyList());
            result.add(Arrays.asList(nums[0]));
            return result;
        }
        int length = (int)Math.pow(2, n);
        List<List<Integer>> result = new ArrayList<>(length);
        int[] index = new int[n];
        for(int k=0; k<=n; k++) {
            addCombineSubset(nums, n, k, index, result);
        }
        return result;
    }

    private static void addCombineSubset(int[] nums, int n, int k, int[] index, List<List<Integer>> result) {
        if(k == 0) {
            result.add(Collections.<Integer>emptyList());
            return;
        }
        if(k == 1) {
            for(int i=0; i<n; i++) {
                result.add(Arrays.asList(nums[i]));
            }
            return;
        }
        if(k == n-1) {
            result.add(asList(nums, 0, k));
            for(int i=k-1; i>=0; i--) {
                swap(nums, i, k);
                result.add(asList(nums, 0, k));
            }
            return;
        }
        if(k == n) {
            result.add(asList(nums, 0, n));
            return;
        }
        for(int i=0; i<k; i++) {
            index[i] = i+1;
        }
        int maxHead = n + 1 - k;
        int end = k - 1;
        int tail = k - 1;
        while(true) {
            if(tail == end) {
                while(index[end] <= n) {
                    result.add(asList(nums, index, k));
                    index[end] += 1;
                }
                tail = moveBack(index, k, maxHead);
                if(tail == -1) { break; }
            }else { // move ahead
                index[tail+1] = index[tail] + 1;
                tail++;
            }
        }
    }

    private static List<Integer> asList(int[] nums, int begin, int end) {
        List<Integer> list = new ArrayList<>(end - begin);
        for(int i=begin; i<end; i++) {
            list.add(nums[i]);
        }
        return list;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static List<Integer> asList(int[] nums, int[] index, int len) {
        List<Integer> list = new ArrayList<>(len);
        for(int i=0; i<len; i++) {
            list.add(nums[index[i]-1]);
        }
        return list;
    }

    private static int moveBack(int[] index, int len, int maxHead) {
        for(int i=len-2; i>=0; i--) {
            int v = index[i] + 1;
            if(v <= maxHead+i) {
                index[i] = v;
                return i;
            }
        }
        return -1;
    }

    private static void print(List<List<Integer>> result) {
        for(List<Integer> list : result) {
            System.out.println(list);
        }
    }
}
