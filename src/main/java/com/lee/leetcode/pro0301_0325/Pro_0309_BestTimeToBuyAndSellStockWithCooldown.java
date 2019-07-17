package com.lee.leetcode.pro0301_0325;

/**
 *
 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
    1. You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
    2. After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

 Example:
    Input: [1,2,3,0,2]
    Output: 3
    Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *
 */
public class Pro_0309_BestTimeToBuyAndSellStockWithCooldown {

    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
//        int maxProfit = maxProfit(prices);
        int maxProfit = maxProfit1(prices);
        System.out.println(maxProfit);
    }

    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if(len < 2) { return 0; }
        int end = len - 1;
        int[] sells = new int[len];
        int[] holds = new int[len];
        sells[1] = prices[1] - prices[0];
        for(int i=2; i<=end; i++) {
            int p = prices[i];
            int maxProfit = p - prices[0], profit;
            for(int j=1; j<i; j++) {
                profit = p - prices[j] + holds[j-1];
                if(profit > maxProfit) {
                    maxProfit = profit;
                }
            }
            sells[i] = maxProfit;
            holds[i] = Math.max(holds[i-1], sells[i-1]);
        }
        return Math.max(sells[end], holds[end]);
    }

    public static int maxProfit1(int[] prices) {
        int len = prices.length;
        if(len < 2) { return 0; }
        int end = len - 1;
        int[] sells = new int[len];
        int[] holds = new int[len];
        sells[1] = prices[1] - prices[0];
        for(int cur=2; cur<=end; cur++) {
            int prev = cur - 1;
            int d = prices[cur] - prices[prev];
            sells[cur] = Math.max(sells[prev], holds[prev-1]) + d;
            holds[cur] = Math.max(holds[prev], sells[prev]);
        }
        return Math.max(sells[end], holds[end]);
    }
}
