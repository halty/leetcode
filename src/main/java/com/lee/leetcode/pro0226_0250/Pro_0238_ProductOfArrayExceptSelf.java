package com.lee.leetcode.pro0226_0250;

import java.util.Arrays;

/**
 *
 Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

 Example:
 Input:  [1,2,3,4]
 Output: [24,12,8,6]
 Note: Please solve it without division and in O(n).

 Follow up:
    Could you solve it with constant space complexity?
    (The output array does not count as extra space for the purpose of space complexity analysis.)
 *
 */
public class Pro_0238_ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] result = productExceptSelf(nums);
        System.out.println(Arrays.toString(result));
        result = productExceptSelf1(nums);
        System.out.println(Arrays.toString(result));
    }

    public static int[] productExceptSelf(int[] nums) {
        int product = 1;
        for(int num : nums) {
            product *= num;
        }
        int[] result = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            result[i] = product / nums[i];
        }
        return result;
    }

    public static int[] productExceptSelf1(int[] nums) {
        int[] result = new int[nums.length];
        int p = 1;
        for(int i=0; i<nums.length; i++) {
            result[i] = p = p * nums[i];
        }
        p = 1;
        for(int i=nums.length-1; i>0; i--) {
            result[i] = result[i-1] * p;
            p *= nums[i];
        }
        result[0] = p;
        return result;
    }
}
