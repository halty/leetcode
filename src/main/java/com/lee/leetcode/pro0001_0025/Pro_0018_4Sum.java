package com.lee.leetcode.pro0001_0025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
 * 
 */
public class Pro_0018_4Sum {

	public static void main(String[] args) {
		int[] nums = {2,1,0,-1};
		int target = 2;
		System.out.println(fourSum(nums, target));
	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        if(len < 4) { return Collections.emptyList(); }
        
        Arrays.sort(nums);
        if(len == 4) {
        	if(nums[0]+nums[1]+nums[2]+nums[3] == target) {
        		return Arrays.asList(Arrays.asList(nums[0], nums[1], nums[2], nums[3]));
        	}else {
        		return Collections.emptyList();
        	}
        }
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int firstEnd = len - 3, secondEnd = len - 2;
        for(int first=0; first<firstEnd; first++) {
        	if(first > 0 && nums[first] == nums[first-1]) continue;
        	int secondBegin = first + 1;
        	for(int second=secondBegin; second<secondEnd; second++) {
        		if(second > secondBegin && nums[second] == nums[second-1]) continue;
        		int third = second + 1, fourth = len - 1;
        		int twoSum = nums[first] + nums[second];
        		while(third < fourth) {
        			int sum = twoSum + nums[third] + nums[fourth];
        			if(sum < target) {
        				while(++third < fourth && nums[third] == nums[third-1]);
        			}else if(sum > target) {
        				while(third < --fourth && nums[fourth] == nums[fourth+1]);
        			}else {
        				result.add(Arrays.asList(nums[first], nums[second], nums[third], nums[fourth]));
        				while(++third < --fourth && nums[third] == nums[third-1] && nums[fourth] == nums[fourth+1]);
        			}
        		}
        		
        	}
        }
        return result;
    }
}
