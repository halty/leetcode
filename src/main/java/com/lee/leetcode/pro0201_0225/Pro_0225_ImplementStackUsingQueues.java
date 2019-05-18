package com.lee.leetcode.pro0201_0225;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 Implement the following operations of a stack using queues.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 empty() -- Return whether the stack is empty.

 Example:
 MyStack stack = new MyStack();
 stack.push(1);
 stack.push(2);
 stack.top();   // returns 2
 stack.pop();   // returns 2
 stack.empty(); // returns false

 Notes:
    You must use only standard operations of a queue -- which means only push to back,
 peek/pop from front, size, and is empty operations are valid.
    Depending on your language, queue may not be supported natively.
 You may simulate a queue by using a list or deque (double-ended queue),
 as long as you use only standard operations of a queue.
    You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 *
 */
public class Pro_0225_ImplementStackUsingQueues {

    public static void main(String[] args) {
        Pro_0225_ImplementStackUsingQueues stack = new Pro_0225_ImplementStackUsingQueues();
        stack.push(1);
        stack.push(2);
        int result = stack.top();
        System.out.println(result);
        result = stack.pop();
        System.out.println(result);
        result = stack.pop();
        System.out.println(result);
        boolean isEmpty = stack.empty();
        System.out.println(isEmpty);
    }

    private Queue<Integer> current;
    private Queue<Integer> backup;

    /** Initialize your data structure here. */
    public Pro_0225_ImplementStackUsingQueues() {
        current = new ArrayDeque<>();
        backup = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        current.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        retainTopElement();
        return current.poll();
    }

    private void retainTopElement() {
        if(current.isEmpty()) {
            Queue<Integer> q = current;
            current = backup;
            backup = q;
        }
        while(current.size() > 1) {
            backup.offer(current.poll());
        }
    }

    /** Get the top element. */
    public int top() {
        retainTopElement();
        return current.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return current.isEmpty() && backup.isEmpty();
    }
}
