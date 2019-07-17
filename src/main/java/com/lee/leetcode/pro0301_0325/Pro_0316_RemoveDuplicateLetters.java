package com.lee.leetcode.pro0301_0325;

import java.util.Arrays;

/**
 *
 Given a string which contains only lowercase letters, remove dulexicographical orderplicate letters so that every letter appear once and only once.
 You must make sure your result is the smallest in  among all possible results.

 Example 1:

 Input: "bcabc"
 Output: "abc"
 Example 2:

 Input: "cbacdcbc"
 Output: "acdb"
 *
 */
public class Pro_0316_RemoveDuplicateLetters {

    public static void main(String[] args) {
        String s = "cbacaedce";
        String result = removeDuplicateLetters(s);
        System.out.println(result);
    }

    public static String removeDuplicateLetters(String s) {
        if(s.length() <= 1) { return s; }
        Context ctx = index(s);
        char[] buf = removeDuplicate(ctx);
        return new String(buf);
    }

    private static Context index(String s) {
        Context ctx = new Context();
        int len = s.length();
        for(int i=0; i<len; i++) {
            ctx.addChar(s.charAt(i), i);
        }
        return ctx;
    }

    private static char[] removeDuplicate(Context ctx) {
        char[] result = new char[ctx.letterCount];
        for(int i = 0; i<ctx.letterCount; i++) {
            for(int offset=0; offset<ctx.counts.length; offset++) {
                if(ctx.counts[offset] == 0) { continue; }
                int idx = ctx.indexes[offset][0];
                if(match(offset, idx, ctx)) {
                    remove(offset, idx, ctx);
                    result[i] = (char)('a' + offset);
                    break;
                }
            }
        }
        return result;
    }

    private static boolean match(int offset, int idx, Context ctx) {
        for(int i=0; i<ctx.counts.length; i++) {
            int count = ctx.counts[i];
            if(count == 0 || i == offset) { continue; }
            if(ctx.indexes[i][count-1] < idx) {
                return false;
            }
        }
        return true;
    }

    private static void remove(int offset, int idx, Context ctx) {
        for(int i=0; i<ctx.counts.length; i++) {
            int count = ctx.counts[i];
            if(count == 0 || i == offset) { continue; }
            int[] index = ctx.indexes[i];
            int j = 0;
            while(j < count) {
                if(index[j] > idx) { break; }
                j++;
            }
            if(j > 0) {
                count -= j;
                System.arraycopy(index, j, index, 0, count);
                ctx.counts[i] = count;
            }
        }
        ctx.counts[offset] = 0;
    }

    private static class Context {
        int[][] indexes;
        int[] counts;
        int letterCount;

        Context() {
            indexes = new int[26][];
            counts = new int[26];
            letterCount = 0;
        }

        void addChar(char ch, int idx) {
            int offset = ch - 'a';
            int cnt = counts[offset];
            int[] index = indexes[offset];
            if(cnt == 0) {
                indexes[offset] = index = new int[10];
                letterCount++;
            }
            if(cnt == index.length) {
                indexes[offset] = index = Arrays.copyOf(index, cnt + (cnt >> 1));
            }
            index[cnt++] = idx;
            counts[offset] = cnt;
        }
    }
}
