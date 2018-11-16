package com.lee.leetcode.pro0101_0125;

/**
 *
 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most two transactions.

 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

 Example 1:
 Input: [3,3,5,0,0,3,1,4]
 Output: 6
 Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

 Example 2:
 Input: [1,2,3,4,5]
 Output: 4
 Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 engaging multiple transactions at the same time. You must sell before buying again.

 Example 3:
 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 */
public class Pro_0123_BestTimeToBuyAndSellStockIII {

    public static void main(String[] args) {
        int[] prices = {7,6,4,3,1};
        int profit = maxProfit(prices);
        System.out.println(profit);
    }

    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if(len > 2) {
            // sequential order
            int[] profits = new int[len];
            profits[0] = 0;
            int min = prices[0];
            for(int i=1; i<len; i++) {
                if(prices[i] > min) {
                    int profit = prices[i] - min;
                    profits[i] = Math.max(profits[i-1], profit);
                }else {
                    min = prices[i];
                    profits[i] = profits[i-1];
                }
            }
            // reversed order
            int[] reversedProfits = new int[len];
            int end = len - 1;
            reversedProfits[end] = 0;
            int max = prices[end];
            for(int i=end-1; i>=0; i--) {
                if(prices[i] < max) {
                    int profit = max - prices[i];
                    reversedProfits[i] = Math.max(reversedProfits[i+1], profit);
                }else {
                    max = prices[i];
                    reversedProfits[i] = reversedProfits[i+1];
                }
            }
            // merge
            int maxProfit = reversedProfits[0];
            for(int i=0; i<end; i++) {
                int profit = profits[i] + reversedProfits[i+1];
                if(maxProfit < profit) {
                    maxProfit = profit;
                }
            }
            return maxProfit;
        }else if(len == 2) {
            return prices[1] > prices[0] ? prices[1]-prices[0] : 0;
        }else {
            return 0;
        }
    }
}
