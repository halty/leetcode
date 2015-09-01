package com.lee.leetcode.pro0026_0050;

import java.util.Arrays;

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
