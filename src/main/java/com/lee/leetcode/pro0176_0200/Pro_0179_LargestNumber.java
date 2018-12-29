package com.lee.leetcode.pro0176_0200;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 Given a list of non negative integers, arrange them such that they form the largest number.

 Example 1:
 Input: [10,2]
 Output: "210"

 Example 2:
 Input: [3,30,34,5,9]
 Output: "9534330"

 Note: The result may be very large, so you need to return a string instead of an integer.
 *
 */
public class Pro_0179_LargestNumber {

    public static void main(String[] args) {
        int[] nums = {0,0,0};
        String result = largestNumber(nums);
        System.out.println(result);
    }

    public static String largestNumber(int[] nums) {
        int n = nums.length, len = 0;
        if(n == 0) { return ""; }
        String[] numStr = new String[n];
        for(int i=0; i<n; i++) {
            String s = Integer.toString(nums[i]);
            numStr[i] = s;
            len += s.length();
        }
        Arrays.sort(numStr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int sLen1 = s1.length(), sLen2 = s2.length();
                int i = 0, j = 0;
                for(; i<sLen1 && j<sLen2; i++, j++) {
                    int r = cmp(s1.charAt(i), s2.charAt(j));
                    if(r != 0) { return r; }
                }
                if(sLen1 == sLen2) { return 0; }
                if(i == sLen1) {
                    for(i=0; i<sLen2 && j<sLen2; i++, j++) {
                        int r = cmp(s2.charAt(i), s2.charAt(j));
                        if(r != 0) { return r; }
                    }
                    for(j=0; i<sLen2 && j<sLen1; i++, j++) {
                        int r = cmp(s2.charAt(i), s1.charAt(j));
                        if(r != 0) { return r; }
                    }
                }else {
                    for(j=0; i<sLen1 && j<sLen1; i++, j++) {
                        int r = cmp(s1.charAt(i), s1.charAt(j));
                        if(r != 0) { return r; }
                    }
                    for(i=0; i<sLen2 && j<sLen1; i++, j++) {
                        int r = cmp(s2.charAt(i), s1.charAt(j));
                        if(r != 0) { return r; }
                    }
                }
                return 0;
            }
        });
        if(numStr[0].equals("0")) { return "0"; }
        StringBuilder buf = new StringBuilder(len);
        for(int i=0; i<n; i++) {
            buf.append(numStr[i]);
        }
        return buf.toString();
    }

    private static int cmp(char ch1, char ch2) {
        // desc
        if(ch1 < ch2) {
            return 1;
        }else if(ch1 > ch2) {
            return -1;
        }else {
            return 0;
        }
    }
}
