package com.lee.leetcode.pro0301_0325;

import java.util.Arrays;

/**
 *
 Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
 You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

 Example 1:
 Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
 Output: 16
 Explanation: The two words can be "abcw", "xtfn".

 Example 2:
 Input: ["a","ab","abc","d","cd","bcd","abcd"]
 Output: 4
 Explanation: The two words can be "ab", "cd".

 Example 3:
 Input: ["a","aa","aaa","aaaa"]
 Output: 0
 Explanation: No such pair of words.
 *
 */
public class Pro_0318_MaximumProductOfWordLengths {

    public static void main(String[] args) {
        String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
//        int max = maxProduct(words);
//        int max = maxProduct1(words);
        int max = maxProduct2(words);
        System.out.println(max);
    }

    public static int maxProduct(String[] words) {
        if(words.length < 2) { return 0; }
        int[] bits = bits(words);
        int maxP = 0, p;
        int end = words.length - 1;
        for(int i=0; i<end; i++) {
            int lenI = words[i].length();
            int maxLenJ = 0, lenJ;
            for(int j=i+1; j<words.length; j++) {
                if((bits[i] & bits[j]) == 0 && (lenJ = words[j].length()) > maxLenJ) {
                    maxLenJ = lenJ;
                }
            }
            if((p = lenI * maxLenJ) > maxP) {
                maxP = p;
            }
        }
        return maxP;
    }

    private static int[] bits(String[] words) {
        int[] bits = new int[words.length];
        for(int i=0; i<words.length; i++) {
            String word = words[i];
            int bit = 0;
            int len = word.length();
            for(int j=0; j<len; j++) {
                int offset = word.charAt(j) - 'a';
                bit |= (1 << offset);
            }
            bits[i] = bit;
        }
        return bits;
    }

    public static int maxProduct1(String[] words) {
        if(words.length < 2) { return 0; }
        Context ctx = prepare(words);
        if(Integer.bitCount(ctx.index) == 1) { return 0; }
        int maxP = 0, p;
        int offset = Integer.numberOfTrailingZeros(ctx.index);
        while(offset < 26) {
            ctx.index -= (1 << offset);
            p = maxProduct(ctx, offset);
            if(p > maxP) { maxP = p; }
            offset = Integer.numberOfTrailingZeros(ctx.index);
        }
        return maxP;
    }

    private static Context prepare(String[] words) {
        Context ctx = new Context();
        for(int i=0; i<words.length; i++) {
            String word = words[i];
            int bit = 0;
            int len = word.length();
            for(int j=0; j<len; j++) {
                int offset = word.charAt(j) - 'a';
                bit |= (1 << offset);
            }
            if(bit != 0) { ctx.add(bit, len); }
        }
        return ctx;
    }

    private static int maxProduct(Context ctx, int offset) {
        if(ctx.index == 0) { return 0; }
        int[] bits = ctx.bitsSet[offset];
        int[] lengths = ctx.lengthsSet[offset];
        int cnt = ctx.count[offset];
        int maxP = 0, p;
        for(int i=0; i<cnt; i++) {
            int bit = bits[i];
            int length = lengths[i];
            int index = ctx.index & (~bit);
            int idx = Integer.numberOfTrailingZeros(index);
            int maxLen = 0, len;
            while(idx < 26) {
                int[] anBits = ctx.bitsSet[idx];
                int[] anLengths = ctx.lengthsSet[idx];
                int anCnt = ctx.count[idx];
                for(int j=0; j<anCnt; j++) {
                    if((bit & anBits[j]) == 0 && (len=anLengths[j]) > maxLen) {
                        maxLen = len;
                    }
                }
                index -= (1 << idx);
                idx = Integer.numberOfTrailingZeros(index);
            }
            if((p = length * maxLen) > maxP) {
                maxP = p;
            }
        }
        return maxP;
    }

    private static class Context {
        int[][] bitsSet;
        int[][] lengthsSet;
        int[] count;
        int index;

        Context() {
            bitsSet = new int[26][];
            lengthsSet = new int[26][];
            count = new int[26];
            index = 0;
        }

        void add(int bit, int length) {
            int offset = Integer.numberOfTrailingZeros(bit);
            int mask = 1 << offset;
            if((index & mask) == 0) {
                index |= mask;
                bitsSet[offset] = new int[10];
                lengthsSet[offset] = new int[10];
                count[offset] = 0;
            }
            int cnt = count[offset];
            int[] bits = bitsSet[offset];
            int[] lengths = lengthsSet[offset];
            if(cnt == bits.length) {
                int newCnt = cnt + (cnt >> 1);
                bitsSet[offset] = bits = Arrays.copyOf(bits, newCnt);
                lengthsSet[offset] = lengths = Arrays.copyOf(lengths, newCnt);
            }
            bits[cnt] = bit;
            lengths[cnt] = length;
            count[offset] = cnt + 1;
        }
    }

    public static int maxProduct2(String[] words) {
        int count = words.length;
        int[] lengths = new int[count];
        int[] bits = new int[count];
        for(int i=0; i<count; i++) {
            String word = words[i];
            int len = word.length();
            int bit = 0;
            for(int j=0; j<len; j++) {
                int offset = word.charAt(j) - 'a';
                bit |= (1 << offset);
            }
            bits[i] = bit;
            lengths[i] = len;
        }
        int maxP = 0, p;
        int end = count - 1;
        for(int i=0; i<end; i++) {
            int lenI = lengths[i];
            int maxLenJ = 0;
            for(int j=i+1; j<count; j++) {
                if((bits[i] & bits[j]) == 0 && lengths[j] > maxLenJ) {
                    maxLenJ = lengths[j];
                }
            }
            if((p = lenI * maxLenJ) > maxP) {
                maxP = p;
            }
        }
        return maxP;
    }
}
