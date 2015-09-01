package com.lee.leetcode.pro0026_0050;

import java.util.Arrays;

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
