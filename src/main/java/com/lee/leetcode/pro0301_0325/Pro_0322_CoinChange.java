package com.lee.leetcode.pro0301_0325;

import java.util.Arrays;

/**
 *
 You are given coins of different denominations and a total amount of money amount.
 Write a function to compute the fewest number of coins that you need to make up that amount.
 If that amount of money cannot be made up by any combination of the coins, return -1.

 Example 1:
 Input: coins = [1, 2, 5], amount = 11
 Output: 3
 Explanation: 11 = 5 + 5 + 1

 Example 2:
 Input: coins = [2], amount = 3
 Output: -1

 Note: You may assume that you have an infinite number of each kind of coin.
 *
 */
public class Pro_0322_CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, Integer.MAX_VALUE};
        int amount = 1459;
//        int result = coinChange(coins, amount);
//        int result = coinChange1(coins, amount);
        int result = coinChange2(coins, amount);
        System.out.println(result);
    }

    public static int coinChange(int[] coins, int amount) {
        if(amount == 0) { return 0; }
        if(coins.length == 1) {
            return amount % coins[0] == 0 ? amount/coins[0] : -1;
        }
        return coinChangeRecursively(coins, amount);
    }

    public static int coinChangeRecursively(int[] coins, int amount) {
        int minCnt = -1, cnt, reserved;
        for(int coin : coins) {
            reserved = amount - coin;
            if(reserved > 0) {
                cnt = coinChangeRecursively(coins, reserved);
                if(cnt > 0) {
                    cnt += 1;
                    minCnt = minCnt == -1 ? cnt : Math.min(minCnt, cnt);
                }
            }else if(reserved == 0) {
                minCnt = 1;
                break;
            }
        }
        return minCnt;
    }

    public static int coinChange1(int[] coins, int amount) {
        if(amount == 0) { return 0; }
        if(coins.length == 1) { return amount % coins[0] == 0 ? amount/coins[0] : -1; }
        int[] counts = new int[amount+1];
        for(int coin : coins) {
            if(coin <= amount) { counts[coin] = 1; }
        }
        if(counts[amount] > 0) { return 1; }
        for(int i=1; i<amount; i++) {
            int count = counts[i];
            if(count == 0) { continue; }
            count += 1;
            long amt = i;
            for(int coin : coins) {
                long j = amt + coin;
                if(j > amount) { continue; }
                int cnt = counts[(int)j];
                counts[(int)j] = (cnt == 0 ? count : Math.min(count, cnt));
            }
        }
        return counts[amount] == 0 ? -1 : counts[amount];
    }

    public static int coinChange2(int[] coins, int amount) {
        if(amount == 0) { return 0; }
        if(coins.length == 1) { return amount % coins[0] == 0 ? amount/coins[0] : -1; }
        Arrays.sort(coins);
        Result result = new Result();
        coinChange2Recursively(coins, amount, coins.length-1, 0, result);
        return result.get();
    }

    private static void coinChange2Recursively(int[] coins, int amount, int coinIndex, int coinCount, Result result) {
        int coin = coins[coinIndex];
        int count = amount / coin;
        int remained = amount - coin * count;
        coinCount += count;
        if(remained == 0) {
            result.compareAndSet(coinCount);
            return;
        }
        if(coinIndex == 0) { return; }
        coinIndex -= 1;
        int nextCoin = coins[coinIndex];
        for(; count>=0; count--,coinCount--,remained+=coin) {
            if(result.notGreaterThan(coinCount + (remained+nextCoin-1)/nextCoin)) { break; }
            coinChange2Recursively(coins, remained, coinIndex, coinCount, result);
        }
    }

    private static class Result {
        int minCount;
        Result() {
            this.minCount = Integer.MAX_VALUE;
        }
        boolean notGreaterThan(int count) {
            return minCount <= count;
        }
        void compareAndSet(int count) {
            if(count < minCount) {
                minCount = count;
            }
        }
        int get() {
            return minCount == Integer.MAX_VALUE ? -1 : minCount;
        }
    }
}
