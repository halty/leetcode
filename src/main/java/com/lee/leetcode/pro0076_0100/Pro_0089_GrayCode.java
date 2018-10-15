package com.lee.leetcode.pro0076_0100;

import java.util.ArrayList;
import java.util.List;

/**
 *
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

Example 1:

Input: 2
Output: [0,1,3,2]
Explanation:
00 - 0
01 - 1
11 - 3
10 - 2

For a given n, a gray code sequence may not be uniquely defined.
For example, [0,2,3,1] is also a valid gray code sequence.

00 - 0
10 - 2
11 - 3
01 - 1
Example 2:

Input: 0
Output: [0]
Explanation: We define the gray code sequence to begin with 0.
             A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
             Therefore, for n = 0 the gray code sequence is [0].
 *
 */
public class Pro_0089_GrayCode {

    public static void main(String[] args) {
        int n = 4;
        List<Integer> result = grayCode(n);
        System.out.println(result);
    }

    public static List<Integer> grayCode(int n) {
        int count = (int) Math.pow(2, n);
        List<Integer> list = new ArrayList<>(count);
        list.add(0);
        if(n > 0) {
            grayCodeRecursively(n, list);
        }
        return list;
    }

    private static void grayCodeRecursively(int n, List<Integer> list) {
        if(n > 1) {
            grayCodeRecursively(n-1, list);
            int last = list.get(list.size()-1);
            last ^= (1 << (n-1));
            list.add(last);
            grayCodeRecursively(n-1, list);
        }else {
            int last = list.get(list.size()-1);
            last ^= (1 << (n-1));
            list.add(last);
        }
    }
}
