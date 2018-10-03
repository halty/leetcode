package com.lee.leetcode.pro0051_0075;

/*
 *
The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
 *
 */
public class Pro_0060_PermutationSequence {

    public static void main(String[] args) {
        /*int n = 4, k = 9;
        String result = getPermutation(n, k);
        System.out.println(result);*/
        int n = 4;
        int c = 1;
        for(int i=1; i<=4; i++) {
            c *= i;
        }
        for(int i=1; i<=c; i++) {
            System.out.println(getPermutation(n, i));
        }
    }

    public static String getPermutation(int n, int k) {
        char[] chars = new char[n];
        int[] factorial = new int[n+1];
        int f = factorial[0] = 1;
        for(int i=1; i<=n; i++) {
            chars[i-1] = (char)(i + '0');
            factorial[i] = f = f * i;
        }

        int end = n - 1;
        int r = k - 1;
        int q;
        for(int i=0; i<n-1; i++) {
            f = factorial[end - i];
            q = r / f;
            r %= f;
            if(q > 0) { // move char[i+q] to char[i]
                char tmp = chars[i + q];
                System.arraycopy(chars, i, chars, i + 1, q);
                chars[i] = tmp;
            }
        }
        return new String(chars);
    }
}
