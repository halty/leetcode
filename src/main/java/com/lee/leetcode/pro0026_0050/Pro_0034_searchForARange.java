package com.lee.leetcode.pro0026_0050;

import java.util.Arrays;

/**
 * 
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
 * 
 */
public class Pro_0034_searchForARange {

	public static void main(String[] args) {
		int[] nums = {2, 2};
		int target = 1;
		System.out.println(Arrays.toString(searchRange(nums, target)));
	}

	public static int[] searchRange(int[] nums, int target) {
        int[] range = {-1, -1};
        if(nums.length == 0) { return range; }
        if(nums.length == 1) {
        	if(nums[0] == target) {
        		range[1] = range[0] = 0;
        	}
        	return range;
        }
        
        range[0] = nums.length; range[1] = -1;
        searchRange(nums, target, range, 0, nums.length-1);
        if(range[0] == nums.length) {
        	range[1] = range[0] = -1;
        }
        return range;
    }
	
	private static void searchRange(int[] nums, int target, int[] range, int left, int right) {
		if(left > right) { return; }
		int pivot = (left + right) / 2;
		if(left == right) {
			if(target == nums[pivot]) {
				if(pivot < range[0]) { range[0] = pivot; }
				if(pivot > range[1]) { range[1] = pivot; }
			}
		}else {
			int pv = nums[pivot];
			if(target < pv) {
				searchRange(nums, target, range, left, pivot-1);
			}else if(target > pv) {
				searchRange(nums, target, range, pivot+1, right);
			}else {
				if(pivot < range[0]) { range[0] = pivot; }
				if(pivot > range[1]) { range[1] = pivot; }
				searchRange(nums, target, range, left, pivot-1);
				searchRange(nums, target, range, pivot+1, right);
			}
		}
	}
}
