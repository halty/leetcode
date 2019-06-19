package com.lee.leetcode.pro0276_0300;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 So the median is the mean of the two middle value.

 For example,
    [2,3,4], the median is 3
    [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:
    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.

 Example:
    addNum(1)
    addNum(2)
    findMedian() -> 1.5
    addNum(3)
    findMedian() -> 2

 Follow up:
    If all integer numbers from the stream are between 0 and 100, how would you optimize it?
    If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 *
 */
public class Pro_0295_FindMedianFromDataStream {

    public static void main(String[] args) {
        Pro_0295_FindMedianFromDataStream finder = new Pro_0295_FindMedianFromDataStream();
        finder.addNum(0);
        finder.addNum(0);
        System.out.println(finder.findMedian());
        /*finder.addNum(6);
        System.out.println(finder.findMedian());
        finder.addNum(10);
        System.out.println(finder.findMedian());
        finder.addNum(2);
        System.out.println(finder.findMedian());
        finder.addNum(6);
        System.out.println(finder.findMedian());
        finder.addNum(5);
        System.out.println(finder.findMedian());
        finder.addNum(0);
        System.out.println(finder.findMedian());
        finder.addNum(6);
        System.out.println(finder.findMedian());
        finder.addNum(3);
        System.out.println(finder.findMedian());
        finder.addNum(1);
        System.out.println(finder.findMedian());
        finder.addNum(0);
        System.out.println(finder.findMedian());
        finder.addNum(0);
        System.out.println(finder.findMedian());*/
    }

    /*private int[] array;
    private int size;

    public Pro_0295_FindMedianFromDataStream() {
        array = new int[10];
        size = 0;
    }

    public void addNum(int num) {
        ensureCapacity();
        int b = 0, e = size - 1;
        while(b <= e) {
            int m = b + (e-b)/2;
            if(array[m] < num) {
                b = m + 1;
            }else if(array[m] > num) {
                e = m - 1;
            }else {
                b = m;
                break;
            }
        }
        if(b < size) {
            System.arraycopy(array, b, array, b+1, size-b);
        }
        array[b] = num;
        size += 1;
    }

    private void ensureCapacity() {
        if(size == array.length) {
            int newLength = array.length + array.length / 2;
            array = Arrays.copyOf(array, newLength);
        }
    }

    public double findMedian() {
        int index = size >> 1;
        if((size & 0x01) == 1) {   // odd
            return array[index];
        }else { // even
            double d = array[index] + array[index-1];
            return d / 2;
        }
    }*/

    /*private int[] elements;
    private int[] histogram;
    private int size;
    private int count;

    public Pro_0295_FindMedianFromDataStream() {
        elements = new int[10];
        histogram = new int[10];
        size = 0;
        count = 0;
    }

    public void addNum(int num) {
        if(size == elements.length) {
            int newLen = size + (size >> 1);
            elements = Arrays.copyOf(elements, newLen);
            histogram = Arrays.copyOf(histogram, newLen);
        }
        int b = 0, e = size - 1;
        while(b <= e) {
            int m = b + (e-b)/2;
            int v = elements[m];
            if(v < num) {
                b = m + 1;
            }else if(v > num) {
                e = m - 1;
            }else {
                histogram[m] += 1;
                count += 1;
                return;
            }
        }
        if(b < size) {
            System.arraycopy(elements, b, elements, b+1, size-b);
            System.arraycopy(histogram, b, histogram, b+1, size-b);
        }
        elements[b] = num;
        histogram[b] = 1;
        size += 1;
        count += 1;
    }

    public double findMedian() {
        if((count & 0x01) == 1) {   // odd
            int index = (count >> 1) + 1;
            int cnt = 0;
            for(int i=0; i<size; i++) {
                cnt += histogram[i];
                if(cnt >= index) {
                    return elements[i];
                }
            }
        }else { // even
            int index = count >> 1;
            int cnt = 0;
            for(int i=0; i<size; i++) {
                cnt += histogram[i];
                if(cnt > index) {
                    return elements[i];
                }else if(cnt == index) {
                    double d = elements[i] + elements[i+1];
                    return d / 2;
                }
            }
        }
        return 0;
    }*/

    private PriorityQueue<Integer> first;   // desc
    private PriorityQueue<Integer> second;  // asc

    public Pro_0295_FindMedianFromDataStream() {
        first = new PriorityQueue<>(Comparator.reverseOrder());
        second = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(first.size() == 0 || num < first.peek()) {
            first.offer(num);
        }else {
            second.offer(num);
        }
        // balance
        if(Math.abs(first.size()-second.size()) > 1) {
            if(first.size() > second.size()) {
                second.offer(first.poll());
            }else {
                first.offer(second.poll());
            }
        }
    }

    public double findMedian() {
        if(first.size() == second.size()) {
            return (first.peek()+second.peek())*0.5;
        }else if(first.size() > second.size()) {
            return first.peek();
        }else {
            return second.peek();
        }
    }
}
