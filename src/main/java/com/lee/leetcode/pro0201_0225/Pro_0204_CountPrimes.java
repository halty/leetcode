package com.lee.leetcode.pro0201_0225;

/**
 *
 Count the number of prime numbers less than a non-negative number, n.

 Example:
 Input: 10
 Output: 4
 Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 *
 */
public class Pro_0204_CountPrimes {

    public static void main(String[] args) {
        int n = 100;
//        int count = countPrimes(n);
        int count = countPrimes1(n);
        System.out.println(count);
    }

    public static int countPrimes(int n) {
        int count = enumPrime(n);
        if(n > 32) {
            for(int i = 33; i < n; i++) {
                if(isPrime(i)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int enumPrime(int n) {
        switch(n) {
            case 0: case 1: case 2: return 0;
            case 3: return 1;
            case 4: case 5: return 2;
            case 6: case 7: return 3;
            case 8: case 9: case 10: case 11: return 4;
            case 12: case 13: return 5;
            case 14: case 15: case 16: case 17: return 6;
            case 18: case 19: return 7;
            case 20: case 21: case 22: case 23: return 8;
            case 24: case 25: case 26: case 27: case 28: case 29: return 9;
            case 30: case 31: return 10;
            default: return 11;
        }
    }

    private static boolean isPrime(int n) {
        int max = (int) Math.sqrt(n);
        for(int i=2; i<=max; i++) {
            if(i * (n/i) == n) {
                return false;
            }
        }
        return true;
    }

    public static int countPrimes1(int n) {
        if(n <= 2) { return 0; }
        boolean[] isNotPrime = new boolean[n];
        for(int i=2; i*i < n; i++) {
            if(isNotPrime[i]) { continue; }
            for(int j=i*i; j<n; j+=i) {
                isNotPrime[j] = true;
            }
        }
        int count = 0;
        for(int i=2; i<n; i++) {
            if(!isNotPrime[i]) { count++; }
        }
        return count;
    }
}
