package com.lee.leetcode.pro0026_0050;

import java.util.Arrays;

/**
 * 
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 * 
 */
public class Pro_0026_removeDuplicatesFromSortedArray {

	public static void main(String[] args) {
		int[] nums = {1,1,2};
		System.out.println(removeDuplicates(nums));
		System.out.println(Arrays.toString(nums));
	}

	public static int removeDuplicates(int[] nums) {
		if(nums == null) { return 0; }
		int len = nums.length;
		if(len < 2) { return len; }
		
		int i = 1, j = 0;
		while(i < len) {
			if(nums[i] != nums[j]) {
				nums[++j] = nums[i];
			}
			i++;
		}
        return j+1;
    }
}
