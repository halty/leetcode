package com.lee.leetcode.pro0226_0250;

/**
 *
 Implement a basic calculator to evaluate a simple expression string.
 The expression string contains only non-negative integers, +, -, *, / operators and empty spaces.
 The integer division should truncate toward zero.

 Example 1:
 Input: "3+2*2"
 Output: 7

 Example 2:
 Input: " 3/2 "
 Output: 1

 Example 3:
 Input: " 3+5 / 2 "
 Output: 5

 Note:
    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
 *
 */
public class Pro_0227_BasicCalculatorII {

    public static void main(String[] args) {
        String s = "3 ";
        int result = calculate(s);
        System.out.println(result);
    }

    public static int calculate(String s) {
        int begin = 0, len = s.length();
        Number left, right;
        Operator one, two;
        left = emitInteger(s, begin);
        begin = discardSpaces(s, left.nextIndex);
        if(begin == len) { return (int)left.num; }
        one = emitOperator(s, begin);
        while(true) {
            right = emitInteger(s, one.nextIndex);
            begin = discardSpaces(s, right.nextIndex);
            if(begin == len) {
                left.num = one.apply(left.num, right.num);
                return (int)left.num;
            }
            two = emitOperator(s, begin);
            while(two.highThan(one)) {
                Number n = emitInteger(s, two.nextIndex);
                right.num = two.apply(right.num, n.num);
                begin = discardSpaces(s, n.nextIndex);
                if(begin == len) {
                    left.num = one.apply(left.num, right.num);
                    return (int)left.num;
                }
                two = emitOperator(s, begin);
            }
            left.num = one.apply(left.num, right.num);
            one = two;
        }
    }

    private static Number emitInteger(String s, int begin) {
        begin = discardSpaces(s, begin);
        int len = s.length();
        long num = 0;
        char ch;
        for(; begin<len && isDigit(ch=s.charAt(begin)); begin++) {
            num = 10*num + ch - '0';
        }
        return new Number(num, begin);
    }

    private static int discardSpaces(String s, int begin) {
        int len = s.length();
        for(; begin<len && s.charAt(begin)==' '; begin++);
        return begin;
    }

    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static class Number {
        long num;
        int nextIndex;
        Number(long num, int nextIndex) {
            this.num = num;
            this.nextIndex = nextIndex;
        }
    }

    private static Operator emitOperator(String s, int begin) {
        begin = discardSpaces(s, begin);
        char ch = s.charAt(begin++);
        return new Operator(ch, begin);
    }

    private static class Operator {
        char op;    // '+', '-', '*', '/'
        int nextIndex;
        Operator(char op, int nextIndex) {
            this.op = op;
            this.nextIndex = nextIndex;
        }
        boolean highThan(Operator other) {
            return priority() > other.priority();
        }
        int priority() {
            if(op == '+' || op == '-') {
                return 0;
            }else {
                return 1;
            }
        }
        long apply(long left, long right) {
            switch(op) {
                case '+': return left + right;
                case '-': return left - right;
                case '*': return left * right;
                case '/': return left / right;
                default: return 0;
            }
        }
    }
}
