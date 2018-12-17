package com.lee.leetcode.pro0151_0175;

/**
 *
 Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

 Example 1:
 Input: [2,3,-2,4]
 Output: 6
 Explanation: [2,3] has the largest product 6.

 Example 2:
 Input: [-2,0,-1]
 Output: 0
 Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 */
public class Pro_0152_MaximunProductSubarray {

    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
//        int product = maxProduct(nums);
        int product = maxProduct1(nums);
        System.out.println(product);
    }

    public static int maxProduct(int[] nums) {
        int n = nums.length;
        int maxP = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            int p = 1;
            for(int j=i; j<n; j++) {
                p *= nums[j];
                if(p > maxP) { maxP = p; }
            }
        }
        return maxP;
    }

    public static int maxProduct1(int[] nums) {
        int n = nums.length;
        int p = nums[0], maxPEndWithI = p, maxP = p;
        for(int i=1; i<n; i++) {
            int k = nums[i];
            if(k > 0) {
                maxPEndWithI = maxPEndWithI > 0 ? maxPEndWithI*k : k;
            }else if(k == 0) {
                maxPEndWithI = 0;
            }else {
                if(p < 0) {
                    maxPEndWithI = p * k;
                }else if(p > 0) {
                    maxPEndWithI = k;
                    int subP = p;
                    for(int j=0; j<i; j++) {
                        subP /= nums[j];
                        if(subP < 0) {
                            maxPEndWithI = subP * k;
                            break;
                        }
                    }
                }else { // p == 0
                    maxPEndWithI = k;
                    int subP = k;
                    for(int j=i-1; j>=0; j--) {
                        int v = nums[j];
                        subP *= v;
                        if(subP > maxPEndWithI) { maxPEndWithI = subP; }
                        if(v == 0) { break; }
                    }
                }
            }
            if(maxPEndWithI > maxP) { maxP = maxPEndWithI; }
            p *= k;
        }
        return maxP;
    }
}
