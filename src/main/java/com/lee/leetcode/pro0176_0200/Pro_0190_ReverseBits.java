package com.lee.leetcode.pro0176_0200;

/**
 *
 Reverse bits of a given 32 bits unsigned integer.

 Example 1:
 Input: 00000010100101000001111010011100
 Output: 00111001011110000010100101000000
 Explanation:
   The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
   so return 964176192 which its binary representation is 00111001011110000010100101000000.

 Example 2:
 Input: 11111111111111111111111111111101
 Output: 10111111111111111111111111111111
 Explanation:
   The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293,
   so return 3221225471 which its binary representation is 10101111110010110010011101101001.

 Note:
   1. Note that in some languages such as Java, there is no unsigned integer type.
      In this case, both input and output will be given as signed integer type and should not affect your implementation,
      as the internal binary representation of the integer is the same whether it is signed or unsigned.
   2. In Java, the compiler represents the signed integers using 2's complement notation.
      Therefore, in Example 2 above the input represents the signed integer -3 and the output represents the signed integer -1073741825.

 Follow up:
 If this function is called many times, how would you optimize it?
 *
 */
public class Pro_0190_ReverseBits {

    public static void main(String[] args) {
        int n = 0b00000010100101000001111010011100;
        System.out.println(String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'));
//        int r = reverseBits(n);
        int r = reverseBits1(n);
        System.out.println(String.format("%32s", Integer.toBinaryString(r)).replace(' ', '0'));
    }

    public static int reverseBits(int n) {
        int r = 0x01 & n;
        for(int i=1; i<32; i++) {
            r <<= 1;
            r |= (0x01 & (n >>> i));
        }
        return r;
    }

    public static int reverseBits1(int n) {
        n = (n & 0x55555555) << 1 | (n >>> 1) & 0x55555555;
        n = (n & 0x33333333) << 2 | (n >>> 2) & 0x33333333;
        n = (n & 0x0f0f0f0f) << 4 | (n >>> 4) & 0x0f0f0f0f;
        n = (n << 24) | ((n & 0xff00) << 8) |
                ((n >>> 8) & 0xff00) | (n >>> 24);
        return n;
    }
}
