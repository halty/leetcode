package com.lee.leetcode.pro0126_0150;

/**
 *
 Given a string s, partition s such that every substring of the partition is a palindrome.
 Return the minimum cuts needed for a palindrome partitioning of s.

 Example:
 Input: "aab"
 Output: 1
 Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 */
public class Pro_0132_PalindromePartitioningII {

    public static void main(String[] args) {
        String s = "aab";
        int result = minCut(s);
        System.out.println(result);
    }

    public static int minCut(String s) {
        int len = s.length();
        switch(len) {
            case 0:
            case 1: return 0;
            case 2:
                char a = s.charAt(0), b = s.charAt(1);
                return a == b ? 0 : 1;
            default:
//                return calcMinCut(s, len);
                return calcMinCut2(s, len);
        }
    }

    private static int calcMinCut(String s, int len) {
        boolean[][] state = new boolean[len][len];
        int[] partitions = new int[len];
        partitions[0] = 0;
        for(int j=1; j<len; j++) {
            int minCut = partitions[j-1] + 1;
            char ch = s.charAt(j);
            int k = j - 1;
            for(int i=0; i<j; i++) {
                boolean isPalindrome = s.charAt(i)==ch && (i+1 >= k || state[i+1][k]);    // substring[i,j] is Palindrome or not
                state[i][j] = isPalindrome;
                if(isPalindrome) {
                    if(i > 0) {
                        int cut = partitions[i-1] + 1;
                        if(minCut > cut) { minCut = cut; }
                    }else {
                        minCut = 0;
                        break;
                    }
                }
            }
            partitions[j] = minCut;
        }
        return partitions[len-1];
    }

    private static int calcMinCut2(String s, int len) {
        int[] cuts = new int[len+1];
        for(int i=0; i<=len; i++) { cuts[i] = i-1; }    // min cut for first i chars
        char[] chars = s.toCharArray();
        cuts[2] = chars[0] == chars[1] ? 0 : 1;   // avoid i=0 iteration
        for(int i=1; i<len; i++) {  // cuts array as DP cache
            cuts[i+1] = Math.min(cuts[i+1], cuts[i]+1);
            int begin = i-1;
            int end = i+1;
            while(begin>=0 && end<len && chars[begin]==chars[end]) {  // s[i] as center, odd number palindrome
                cuts[end+1] = Math.min(cuts[end+1], cuts[begin]+1);
                begin--;
                end++;
            }
            begin = i;
            end = i+1;
            while(begin>=0 && end<len && chars[begin]==chars[end]) {  // center between s[i] and s[i+1], even number palindrome
                cuts[end+1] = Math.min(cuts[end+1], cuts[begin]+1);
                begin--;
                end++;
            }
        }
        return cuts[len];
    }
}
