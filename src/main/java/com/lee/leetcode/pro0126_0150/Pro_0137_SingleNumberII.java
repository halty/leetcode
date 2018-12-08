package com.lee.leetcode.pro0126_0150;

/**
 *
 Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 Note:
    Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 Example 1:
 Input: [2,2,3,2]
 Output: 3

 Example 2:
 Input: [0,1,0,1,0,1,99]
 Output: 99
 *
 */
public class Pro_0137_SingleNumberII {

    public static void main(String[] args) {
        int[] nums = { -2,-2,1,1,-3,1,-3,-3,-4,-2};
//        int result = singleNumber(nums);
        int result = singleNumber1(nums);
        System.out.println(result);
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for(int i=0; i<32; i++) {   // numbers of bit for integer is 32
            int count = 0;
            for(int num : nums) {
                count += (num >> i) & 1;
            }
            if(count % 3 == 1) {
                result |= (1 << i);
            }
        }
        return result;
    }

    public static int singleNumber1(int[] nums) {
        /* 模拟三进制
         *   one - 假设其第i个二进制位为1，则表示已累计统计的N个数中，其对应第i位二进制位为1的个数有1个；
         *   two - 假设其第i个二进制位为1，则表示已累计统计的N个数中，其对应第i位二进制位为1的个数有2个；
         * 依次类推
         * 模拟N进制
         *   N-1 - 假设其第i个二进制位为1，则表示已累计统计的N个数中，其对应第i位二进制位为1的个数有N-1个；
         */
        int one = 0, two = 0;
        for(int num : nums) {
            int x = one ^ num;
            int y = one & num;  // 1 + 1 进位
            one = (y & two) ^ x;    // (2 + 2 进位) ^ x
            two = y ^ two;
        }
        one &= one ^ two;   // 1 + 2 = 3 清0
        return one;
    }
}
