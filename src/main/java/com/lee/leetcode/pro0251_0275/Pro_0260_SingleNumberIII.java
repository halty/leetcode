package com.lee.leetcode.pro0251_0275;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 Find the two elements that appear only once.

 Example:
 Input:  [1,2,1,3,2,5]
 Output: [3,5]

 Note:
    The order of the result is not important. So in the above example, [5, 3] is also correct.
    Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 *
 */
public class Pro_0260_SingleNumberIII {

    public static void main(String[] args) {
        int[] nums = {-1638685546,-2084083624,-307525016,-930251592,-1638685546,1354460680,623522045,-1370026032,-307525016,-2084083624,-930251592,472570145,-1370026032,1063150409,160988123,1122167217,1145305475,472570145,623522045,1122167217,1354460680,1145305475};
        int[] result = singleNumber(nums);
        System.out.println(Arrays.toString(result));
        result = singleNumber1(nums);
        System.out.println(Arrays.toString(result));
    }

    public static int[] singleNumber(int[] nums) {
        if(nums.length == 2) { return nums; }
        Set<Integer> set = new HashSet<>();
        for(Integer num : nums) {
            if(!set.remove(num)) {
                set.add(num);
            }
        }
        int[] result = new int[set.size()];
        int i = 0;
        for(Integer num : set) {
            result[i++] = num;
        }
        return result;
    }

    public static int[] singleNumber1(int[] nums) {
        if(nums.length == 2) { return nums; }
        int r = 0;
        for(int num : nums) {
            r ^= num;
        }
        int mask = r & -r;
        int a = 0, b = 0;
        for(int num : nums) {
            if((mask & num) == 0) {
                a ^= num;
            }else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }
}
