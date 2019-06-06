package com.lee.leetcode.pro0251_0275;

/**
 *
 Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher,
 write a function to compute the researcher's h-index.

 According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
 and the other N − h papers have no more than h citations each."

 Example:
 Input: citations = [0,1,3,5,6]
 Output: 3
 Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
              received 0, 1, 3, 5, 6 citations respectively.
              Since the researcher has 3 papers with at least 3 citations each and the remaining
              two with no more than 3 citations each, her h-index is 3.

 Note: If there are several possible values for h, the maximum one is taken as the h-index.

 Follow up:
    This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
    Could you solve it in logarithmic time complexity?
 *
 */
public class Pro_0275_HIndexII {

    public static void main(String[] args) {
        int[] citations = {0,1,2,5,6};
//        int h = hIndex(citations);
        int h = hIndex1(citations);
        System.out.println(h);
    }

    public static int hIndex(int[] citations) {
        int n = citations.length;
        if(n == 0) { return 0; }
        int i = 1;
        for(; i<=n; i++) {
            if(citations[n-i] < i) { break; }
        }
        return i-1;
    }

    public static int hIndex1(int[] citations) {
        int n = citations.length;
        if(n == 0) { return 0; }
        int b = 0, e = n-1, m;
        while(b <= e) {
            m = b + (e-b) / 2;
            int c = citations[m];
            int cnt = n - m;
            if(c > cnt) {
                e = m - 1;
            }else if(c < cnt) {
                b = m + 1;
            }else {
                return cnt;
            }
        }
        return n-b;
    }
}
