package com.lee.leetcode.pro0251_0275;

/**
 *
 Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

 Example:
 Input: 38
 Output: 2
 Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
 Since 2 has only one digit, return it.

 Follow up: Could you do it without any loop/recursion in O(1) runtime?
 *
 */
public class Pro_0258_AddDigits {

    public static void main(String[] args) {
        int num = Integer.MAX_VALUE;
        int result = addDigits(num);
        System.out.println(result);
        result = addDigits1(num);
        System.out.println(result);
    }

    public static int addDigits(int num) {
        while(num >= 10) {
            int r = 0;
            do {
                r += num % 10;
                num /= 10;
            }while(num != 0);
            num = r;
        }
        return num;
    }

    public static int addDigits1(int num) {
        return num < 10 ? num : ((num%=9) == 0 ? 9 : num);
    }
}
