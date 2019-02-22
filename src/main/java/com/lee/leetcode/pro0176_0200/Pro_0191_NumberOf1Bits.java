package com.lee.leetcode.pro0176_0200;

/**
 *
 Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).

 Example 1:
 Input: 00000000000000000000000000001011
 Output: 3
 Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.

 Example 2:
 Input: 00000000000000000000000010000000
 Output: 1
 Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.

 Example 3:
 Input: 11111111111111111111111111111101
 Output: 31
 Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.

 Note:
   1. Note that in some languages such as Java, there is no unsigned integer type.
      In this case, the input will be given as signed integer type and should not affect your implementation,
      as the internal binary representation of the integer is the same whether it is signed or unsigned.
   2. In Java, the compiler represents the signed integers using 2's complement notation.
      Therefore, in Example 3 above the input represents the signed integer -3.

 Follow up:
 If this function is called many times, how would you optimize it?
 *
 */
public class Pro_0191_NumberOf1Bits {

    public static void main(String[] args) {
//        int n = 0b1011;
        int n = 0b11111111111111111111011101111101;
//        int r = hammingWeight(n);
        int r = hammingWeight1(n);
        System.out.println(r);
    }

    public static int hammingWeight(int n) {
        int c = n & 0x01;
        for(int i=1; i<32; i++) {
            c += (0x01 & (n >>> i));
        }
        return c;
    }

    public static int hammingWeight1(int n) {
        n = ((n >>> 1) & 0x55555555) + (n & 0x55555555);
        n = ((n >>> 2) & 0x33333333) + (n & 0x33333333);
        n = ((n >>> 4) & 0x0f0f0f0f) + (n & 0x0f0f0f0f);
        // n = (n & 0x0f) + ((n >>> 8) & 0x0f) + ((n >>> 16) & 0x0f) + (n >>> 24);
        n = ((n >>> 8) & 0x00ff00ff) + (n & 0x00ff00ff);
        n = ((n >>> 16) & 0x0000ffff) + (n & 0x0000ffff);
        return n;
    }
}
