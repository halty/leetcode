package com.lee.leetcode.pro0301_0325;

/**
 *
 Additive number is a string whose digits can form additive sequence.

 A valid additive sequence should contain at least three numbers. Except for the first two numbers,
 each subsequent number in the sequence must be the sum of the preceding two.

 Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

 Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

 Example 1:
 Input: "112358"
 Output: true
 Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8

 Example 2:
 Input: "199100199"
 Output: true
 Explanation: The additive sequence is: 1, 99, 100, 199.
 1 + 99 = 100, 99 + 100 = 199

 Follow up: How would you handle overflow for very large input integers?
 *
 */
public class Pro_0306_AdditiveNumber {

    public static void main(String[] args) {
        String num = "121474836472147483648";
        boolean isAdditive = isAdditiveNumber(num);
        System.out.println(isAdditive);
    }

    public static boolean isAdditiveNumber(String num) {
        char[] chars = num.toCharArray();
        if(chars.length < 3) { return false; }
        int maxI = chars[0] == '0' ? 1 : (chars.length - 1) / 2;
        long first = 0;
        for(int i=0; i<maxI; i++) {
            first = first*10 + chars[i]-'0';
            int b = i + 1;
            if(chars[b] == '0') {
                if(isAdditive(chars, b+1, first, 0)) {
                    return true;
                }
            }else {
                int maxJ = 3 * b <= chars.length ? (chars.length + b) / 2 : chars.length - b;
                long second = 0;
                for(int j = b; j < maxJ; j++) {
                    second = second * 10 + chars[j] - '0';
                    int k = j + 1;
                    if(chars[k] == '0') {
                        continue;
                    }
                    if(isAdditive(chars, k, first, second)) {
                        return true;
                    }
                }
            }
        }
        return false;
}

    private static boolean isAdditive(char[] chars, int begin, long first, long second) {
        long target = first + second, sum = 0;
        while(begin < chars.length) {
            sum = sum*10 + chars[begin]-'0';
            if(sum < target) {
                begin++;
            }else if(sum > target) {
                return false;
            }else {
                first = second;
                second = sum;
                target = first + second;
                sum = 0;
                begin++;
                if(begin == chars.length) {
                    return true;
                }
                if(target > 0 && chars[begin] == '0') {
                    return false;
                }
            }
        }
        return false;
    }
}
