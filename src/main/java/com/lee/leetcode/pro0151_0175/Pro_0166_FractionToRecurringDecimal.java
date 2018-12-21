package com.lee.leetcode.pro0151_0175;

import java.util.HashMap;
import java.util.Map;

/**
 *
 Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 If the fractional part is repeating, enclose the repeating part in parentheses.

 Example 1:
 Input: numerator = 1, denominator = 2
 Output: "0.5"

 Example 2:
 Input: numerator = 2, denominator = 1
 Output: "2"

 Example 3:
 Input: numerator = 2, denominator = 3
 Output: "0.(6)"
 *
 */
public class Pro_0166_FractionToRecurringDecimal {

    public static void main(String[] args) {
        int numerator = -1;
        int denominator = Integer.MIN_VALUE;
        String result = fractionToDecimal(numerator, denominator);  // 0.0000000004656612873077392578125
        System.out.println(result);
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        StringBuilder buf = new StringBuilder();
        long n = numerator;
        long d = denominator;
        if(n*d < 0) { buf.append('-'); }
        n = Math.abs(n);
        d = Math.abs(d);
        long q = n / d;
        buf.append(q);
        long mod = n % d;
        if(mod == 0) { return buf.toString(); }
        buf.append('.');
        Map<Long, Integer> numeratorIndexMap = new HashMap<>();
        while(true) {
            int zeroCount = 0;
            for(mod*=10; mod<d; zeroCount++,mod*=10);
            Integer index = numeratorIndexMap.get(mod);
            if(index != null) {
                int i = index;
                for(; buf.charAt(i)=='0'; i++);
                i -= zeroCount;
                char ch = '(';
                buf.insert(i, ch);
                buf.append(')');
                return buf.toString();
            }else {
                index = buf.length();
                numeratorIndexMap.put(mod, index);
                for(int i=0; i<zeroCount; i++) { buf.append('0'); }
                q = (int)(mod / d);
                buf.append(q);
                mod = mod % d;
                if(mod == 0) { return buf.toString(); }
            }
        }
    }
}
