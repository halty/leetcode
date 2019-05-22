package com.lee.leetcode.pro0226_0250;

import java.util.Stack;

/**
 *
 Implement the following operations of a queue using stacks.
    push(x) -- Push element x to the back of queue.
    pop() -- Removes the element from in front of queue.
    peek() -- Get the front element.
    empty() -- Return whether the queue is empty.

 Example:
    MyQueue queue = new MyQueue();
    queue.push(1);
    queue.push(2);
    queue.peek();  // returns 1
    queue.pop();   // returns 1
    queue.empty(); // returns false

 Notes:
    You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
    Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue),
 as long as you use only standard operations of a stack.
    You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 *
 */
public class Pro_0232_ImplementQueueUsingStacks {

    public static void main(String[] args) {
        Pro_0232_ImplementQueueUsingStacks queue = new Pro_0232_ImplementQueueUsingStacks();
        queue.push(1);
        queue.push(2);
        int result = queue.peek();
        System.out.println(result);
        result = queue.pop();
        System.out.println(result);
        boolean isEmpty = queue.empty();
        System.out.println(isEmpty);
    }

    private Stack<Integer> current;
    private Stack<Integer> backup;

    /** Initialize your data structure here. */
    public Pro_0232_ImplementQueueUsingStacks() {
        current = new Stack<>();
        backup = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        current.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        transfer();
        return backup.pop();
    }

    private void transfer() {
        if(backup.isEmpty()) {
            while(!current.isEmpty()) {
                backup.push(current.pop());
            }
        }
    }

    /** Get the front element. */
    public int peek() {
        transfer();
        return backup.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return current.isEmpty() && backup.isEmpty();
    }
}
