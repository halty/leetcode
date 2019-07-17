package com.lee.leetcode.pro0301_0325;

/**
 *
 Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

 Find the maximum coins you can collect by bursting the balloons wisely.

 Note:
    You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
    0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

 Example:
 Input: [3,1,5,8]
 Output: 167
 Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
              coins =  3*1*5     +  3*5*8    +   1*3*8     + 1*8*1   = 167
 *
 */
public class Pro_0312_BurstBalloons {

    public static void main(String[] args) {
        int[] nums = {1,3,5,8};
//        int maxCoins = maxCoins(nums);
//        int maxCoins = maxCoins1(nums);
        int maxCoins = maxCoins2(nums);
        System.out.println(maxCoins);
    }

    public static int maxCoins(int[] nums) {
        int end = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) { nums[end++] = nums[i]; }
        }
        if(end == 0) { return 0; }
        return maxCoinsRecursively(nums, end);
    }

    private static int maxCoinsRecursively(int[] nums, int end) {
        switch(end) {
            case 1: return nums[0];
            case 2: return nums[0]*nums[1]+Math.max(nums[0], nums[1]);
            case 3:
                return Math.max(nums[0]*nums[2]*(nums[1]+1)+Math.max(nums[0], nums[2]),
                                nums[0]*nums[1]+nums[1]*nums[2]+Math.max(Math.max(nums[0], nums[1]), nums[2]));
        }
        int num = nums[0];
        int max = nums[0] * nums[1];
        end -= 1;
        System.arraycopy(nums, 1, nums, 0, end);
        max += maxCoinsRecursively(nums, end);
        for(int i=0; i<end; i++) {
            int tmp = nums[i];
            nums[i] = num;
            num = tmp;
            int sum = nums[i]*num*(i+1==end? 1 : nums[i+1]) + maxCoinsRecursively(nums, end);
            if(sum > max) { max = sum; }
        }
        nums[end] = num;
        return max;
    }

    public static int maxCoins1(int[] nums) {
        int[] balloons = new int[nums.length+2];
        int end = 1;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) { balloons[end++] = nums[i]; }
        }
        if(end == 1) { return 0; }
        balloons[end++] = balloons[0] = 1;
        int[][] cache = new int[end][end];
        return maxCoinsRecursively(balloons, 0, end-1, cache);
    }

    private static int maxCoinsRecursively(int[] balloons, int begin, int end, int[][] cache) {
        if(begin + 1 == end) { return 0; }
        int maxCoins = cache[begin][end];
        if(maxCoins == 0) {
            int coins;
            for (int i = begin + 1; i < end; i++) {
                // i was the last burst balloon
                coins = maxCoinsRecursively(balloons, begin, i, cache)
                      + maxCoinsRecursively(balloons, i, end, cache)
                      + balloons[begin] * balloons[i] * balloons[end];
                if(coins > maxCoins) { maxCoins = coins; }
            }
            cache[begin][end] = maxCoins;
        }
        return maxCoins;
    }

    public static int maxCoins2(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n+2];
        int end = 1;
        for(int i = 0; i < n; i++) {
            if(nums[i] != 0) { balloons[end++] = nums[i]; }
        }
        if(end == 1) { return 0; }
        balloons[end++] = balloons[0] = 1;
        n = end - 2;
        int[][] cache = new int[end][end];
        for(int w=1; w<=n; w++) {
            int iMax = n-w+1;
            for(int i=1; i<=iMax; i++) {
                int j = i+w-1;
                int maxCoins = 0, coins;
                for(int k=i; k<=j; k++) {
                    // k was the last balloon
                    coins = balloons[i-1]*balloons[k]*balloons[j+1] + cache[i][k-1] + cache[k+1][j];
                    if(coins > maxCoins) { maxCoins = coins; }
                }
                cache[i][j] = maxCoins;
            }
        }
        return cache[1][n];
    }
}
