package com.lee.leetcode.pro0301_0325;

import java.util.PriorityQueue;

/**
 *
 Write a program to find the nth super ugly number.
 Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.

 Example:
 Input: n = 12, primes = [2,7,13,19]
 Output: 32
 Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
 super ugly numbers given primes = [2,7,13,19] of size 4.

 Note:
    1 is a super ugly number for any given primes.
    The given numbers in primes are in ascending order.
    0 < k ≤ 100, 0 < n ≤ 10^6, 0 < primes[i] < 1000.
    The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 *
 */
public class Pro_0313_SuperUglyNumber {

    public static void main(String[] args) {
        int n = 12;
        int[] primes = {2,7,13,19};
//        int num = nthSuperUglyNumber(n, primes);
        int num = nthSuperUglyNumber1(n, primes);
        System.out.println(num);
    }

    public static int nthSuperUglyNumber(int n, int[] primes) {
        if(n == 1) { return 1; }
        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = primes[0];
        PriorityQueue<Element> queue = new PriorityQueue<>(primes.length);
        for(int prime : primes) {
            queue.offer(new Element(prime, prime));
        }
        int index = 2;
        while(index < n) {
            int prevIndex = index - 1;
            int prev = nums[index-1];
            Element e = queue.poll();
            if(e.num > prev) {
                nums[index++] = e.num;
            }
            int factor = prev / e.prime;
            factor = findNext(nums, prevIndex, factor);
            e.num = factor * e.prime;
            queue.offer(e);
        }
        return nums[n-1];
    }

    private static int findNext(int[] nums, int end, int target) {
        int b = 0, e = end;
        while(b <= e) {
            int m = b + (e-b)/2;
            int mVal = nums[m];
            if(mVal < target) {
                b = m + 1;
            }else if(mVal > target) {
                e = m - 1;
            }else {
                return nums[m + 1];
            }
        }
        return nums[b];
    }

    private static class Element implements Comparable<Element> {
        private int prime;
        private int num;

        Element(int prime, int num) {
            this.prime = prime;
            this.num = num;
        }

        @Override
        public int compareTo(Element o) {
            return num - o.num;
        }
    }

    public static int nthSuperUglyNumber1(int n, int[] primes) {
        if(n == 1) { return 1; }
        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = primes[0];
        PriorityQueue<UglyNumber> queue = new PriorityQueue<>(primes.length);
        int[] factorIndexes = new int[primes.length];
        for(int i=0; i<primes.length; i++) {
            factorIndexes[i] = 0;
            queue.offer(new UglyNumber(i, primes[i]));
        }
        int index = 2;
        while(index < n) {
            int prev = nums[index-1];
            UglyNumber e = queue.poll();
            if(e.num > prev) {
                nums[index++] = e.num;
            }
            factorIndexes[e.primeIndex] += 1;
            e.num = nums[factorIndexes[e.primeIndex]] * primes[e.primeIndex];
            queue.offer(e);
        }
        return nums[n-1];
    }

    private static class UglyNumber implements Comparable<UglyNumber> {
        private int primeIndex;
        private int num;

        UglyNumber(int primeIndex, int num) {
            this.primeIndex = primeIndex;
            this.num = num;
        }

        @Override
        public int compareTo(UglyNumber o) {
            return num - o.num;
        }
    }
}
