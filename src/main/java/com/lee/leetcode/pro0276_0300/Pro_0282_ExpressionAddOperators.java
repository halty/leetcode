package com.lee.leetcode.pro0276_0300;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 Given a string that contains only digits 0-9 and a target value,
 return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

 Example 1:
 Input: num = "123", target = 6
 Output: ["1+2+3", "1*2*3"]

 Example 2:
 Input: num = "232", target = 8
 Output: ["2*3+2", "2+3*2"]

 Example 3:
 Input: num = "105", target = 5
 Output: ["1*0+5","10-5"]

 Example 4:
 Input: num = "00", target = 0
 Output: ["0+0", "0-0", "0*0"]

 Example 5:
 Input: num = "3456237490", target = 9191
 Output: []
 *
 */
public class Pro_0282_ExpressionAddOperators {

    public static void main(String[] args) {
        String num = "123";
        int target = 6;
//        List<String> result = addOperators(num, target);
        List<String> result = addOperators1(num, target);
        System.out.println(result);
    }

    public static List<String> addOperators(String num, int target) {
        int len = num.length();
        if(len == 0) { return Collections.emptyList(); }
        List<String> result = new ArrayList<>();
        int maxLength = len * 2;
        StringBuilder buf = new StringBuilder(maxLength);
        ComputeStack stack = new ComputeStack(maxLength);
        addOperator(result, target, num, 0, buf, stack);
        return result;
    }

    private static void addOperator(List<String> result, int target, String num, int begin,
                                    StringBuilder buf, ComputeStack stack) {
        int end = num.length() - 1;
        char ch = num.charAt(begin);
        if(ch == '0') {
            buf.append(ch);
            stack.push(0);
            if(begin == end) {
                if(stack.computeMatched(target)) {
                    result.add(buf.toString());
                }
            }else {
                for(Operator op : Operator.values()) {
                    buf.append(op.symbol);
                    stack.push(op);
                    addOperator(result, target, num, begin+1, buf, stack);
                    stack.pop();
                    buf.setLength(buf.length()-1);
                }
            }
            stack.pop();
            buf.setLength(buf.length()-1);
        }else {
            int v = 0;
            int oldLength = buf.length();
            for(int i=begin; i<end; i++) {
                ch = num.charAt(i);
                v = v*10 + ch - '0';
                if(v < 0) {
                    buf.setLength(oldLength);
                    return;
                }
                buf.append(ch);
                stack.push(v);
                for(Operator op : Operator.values()) {
                    buf.append(op.symbol);
                    stack.push(op);
                    addOperator(result, target, num, i+1, buf, stack);
                    stack.pop();
                    buf.setLength(buf.length()-1);
                }
                stack.pop();
            }
            ch = num.charAt(end);
            v = v*10 + ch - '0';
            if(v < 0) {
                buf.setLength(oldLength);
                return;
            }
            buf.append(ch);
            stack.push(v);
            if(stack.computeMatched(target)) {
                result.add(buf.toString());
            }
            stack.pop();
            buf.setLength(oldLength);
        }
    }

    private static class ComputeStack {
        Object[] array;
        int size;

        ComputeStack(int capacity) {
            array = new Object[capacity];
            size = 0;
        }
        void push(Object element) {
            array[size++] = element;
        }
        void pop() { size--; }
        boolean computeMatched(int target) {
            if(size == 1) { return target == (int)array[0]; }
            long left = (int)array[0], right = (int)array[2];
            Operator one = (Operator) array[1], two;
            int i = 3;
            while(true) {
                if(i == size) {
                    return target == one.apply(left, right);
                }
                two = (Operator) array[i++];
                while(two.comparePriority(one) > 0) {
                    long n = (int)array[i++];
                    right = two.apply(right, n);
                    if(i == size) {
                        return target == one.apply(left, right);
                    }
                    two = (Operator) array[i++];
                }
                left = one.apply(left, right);
                right = (int) array[i++];
                one = two;
            }
        }
    }

    private enum Operator {
        ADD(1, '+') {
            @Override
            public long apply(long a, long b) {
                return a + b;
            }
        },
        SUBTRACT(1, '-') {
            @Override
            public long apply(long a, long b) {
                return a - b;
            }
        },
        MULTIPLE(2, '*') {
            @Override
            public long apply(long a, long b) {
                return a * b;
            }
        };

        int priority;
        char symbol;

        Operator(int priority, char symbol) {
            this.priority = priority;
            this.symbol = symbol;
        }
        public abstract long apply(long a, long b);
        public int comparePriority(Operator other) {
            return priority - other.priority;
        }
    }

    public static List<String> addOperators1(String num, int target) {
        if(target == Integer.MIN_VALUE) { return Collections.emptyList(); }
        List<String> result = new ArrayList<>();
        char[] source = num.toCharArray();
        char[] path = new char[source.length * 2];
        long n = 0;
        int i = 0;
        while(i < source.length) {
            n = n*10 + source[i]-'0';
            path[i] = source[i];
            i += 1;
            addOperator(result, target, source, i, path, i, 0, n);
            if(n == 0) { break; }
        }
        return result;
    }

    private static void addOperator(List<String> result, int target, char[] source, int sourceIndex,
                                      char[] path, int pathIndex, long sum, long multiple) {
        if(sourceIndex == source.length) {
            if(target == sum + multiple) {
                result.add(new String(path, 0, pathIndex));
            }
            return;
        }
        long n = 0;
        int opIndex = pathIndex++, nextSourceIndex, nextPathIndex;
        for(int i=sourceIndex; i<source.length; i++) {
            n = n*10 + source[i]-'0';
            nextSourceIndex = i + 1;
            nextPathIndex = pathIndex + 1;
            path[pathIndex++] = source[i];
            path[opIndex] = '+';
            addOperator(result, target, source, nextSourceIndex, path, nextPathIndex, sum+multiple, n);
            path[opIndex] = '-';
            addOperator(result, target, source, nextSourceIndex, path, nextPathIndex, sum+multiple, -n);
            path[opIndex] = '*';
            addOperator(result, target, source, nextSourceIndex, path, nextPathIndex, sum, n*multiple);
            if(n == 0) { break; }
        }
    }
}
