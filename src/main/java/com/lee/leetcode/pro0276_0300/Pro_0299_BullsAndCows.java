package com.lee.leetcode.pro0276_0300;

import java.util.Arrays;

/**
 *
 You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is.
 Each time your friend makes a guess, you provide a hint that indicates
 how many digits in said guess match your secret number exactly in both digit and position (called "bulls")
 and how many digits match the secret number but locate in the wrong position (called "cows").
 Your friend will use successive guesses and hints to eventually derive the secret number.

 Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.

 Please note that both secret number and friend's guess may contain duplicate digits.

 Example 1:
 Input: secret = "1807", guess = "7810"
 Output: "1A3B"
 Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.

 Example 2:
 Input: secret = "1123", guess = "0111"
 Output: "1A1B"
 Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.

 Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 *
 */
public class Pro_0299_BullsAndCows {

    public static void main(String[] args) {
        String secret = "1807", guess = "7810";
        String hint = getHint(secret, guess);
        System.out.println(hint);
    }

    public static String getHint(String secret, String guess) {
        int[][] secretHisto = histogram(secret);
        int[][] guessHisto = histogram(guess);
        int bulls = 0, cows = 0;
        for(int k=0; k<10; k++) {
            int[] secretIndexes = secretHisto[k];
            int[] guessIndexes = guessHisto[k];
            if(secretIndexes == null || guessIndexes == null) {
                continue;
            }
            int sLen = secretIndexes[0];
            int gLen = guessIndexes[0];
            int i = 1;
            int j = 1;
            int bullsCnt = 0;
            while(i <= sLen && j <= gLen) {
                int sIdx = secretIndexes[i];
                int gIdx = guessIndexes[j];
                if(sIdx == gIdx) {
                    bullsCnt += 1;
                    i++;
                    j++;
                }else if(sIdx < gIdx) {
                    i++;
                }else {
                    j++;
                }
            }
            bulls += bullsCnt;
            cows += Math.min(sLen, gLen) - bullsCnt;
        }
        return bulls + "A" + cows + "B";
    }

    private static int[][] histogram(String str) {
        int[][] histogram = new int[10][];
        int len = str.length();
        for(int i=0; i<len; i++) {
            int idx = str.charAt(i) - '0';
            int[] indexes = histogram[idx];
            if(indexes == null) {
                histogram[idx] = indexes = new int[10];
                indexes[0] = 1;
                indexes[1] = i;
            }else {
                int next = indexes[0] + 1;
                if(next == indexes.length) {
                    indexes = Arrays.copyOf(indexes, next + (next >> 1));
                    histogram[idx] = indexes;
                }
                indexes[0] = next;
                indexes[next] = i;
            }
        }
        return histogram;
    }
}
