package com.lee.leetcode.pro0151_0175;

/**
 *
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

 Find the minimum element.
 You may assume no duplicate exists in the array.

 Example 1:
 Input: [3,4,5,1,2]
 Output: 1

 Example 2:
 Input: [4,5,6,7,0,1,2]
 Output: 0
 *
 */
public class Pro_0153_FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {3,4,5,1,2};
        int min = findMin(nums);
//        int min = findMin1(nums);
        System.out.println(min);
    }

    public static int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int num : nums) {
            if(min > num) {
                min = num;
            }
        }
        return min;
    }

    public static int findMin1(int[] nums) {
        int len = nums.length;
        int b = 0, e = len - 1;
        while(e-b >= 2) {
            int m = (b + e) / 2;
            if(nums[m] > nums[e]) {
                b = m + 1;
            }else {
                e = m;
            }
        }
        return b == e ? nums[b] : Math.min(nums[b], nums[e]);
    }
}
