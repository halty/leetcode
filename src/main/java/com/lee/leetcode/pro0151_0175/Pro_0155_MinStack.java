package com.lee.leetcode.pro0151_0175;

import java.util.Arrays;

/**
 *
 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.

 Example:
     MinStack minStack = new MinStack();
     minStack.push(-2);
     minStack.push(0);
     minStack.push(-3);
     minStack.getMin();   --> Returns -3.
     minStack.pop();
     minStack.top();      --> Returns 0.
     minStack.getMin();   --> Returns -2.
 *
 */
public class Pro_0155_MinStack {

    public static void main(String[] args) {
        Pro_0155_MinStack minStack = new Pro_0155_MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.push(2147483647);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.push(-2147483648);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }

    /*public Pro_0155_MinStack() {
        stack = new int[10];
        array = new int[10];
        count = 0;
    }

    public void push(int x) {
        ensureCapacity();
        int index = find(x);
        if(index < count) {
            System.arraycopy(array, index, array,index+1, count-index);
        }
        array[index] = x;
        stack[count++] = x;
    }

    private void ensureCapacity() {
        if(count == array.length) {
            int newSize = count + (count >> 1);
            stack = Arrays.copyOf(stack, newSize);
            array = Arrays.copyOf(array, newSize);
        }
    }

    private int find(int x) {
        int b = 0, e = count - 1;
        while(b <= e) {
            int mid = b + ((e-b) >> 1);
            int vm = array[mid];
            if(x > vm) {
                e = mid - 1;
            }else if(x < vm) {
                b = mid + 1;
            }else {
                return mid;
            }
        }
        return b;
    }

    public void pop() {
        if(count == 0) { return; }
        int top = count - 1;
        int x = stack[top];
        int index = find(x);
        if(index < top) {
            System.arraycopy(array, index+1, array, index, count-index-1);
        }
        count--;
    }

    public int top() {
        return count == 0 ? -1 : stack[count-1];
    }

    public int getMin() {
        return count == 0 ? -1 : array[count-1];
    }

    private int[] stack;
    private int[] array;
    private int count;*/

    /*public Pro_0155_MinStack() {
        stack = new int[10];
        top = -1;
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if(++top == stack.length) {
            stack = Arrays.copyOf(stack, top+(top >> 1));
        }
        stack[top] = x;
        if(x < min) {
            min = x;
        }
    }

    public void pop() {
        int x = stack[top--];
        if(x == min) {
            updateMin();
        }
    }

    private void updateMin() {
        int x = Integer.MAX_VALUE;
        for(int i=top; i>=0; i--) {
            int v = stack[i];
            if(v < x) {
                x = v;
            }
        }
        min = x;
    }

    public int top() {
        return stack[top];
    }

    public int getMin() {
        return min;
    }

    private int[] stack;
    private int top;
    private int min;*/

    public Pro_0155_MinStack() {
        stack = new int[10];
        top = -1;
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if(++top == stack.length) {
            stack = Arrays.copyOf(stack, top+(top >> 1));
        }
        if(x <= min) {   // push the local min value
            stack[top++] = min;
            min = x;
        }
        stack[top] = x;
    }

    public void pop() {
        int x = stack[top--];
        if(x == min) {
            min = stack[top--];
        }
    }

    public int top() {
        return stack[top];
    }

    public int getMin() {
        return min;
    }

    private int[] stack;
    private int top;
    private int min;
}
