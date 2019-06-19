package com.lee.leetcode.pro0276_0300;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 Given an Iterator class interface with methods: next() and hasNext(),
 design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

 Example:
     Assume that the iterator is initialized to the beginning of the list: [1,2,3].

     Call next() gets you 1, the first element in the list.
     Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
     You call next() the final time and it returns 3, the last element.
     Calling hasNext() after that should return false.

 Follow up: How would you extend your design to be generic and work with all types, not just integer?
 *
 */
public class Pro_0284_PeekingIterator implements Iterator<Integer> {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3);
        Pro_0284_PeekingIterator iterator = new Pro_0284_PeekingIterator(list.iterator());
        while(iterator.hasNext()) {
            System.out.println(iterator.peek());
            System.out.println(iterator.next());
        }
    }

    private static final Object NOP = new Object();

    private Iterator<Integer> iterator;
    private Object peeking;

    public Pro_0284_PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        this.peeking = NOP;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(peeking == NOP) {
            peeking = iterator.next();
        }
        return (Integer) peeking;
    }

    // hasNext() and next() should behave the same as in  the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if(peeking == NOP) {
            peeking = iterator.next();
        }
        Integer next = (Integer) peeking;
        peeking = NOP;
        return next;
    }

    @Override
    public boolean hasNext() {
        return peeking != NOP || iterator.hasNext();
    }
}
