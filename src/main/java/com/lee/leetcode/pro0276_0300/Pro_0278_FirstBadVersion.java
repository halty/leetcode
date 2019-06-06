package com.lee.leetcode.pro0276_0300;

/**
 *
 You are a product manager and currently leading a team to develop a new product.
 Unfortunately, the latest version of your product fails the quality check.
 Since each version is developed based on the previous version, all the versions after a bad version are also bad.

 Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

 You are given an API bool isBadVersion(version) which will return whether version is bad.
 Implement a function to find the first bad version. You should minimize the number of calls to the API.

 Example:
 Given n = 5, and version = 4 is the first bad version.
     call isBadVersion(3) -> false
     call isBadVersion(5) -> true
     call isBadVersion(4) -> true
 Then 4 is the first bad version.
 *
 */
public class Pro_0278_FirstBadVersion {

    public static void main(String[] args) {
        int n = 1420736637;
        badVersion = 1150769282;
//        int v = firstBadVersion(n);
        int v = firstBadVersion1(n);
        System.out.println(v);
    }

    private static int badVersion;

    public static int firstBadVersion(int n) {
        for(int i=1; i<n; i++) {
            if(isBadVersion(i)) {
                return i;
            }
        }
        return n;
    }

    public static int firstBadVersion1(int n) {
        int b = 1, e = n - 1, m;
        while(b <= e) {
            m = b + (e-b)/2;
            if(isBadVersion(m)) {
                e = m - 1;
            }else {
                b = m + 1;
            }
        }
        return b;
    }

    private static boolean isBadVersion(int version) {
        return version >= badVersion;
    }
}
