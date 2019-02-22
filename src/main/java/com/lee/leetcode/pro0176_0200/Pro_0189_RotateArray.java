package com.lee.leetcode.pro0176_0200;

import java.util.Arrays;

/**
 *
 Given an array, rotate the array to the right by k steps, where k is non-negative.

 Example 1:
 Input: [1,2,3,4,5,6,7] and k = 3
 Output: [5,6,7,1,2,3,4]
 Explanation:
 rotate 1 steps to the right: [7,1,2,3,4,5,6]
 rotate 2 steps to the right: [6,7,1,2,3,4,5]
 rotate 3 steps to the right: [5,6,7,1,2,3,4]

 Example 2:
 Input: [-1,-100,3,99] and k = 2
 Output: [3,99,-1,-100]
 Explanation:
 rotate 1 steps to the right: [99,-1,-100,3]
 rotate 2 steps to the right: [3,99,-1,-100]

 Note:
   1. Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
   2. Could you do it in-place with O(1) extra space?
 *
 */
public class Pro_0189_RotateArray {

    public static void main(String[] args) {
        int k = 9;
        int[] nums = {1,2,3,4,5,6,7};
//        rotate(nums, k);
//        rotate1(nums, k);
        rotate2(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] target = new int[n];
        k = k % n;
        int i = 0, j = n - k;
        while(j < n) {
            target[i] = nums[j];
            i++;
            j++;
        }
        j = 0;
        while(i < n) {
            target[i] = nums[j];
            i++;
            j++;
        }
        i = 0;
        while(i < n) {
            nums[i] = target[i];
            i++;
        }
    }

    public static void rotate1(int[] nums, int k) {
        int n = nums.length;
        int[] target = new int[n];
        k = k % n;
        System.arraycopy(nums, n-k, target, 0, k);
        System.arraycopy(nums, 0, target, k, n-k);
        System.arraycopy(target, 0, nums, 0, n);
    }

    public static void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        swapRange(nums, 0, n-1);
        swapRange(nums, 0, k-1);
        swapRange(nums, k, n-1);
    }

    private static void swapRange(int[] nums, int begin, int end) {
        while(begin < end) {
            swap(nums, begin, end);
            begin++;
            end--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}