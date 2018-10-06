package com.lee.leetcode.pro0051_0075;

import java.util.Arrays;

/*
 *
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
 *
 */
public class Pro_0075_SortColors {

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
//        sortColors1(nums);
        sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors1(int[] nums) {
        int n = nums.length;
        if(n <= 1) { return; }
        int zeroCnt = 0, oneCnt = 0, twoCnt = 0;
        for(int i=0; i<n; i++) {
            switch(nums[i]) {
                case 0: zeroCnt++; break;
                case 1: oneCnt++; break;
                case 2: twoCnt++; break;
            }
        }
        int i = 0;
        for(int j=0; j<zeroCnt; j++,i++) {
            nums[i] = 0;
        }
        for(int j=0; j<oneCnt; j++,i++) {
            nums[i] = 1;
        }
        for(int j=0; j<twoCnt; j++,i++) {
            nums[i] = 2;
        }
    }

    public static void sortColors2(int[] nums) {
        int n = nums.length;
        if(n <= 1) { return; }
        int b = 0, e = n-1;
        int swapB = b, swapE = e;
        while(b < e) {
            if(nums[b] == 0) {
                if(swapB == b) {
                    b++;
                    swapB++;
                }else {
                    swap(nums, swapB, b);
                    swapB++;
                }
                continue;
            }
            if(nums[e] == 2) {
                if(swapE == e) {
                    e--;
                    swapE--;
                }else {
                    swap(nums, swapE, e);
                    swapE--;
                }
                continue;
            }
            if(nums[b] == 2) {
                swap(nums, b, swapE);
                if(swapE == e) {
                    e--;
                }
                swapE--;
                continue;
            }
            if(nums[e] == 0) {
                swap(nums, swapB, e);
                if(swapB == b) {
                    b++;
                }
                swapB++;
                continue;
            }
            b++;
            e--;
        }
        if(b == e) {
            if(nums[b] == 0) {
                swap(nums, swapB, b);
            }else if(nums[e] == 2) {
                swap(nums, swapE ,e);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
