package com.lee.leetcode.pro0126_0150;

/**
 *
 There are N children standing in a line. Each child is assigned a rating value.
 You are giving candies to these children subjected to the following requirements:
    1. Each child must have at least one candy.
    2. Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?

 Example 1:
 Input: [1,0,2]
 Output: 5
 Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

 Example 2:
 Input: [1,2,2]
 Output: 4
 Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 The third child gets 1 candy because it satisfies the above two conditions.
 *
 */
public class Pro_0135_Candy {

    public static void main(String[] args) {
        int[] ratings = {1,0,2};
//        int total = candy(ratings);
        int total = candy1(ratings);
        System.out.println(total);
    }

    public static int candy(int[] ratings) {
        int total = 0;
        int n = ratings.length;
        if(n > 1) {
            int[] candies = new int[n];
            int end = n - 1;
            assignCandyRecursively(ratings, candies, 0, end, end);
            for(int candy : candies) {
                total += candy;
            }
        }else if(n == 1) {
            total = 1;
        }
        return total;
    }

    private static void assignCandyRecursively(int[] ratings, int[] candies, int begin, int end, int endPosition) {
        if(begin < end) {
            int minRating = ratings[begin];
            int minBegin = begin, minEnd = minBegin - 1;
            for(int i=begin+1; i<=end; i++) {
                int rating = ratings[i];
                if(minRating > rating) {
                    minRating = rating;
                    minBegin = i;
                    minEnd  = minBegin - 1;
                }else if(minRating < rating) {
                    if(minEnd < minBegin) {
                        minEnd = i - 1;
                    }
                }
            }
            if(minEnd < minBegin) {
                minEnd = end;
            }
            assignCandy(candies, minBegin, endPosition);
            int d = minEnd - minBegin;
            if(d > 0) {
                if(d == 1) {
                    if(minEnd == endPosition) {
                        candies[minEnd] = 1;
                    }else {
                        candies[minEnd] = candies[minEnd+1] + 1;
                    }
                }else {
                    assignCandy(candies, minEnd, endPosition);
                    for(int i=minBegin+1; i<minEnd; i++) {
                        candies[i] = 1;
                    }
                }
            }
            if(begin < minBegin) {
                assignCandyRecursively(ratings, candies, begin, minBegin-1, endPosition);
            }
            if(minEnd < end) {
                assignCandyRecursively(ratings, candies, minEnd+1, end, endPosition);
            }
        }else { // begin == end
            assignCandy(candies, begin, endPosition);
        }
    }

    private static void assignCandy(int[] candies, int index, int endPosition) {
        int maxCandy = 0;
        if(index > 0) {
            maxCandy = candies[index-1];
        }
        if(index < endPosition) {
            int candy = candies[index+1];
            if(maxCandy < candy) {
                maxCandy = candy;
            }
        }
        candies[index] = maxCandy + 1;
    }

    public static int candy1(int[] ratings) {
        int total = 0;
        int n = ratings.length;
        if(n > 1) {
            int prevRating = ratings[0];
            int prevCandy = 1;
            int cur = 1;
            total += prevCandy;
            while(cur < n) {
                int curRating = ratings[cur];
                if(curRating > prevRating) {    // asc seq
                    prevCandy += 1;
                    total += prevCandy;
                    cur++;
                    prevRating = curRating;
                }else if(curRating == prevRating) { // level seq
                    prevCandy = 1;
                    total += prevCandy;
                    cur++;
                    prevRating = curRating;
                }else { // desc seq
                    int e = cur + 1;
                    for(; e<n && ratings[e]<ratings[e-1]; e++);
                    int len = e - cur;
                    int beginCandy = 1 + len;
                    if(beginCandy > prevCandy) {
                        total += beginCandy - prevCandy;
                    }
                    total += beginCandy * len / 2;  // desc seq sum
                    prevCandy = 1;
                    cur = e;
                    prevRating = ratings[e-1];
                }
            }
        }else if(n == 1) {
            total = 1;
        }
        return total;
    }
}
