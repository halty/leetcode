package com.lee.leetcode.pro0201_0225;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
 such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

 Example 1:
 Input: nums = [1,2,3,1], k = 3
 Output: true

 Example 2:
 Input: nums = [1,0,1,1], k = 1
 Output: true

 Example 3:
 Input: nums = [1,2,3,1,2,3], k = 2
 Output: false
 *
 */
public class Pro_0219_ContainsDuplicateII {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1,2,3};
        int k = 3;
//        boolean isDuplicated = containsNearbyDuplicate(nums, k);
        boolean isDuplicated = containsNearbyDuplicate1(nums, k);
        System.out.println(isDuplicated);
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int end = nums.length - 1;
        for(int i=0; i<end; i++) {
            int v = nums[i];
            int limit = (end-i > k) ? i+k : end;
            for(int j=i+1; j<= limit; j++) {
                if(v == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsNearbyDuplicate1(int[] nums, int k) {
        int end = nums.length - 1;
        if(k >= end) {
            Arrays.sort(nums);
            for(int i=1; i<nums.length; i++) {
                if(nums[i] == nums[i-1]) {
                    return true;
                }
            }
            return false;
        }else {
            Set<Integer> set = new HashSet<>(k);
            for(int i=0; i<k; i++) {
                if(!set.add(nums[i])) {
                    return true;
                }
            }
            for(int i=k; i<nums.length; i++) {
                int rIndex = i - k;
                if(!set.add(nums[i])) {
                    return true;
                }
                set.remove(nums[rIndex]);
            }
            return false;
        }
    }
}
