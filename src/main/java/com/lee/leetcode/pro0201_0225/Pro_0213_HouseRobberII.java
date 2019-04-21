package com.lee.leetcode.pro0201_0225;

/**
 *
 You are a professional robber planning to rob houses along a street.
 Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
 That means the first house is the neighbor of the last one.
 Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 Example 1:
 Input: [2,3,2]
 Output: 3
 Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

 Example 2:
 Input: [1,2,3,1]
 Output: 4
 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount you can rob = 1 + 3 = 4.
 *
 */
public class Pro_0213_HouseRobberII {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
//        int amount = rob1(nums);
        int amount = rob2(nums);
        System.out.println(amount);
    }

    public static int rob1(int[] nums) {
        int n = nums.length;
        switch(n) {
            case 0: return 0;
            case 1: return nums[0];
            case 2: return Math.max(nums[0], nums[1]);
            case 3: return Math.max(nums[0], Math.max(nums[1], nums[2]));
        }
        int[] pickNow = new int[n];
        int[] giveUpNow = new int[n];
        int[] pickNowWithoutFirst = new int[n];
        int[] giveUpNowWithoutFirst = new int[n];
        pickNow[0] = nums[0];
        giveUpNow[0] = 0;
        pickNowWithoutFirst[0] = 0;
        giveUpNowWithoutFirst[0] = 0;
        for(int i=1; i<n; i++) {
            int prev = i - 1;
            pickNow[i] = giveUpNow[prev] + nums[i];
            giveUpNow[i] = Math.max(pickNow[prev], giveUpNow[prev]);
            pickNowWithoutFirst[i] = giveUpNowWithoutFirst[prev] + nums[i];
            giveUpNowWithoutFirst[i] = Math.max(pickNowWithoutFirst[prev], giveUpNowWithoutFirst[prev]);
        }
        return Math.max(pickNowWithoutFirst[n-1], giveUpNow[n-1]);
    }

    public static int rob2(int[] nums) {
        int n = nums.length;
        switch(n) {
            case 0: return 0;
            case 1: return nums[0];
            case 2: return Math.max(nums[0], nums[1]);
            case 3: return Math.max(nums[0], Math.max(nums[1], nums[2]));
        }
        int pickNow = nums[0];
        int giveUpNow = 0;
        int pickNowWithoutFirst = 0;
        int giveUpNowWithoutFirst = 0;
        for(int i=1; i<n; i++) {
            int prev = i - 1;
            int tmp = pickNow;
            pickNow = giveUpNow + nums[i];
            giveUpNow = Math.max(tmp, giveUpNow);
            tmp = pickNowWithoutFirst;
            pickNowWithoutFirst = giveUpNowWithoutFirst + nums[i];
            giveUpNowWithoutFirst = Math.max(tmp, giveUpNowWithoutFirst);
        }
        return Math.max(pickNowWithoutFirst, giveUpNow);
    }
}
