package com.lee.leetcode.pro0176_0200;

import java.util.Arrays;

/**
 *
 Say you have an array for which the ith element is the price of a given stock on day i.
 Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Note: You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 Example 1:
 Input: [2,4,1], k = 2
 Output: 2
 Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

 Example 2:
 Input: [3,2,6,5,0,3], k = 2
 Output: 7
 Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 */
public class Pro_0188_BestTimeToBuyAndSellStockIV {

    public static void main(String[] args) {
        int k = 2;
//        int[] prices = {3,3,5,0,0,3,1,4}; // 6
        int[] prices = {3,2,6,5,0,3};   // 7
//        int profit = maxProfit(k, prices);
        int profit = maxProfit1(k, prices);
        System.out.println(profit);
    }

    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n < 2 || k < 1) { return 0; }
        if(n < 4) {
            int maxProfit = Math.max(0, prices[1]-prices[0]);
            if(n == 3) {
                int profit = Math.max(prices[2]-prices[1], prices[2]-prices[0]);
                maxProfit = Math.max(maxProfit, profit);
            }
            return maxProfit;
        }
        int maxCount = n >> 1;
        if(k == 1) {
            int maxProfit = 0;
            int min = prices[0];
            for(int i=1; i<n; i++) {
                int v = prices[i];
                if(v > min) {
                    maxProfit = Math.max(maxProfit, v-min);
                }else {
                    min = v;
                }
            }
            return maxProfit;
        }else if(k >= maxCount) {
            int maxProfit = 0;
            for(int i=1; i<n; i++) {
                int profit = prices[i] - prices[i-1];
                if(profit > 0) { maxProfit += profit; }
            }
            return maxProfit;
        }else { // 1 < k < n/2
//            return maxProfitWithK(prices, n, k);
            return maxProfitWithK1(prices, n, k);
        }
    }

    private static int maxProfitWithK(int[] prices, int n, int k) {
        int[][] profits = new int[n-1][k];
        Arrays.fill(profits[0], -1);
        profits[0][0] = Math.max(0, prices[1]-prices[0]);
        for(int i=2; i<n; i++) {
            int[] curProfit = profits[i-1];
            System.arraycopy(profits[i-2], 0, curProfit, 0, k);
            int sellPrice = prices[i];
            for(int j=i-1; j>1; j--) {
                int buyPrice = prices[j];
                if(sellPrice > buyPrice) {
                    updateCurrentProfit(k, curProfit, profits[j-2], sellPrice-buyPrice);
                }
            }
            int profit = Math.max(sellPrice-prices[1], sellPrice-prices[0]);
            if(profit > 0 && curProfit[0] < profit) {
                curProfit[0] = profit;
            }
        }
        int[] lastProfit = profits[n-2];
        int maxProfit = 0;
        for(int i=lastProfit.length-1; i>=0; i--) {
            maxProfit = lastProfit[i];
            if(maxProfit != -1) { break; }
        }
        return maxProfit;
    }

    private static void updateCurrentProfit(int k, int[] currentProfit, int[] prevProfit, int profit) {
        if(currentProfit[0] < profit) {
            currentProfit[0] = profit;
        }
        for(int i=1; i<k; i++) {
            int oldProfit = prevProfit[i-1];
            if(oldProfit == -1) { break; }
            int newProfit = oldProfit + profit;
            if(currentProfit[i] < newProfit) {
                currentProfit[i] = newProfit;
            }
        }
    }

    private static int maxProfitWithK1(int[] prices, int n, int k) {
        int len = n >> 1;
        int[] buyPrices = new int[len];
        int[] sellPrices = new int[len];
        int count = 0;
        int prevPrice = prices[0];
        int beginPrice = prevPrice;
        int begin = 0;
        for(int i=1; i<n; i++) {
            int price = prices[i];
            if(prevPrice > price) {
                if(i-begin > 1 && beginPrice != prevPrice) {
                    buyPrices[count] = beginPrice;
                    sellPrices[count] = prevPrice;
                    count++;
                }
                begin = i;
                beginPrice = price;
            }
            prevPrice = price;
        }
        if(n-begin > 1 && beginPrice != prevPrice) {
            buyPrices[count] = beginPrice;
            sellPrices[count] = prevPrice;
            count++;
        }
        if(count <= k) {
            int maxProfit = 0;
            for(int i=0; i<count; i++) {
                maxProfit += sellPrices[i] - buyPrices[i];
            }
            return maxProfit;
        }else {
            int[][] profits = new int[count][k];
            Arrays.fill(profits[0], -1);
            profits[0][0] = sellPrices[0]-buyPrices[0];
            for(int i=1; i<count; i++) {
                int[] curProfit = profits[i];
                System.arraycopy(profits[i-1], 0, curProfit, 0, k);
                int sellPrice = sellPrices[i];
                for(int j=i; j>0; j--) {
                    int[] prevProfit = profits[j-1];
                    int buyPrice = buyPrices[j];
                    if(sellPrice > buyPrice) {
                        int profit = sellPrice - buyPrice;
                        if(curProfit[0] < profit) { curProfit[0] = profit; }
                        for(int t=1; t<k; t++) {
                            int oldProfit = prevProfit[t-1];
                            if(oldProfit == -1) { break; }
                            int newProfit = oldProfit + profit;
                            if(curProfit[t] < newProfit) {
                                curProfit[t] = newProfit;
                            }
                        }
                    }
                }
                int profit = sellPrice - buyPrices[0];
                if(profit > curProfit[0]) { curProfit[0] = profit; }
            }
            return profits[count-1][k-1];
        }
    }

    public static int maxProfit1(int k, int[] prices) {
        int n = prices.length;
        if(n < 2 || k < 1) { return 0; }
        if(n < 4) {
            int maxProfit = Math.max(0, prices[1]-prices[0]);
            if(n == 3) {
                int profit = Math.max(prices[2]-prices[1], prices[2]-prices[0]);
                maxProfit = Math.max(maxProfit, profit);
            }
            return maxProfit;
        }
        int maxCount = n >> 1;
        if(k == 1) {
            int maxProfit = 0;
            int min = prices[0];
            for(int i=1; i<n; i++) {
                int v = prices[i];
                if(v > min) {
                    maxProfit = Math.max(maxProfit, v-min);
                }else {
                    min = v;
                }
            }
            return maxProfit;
        }else if(k >= maxCount) {
            int maxProfit = 0;
            for(int i=1; i<n; i++) {
                int profit = prices[i] - prices[i-1];
                if(profit > 0) { maxProfit += profit; }
            }
            return maxProfit;
        }else { // 1 < k < n/2
            int[][] profits = new int[k+1][n];
            for(int i=1; i<=k; i++) {   // 换个角度，先从次数开始考虑，再考虑卖出的时机
                int localProfit = -prices[0];
                for(int j=1; j<n; j++) {
                    // 分为第[j+1]天不参与交易、第[j+1]参与交易2种情况
                    // 则第i次在第[j+1]天的最大收益：max(第i次在第j天的最大收益(不参与交易)，第i次在第[j+1]天卖出的收益)
                    profits[i][j] = Math.max(profits[i][j-1], localProfit+prices[j]);
                    // 分为第[j+1]天不买入、第[j+1]买入2种情况
                    // 则局部的最大收益：max(第[j+1]天不买入的收益(维持不变)，第[i-1]次在第j天的最大收益减去第[j+1]天买入的成本)
                    localProfit = Math.max(localProfit, profits[i-1][j-1]-prices[j]);
                }
            }
            return profits[k][n-1];
        }
    }
}
