package com.lee.leetcode.pro0251_0275;

import java.util.BitSet;

/**
 *
 Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

 Example 1:
 Input: [3,0,1]
 Output: 2

 Example 2:
 Input: [9,6,4,2,3,5,7,0,1]
 Output: 8

 Note:
    Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 *
 */
public class Pro_0268_MissingNumber {

    public static void main(String[] args) {
        int[] nums = {9,6,4,2,3,5,7,0,1};
//        int result = missingNumber(nums);
//        int result = missingNumber1(nums);
        int result = missingNumber2(nums);
        System.out.println(result);
    }

    public static int missingNumber(int[] nums) {
        BitSet set = new BitSet(nums.length+1);
        set.clear(0, nums.length+1);
        for(int num : nums) { set.set(num); }
        return set.nextClearBit(0);
    }

    public static int missingNumber1(int[] nums) {
        long total = (1+nums.length) * nums.length / 2;
        long sum = 0;
        for(int num : nums) { sum += num; }
        return (int)(total - sum);
    }

    public static int missingNumber2(int[] nums) {
        int n = 0, i = 1;
        for(int num : nums) {
            n ^= num;
            n ^= i;
            i++;
        }
        return n;
    }
}
