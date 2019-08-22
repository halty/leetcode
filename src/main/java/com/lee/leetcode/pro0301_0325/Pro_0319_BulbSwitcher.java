package com.lee.leetcode.pro0301_0325;

import java.util.BitSet;

/**
 *
 There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb.
 On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

 Example:
 Input: 3
 Output: 1
 Explanation:
    At first, the three bulbs are [off, off, off].
    After first round, the three bulbs are [on, on, on].
    After second round, the three bulbs are [on, off, on].
    After third round, the three bulbs are [on, off, off].
    So you should return 1, because there is only one bulb is on.
 *
 */
public class Pro_0319_BulbSwitcher {

    public static void main(String[] args) {
        int n = 3;
        int result = bulbSwitch(n);
//        int result = bulbSwitch1(n);
//        int result = bulbSwitch2(n);
        System.out.println(result);
    }

    public static int bulbSwitch(int n) {
        // 1 - off, 0 - on
        if(n == 0) { return 0; }
        if(n < 3) { return 1; }
        int count = n;
        int[] bulbs = new int[n + 1];
        int end = n >> 1;
        for(int i=2; i<=end; i++) {
            long m = i;
            int k;
            while(m <= n) {
                k = (int)m;
                bulbs[k] = 1 - bulbs[k];
                m += i;
            }
            count -= bulbs[i];
        }
        for(int i=end+1; i<=n; i++) {
            count -= 1 - bulbs[i];
        }
        return count;
    }

    public static int bulbSwitch1(int n) {
        // 1 - off, 0 - on
        if(n == 0) { return 0; }
        if(n < 3) { return 1; }
        BitSet set = new BitSet(n+1);
        set.set(0);
        int end = n >> 1;
        for(int i=2; i<=end; i++) {
            long m = i;
            int k;
            while(m <= n) {
                k = (int)m;
                set.flip(k);
                m += i;
            }
        }
        set.flip(end+1, n+1);
        return n + 1 - set.cardinality();
    }

    public static int bulbSwitch2(int n) {
        /*
         * given n bulbs, for i in [1, n], if bulbs[i] toggle odd times, bulbs[i] is on, otherwise off for even times.
         * if i can be represents i = k*k, then bulbs[i] has been toggle odd times, otherwise toggle even times.
         * like 1 = 1*1; 2 = 1*2; 3 = 1*3; 4 = 1*4,2*2; 5 = 1*5; 6 = 1*6, 2*3; 7 = 1*7; 8 = 1*8,2*4; 9 = 1*9, 3*3;
         * for example:
         * index         1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32
         * -------------------------------------------------------------------------------------------------------------
         * toggle times  1  2  2  3  2  4  2  4  3  4  2  6  2  2  4  5  2  6  2  6  4  4  2  8  3  4  4  6  2  6  2  6
         * -------------------------------------------------------------------------------------------------------------
         * state         1  0  0  1  0  0  0  0  1  0  0  0  0  0  0  1  0  0  0  0  0  0  0  0  1  0  0  0  0  0  0  0
         *
         * so the number of bulbs are on is : k = (int)sqrt(n)
         */
        return (int)Math.sqrt(n);
    }
}
