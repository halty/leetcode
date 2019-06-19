package com.lee.leetcode.pro0276_0300;

import java.util.Arrays;

/**
 *
 Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 Example:
 Input: [0,1,0,3,12]
 Output: [1,3,12,0,0]

 Note:
    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.
 *
 */
public class Pro_0283_MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {0,0,1,2,3};
//        moveZeroes(nums);
        moveZeroes1(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        int len = nums.length;
        if(len > 1) {
            int i = 0, j = 0;
            while(i < len) {
                if(nums[i] == 0) {
                    while(++i < len && nums[i] == 0);
                    if(i == len) { break; }
                }
                nums[j++] = nums[i++];
            }
            while(j < len) {
                nums[j++] = 0;
            }
        }
    }

    public static void moveZeroes1(int[] nums) {
        int len = nums.length;
        if(len > 1) {
            int i = 0;
            for(; i < len && nums[i] != 0; i++);
            if(i < len) {
                int k = i-1;
                for(int j=i+1; j<len; j++) {
                    if(nums[j] != 0) {
                        k = j;
                        nums[i++] = nums[j];
                    }
                }
                for(; i<=k; i++) {
                    nums[i] = 0;
                }
            }
        }
    }
}
