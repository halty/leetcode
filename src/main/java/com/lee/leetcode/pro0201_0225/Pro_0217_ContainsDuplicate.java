package com.lee.leetcode.pro0201_0225;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 Given an array of integers, find if the array contains any duplicates.
 Your function should return true if any value appears at least twice in the array,
 and it should return false if every element is distinct.

 Example 1:
 Input: [1,2,3,1]
 Output: true

 Example 2:
 Input: [1,2,3,4]
 Output: false

 Example 3:
 Input: [1,1,1,3,3,4,3,2,4,2]
 Output: true
 *
 */
public class Pro_0217_ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
//        boolean isDuplicated = containsDuplicate(nums);
//        boolean isDuplicated = containsDuplicate1(nums);
//        boolean isDuplicated = containsDuplicate2(nums);
        boolean isDuplicated = containsDuplicate3(nums);
        System.out.println(isDuplicated);
    }

    public static boolean containsDuplicate(int[] nums) {
        int len = nums.length, end = len-1;
        for(int i=0; i<end; i++) {
            int v = nums[i];
            for(int j=i+1; j<len; j++) {
                if(nums[j] == v) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for(int i=1; i<nums.length; i++) {
            if(nums[i] == nums[i-1]) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsDuplicate2(int[] nums) {
        int len = nums.length;
        for(int i=1; i<len; i++) {
            int v = nums[i];
            // binary search
            int b = 0, e = i-1;
            while(b <= e) {
                int m = b + (e-b)/2;
                int mv = nums[m];
                if(v < mv) {
                    e = m - 1;
                }else if(v > mv) {
                    b = m + 1;
                }else {
                    return true;
                }
            }
            if(b < i) {
                System.arraycopy(nums, b, nums, b+1, i-b);
                nums[b] = v;
            }
        }
        return false;
    }

    public static boolean containsDuplicate3(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for(int num : nums) {
            if(!set.add(num)) {
                return true;
            }
        }
        return false;
    }
}
