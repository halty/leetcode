package com.lee.leetcode.pro0176_0200;

/**
 *
 You are a professional robber planning to rob houses along a street.
 Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them
 is that adjacent houses have security system connected and it will automatically contact the police
 if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house,
 determine the maximum amount of money you can rob tonight without alerting the police.

 Example 1:
 Input: [1,2,3,1]
 Output: 4
 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 Total amount you can rob = 1 + 3 = 4.

 Example 2:
 Input: [2,7,9,3,1]
 Output: 12
 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 Total amount you can rob = 2 + 9 + 1 = 12.
 *
 */
public class Pro_0198_HouseRobber {

    public static void main(String[] args) {
//        int[] nums = {2,7,9,3,1};
        int[] nums = {1,2,3,1};
//        int[] nums = {155,44,52,58,250,225,109,118,211,73,137,96,137,89,174,66,134,26,25,205,239,85,146,73,55,6,122,196,128,50,61,230,94,208,46,243,105,81,157,89,205,78,249,203,238,239,217,212,241,242,157,79,133,66,36,165};
//        int result = rob(nums);
        int result = rob1(nums);
        System.out.println(result);
    }

    public static int rob(int[] nums) {
        int n = nums.length;
        if(n == 0) { return 0; }
        return robRecursively(nums, n-1);
    }

    private static int robRecursively(int[] nums, int i) {
        if(i == 0) {
            return nums[0];
        }else if(i == 1) {
            return Math.max(nums[1], nums[0]);
        }else {
            return Math.max(nums[i] + robRecursively(nums, i - 2), robRecursively(nums, i - 1));
        }
    }

    public static int rob1(int[] nums) {
        int n = nums.length;
        switch(n) {
            case 0: return 0;
            case 1: return nums[0];
            case 2: return Math.max(nums[0], nums[1]);
        }
        int[] maxAmountsWithRob = new int[n];
        int[] maxAmountsWithoutRob = new int[n];
        maxAmountsWithRob[0] = nums[0];
        maxAmountsWithoutRob[0] = 0;
        maxAmountsWithRob[1] = nums[1];
        maxAmountsWithoutRob[1] = nums[0];
        for(int i=2; i<n; i++) {
            maxAmountsWithRob[i] = maxAmountsWithoutRob[i-1] + nums[i];
            maxAmountsWithoutRob[i] = Math.max(maxAmountsWithRob[i-1], maxAmountsWithoutRob[i-1]);
        }
        return Math.max(maxAmountsWithRob[n-1], maxAmountsWithoutRob[n-1]);
    }

    public static int rob2(int[] nums) {
        int n = nums.length;
        switch(n) {
            case 0: return 0;
            case 1: return nums[0];
            case 2: return Math.max(nums[0], nums[1]);
        }
        int maxAmountsWithRob = nums[1];
        int maxAmountsWithoutRob = nums[0];
        for(int i=2; i<n; i++) {
            int amount = maxAmountsWithoutRob + nums[i];
            maxAmountsWithoutRob = Math.max(maxAmountsWithRob, maxAmountsWithoutRob);
            maxAmountsWithRob = amount;
        }
        return Math.max(maxAmountsWithRob, maxAmountsWithoutRob);
    }
}
