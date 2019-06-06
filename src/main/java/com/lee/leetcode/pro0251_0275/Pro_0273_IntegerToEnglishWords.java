package com.lee.leetcode.pro0251_0275;

/**
 *
 Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.

 Example 1:
 Input: 123
 Output: "One Hundred Twenty Three"

 Example 2:
 Input: 12345
 Output: "Twelve Thousand Three Hundred Forty Five"

 Example 3:
 Input: 1234567
 Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

 Example 4:
 Input: 1234567891
 Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 */
public class Pro_0273_IntegerToEnglishWords {

    public static void main(String[] args) {
        int num = 100;
        String result = numberToWords(num);
        System.out.println(result);
    }

    public static String numberToWords(int num) {
        if(num == 0) { return "Zero"; }
        int q, u = 1000000000;
        StringBuilder buf = new StringBuilder();
        while(u != 0) {
            q = num / u;
            num %= u;
            if(q != 0) {
                append(q, buf).append(unit(u));
            }
            u /= 1000;
        }
        buf.setLength(buf.length()-1);
        return buf.toString();
    }

    private static StringBuilder append(int r, StringBuilder buf) {
        int h = r / 100;
        if(h != 0) {
            buf.append(single(h)).append(unit(100));
        }
        r %= 100;
        if(r >= 20) {
            int t = r / 10;
            buf.append(ten(t));
            r %= 10;
            if(r != 0) {
                buf.append(single(r));
            }
        }else {
            buf.append(single(r));
        }
        return buf;
    }

    private static String single(int index) {
        switch(index) {
            case 1: return "One ";
            case 2: return "Two ";
            case 3: return "Three ";
            case 4: return "Four ";
            case 5: return "Five ";
            case 6: return "Six ";
            case 7: return "Seven ";
            case 8: return "Eight ";
            case 9: return "Nine ";
            case 10: return "Ten ";
            case 11: return "Eleven ";
            case 12: return "Twelve ";
            case 13: return "Thirteen ";
            case 14: return "Fourteen ";
            case 15: return "Fifteen ";
            case 16: return "Sixteen ";
            case 17: return "Seventeen ";
            case 18: return "Eighteen ";
            case 19: return "Nineteen ";
            default: return "";
        }
    }

    private static String ten(int index) {
        switch(index) {
            case 2: return "Twenty ";
            case 3: return "Thirty ";
            case 4: return "Forty ";
            case 5: return "Fifty ";
            case 6: return "Sixty ";
            case 7: return "Seventy ";
            case 8: return "Eighty ";
            case 9: return "Ninety ";
            default: return "";
        }
    }

    private static String unit(int index) {
        switch(index) {
            case 100: return "Hundred ";
            case 1000: return "Thousand ";
            case 1000000: return "Million ";
            case 1000000000: return "Billion ";
            default: return "";
        }
    }
}
