package com.lee.leetcode.pro0026_0050;

import java.util.Arrays;

/**
 * 
Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * 
 */
public class Pro_0027_removeElement {

	public static void main(String[] args) {
		int[] nums = {1,2,2,3,5};
		int val = 2;
		System.out.println(Arrays.toString(Arrays.copyOf(nums, removeElement(nums, val))));
	}

	public static int removeElement(int[] nums, int val) {
        int len = nums.length;
        if(len == 0) { return 0; }
        
        int i = 0, j = 0;
		while(i < len) {
			int element = nums[i++];
			if(element != val) {
				nums[j++] = element;
			}
		}
        return j;
    }
}
