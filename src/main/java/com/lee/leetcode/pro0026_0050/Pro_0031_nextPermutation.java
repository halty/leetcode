package com.lee.leetcode.pro0026_0050;

import java.util.Arrays;

/*
 * 
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 * 
 */
public class Pro_0031_nextPermutation {

	public static void main(String[] args) {
		// int[] nums = {1,2,3,4};
		int[] nums = {1,1,5};
		
		int iters = 1;
		for(int i=2; i<=nums.length; i++) {
			iters *= i;
		}
		
		System.out.println(Arrays.toString(nums));
		for(int i=0; i<iters; i++) {
			nextPermutation(nums);
			System.out.println(Arrays.toString(nums));
		}
		/*
		System.out.println(Arrays.toString(nums));
		nextPermutation(nums);
		System.out.println(Arrays.toString(nums));
		*/
	}
	
	public static void nextPermutation(int[] nums) {
        if(nums.length < 2) { return; }

        int i = nums.length - 1;
        for(; i>0 && nums[i-1] >= nums[i]; i--);
        for(int j=i, k=nums.length-1; j<k; j++, k--) {
        	int tmp = nums[j];
        	nums[j] = nums[k];
        	nums[k] = tmp;
        }
        if(i > 0) {
        	int left = i - 1;
        	int right = i;
        	for(; nums[right] <= nums[left]; right++);
        	int tmp = nums[right];
        	nums[right] = nums[left];
        	nums[left] = tmp;
        }
    }

}
