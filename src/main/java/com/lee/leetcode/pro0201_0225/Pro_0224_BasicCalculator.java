package com.lee.leetcode.pro0201_0225;

/**
 *
 Implement a basic calculator to evaluate a simple expression string.
 The expression string may contain open ( and closing parentheses ),
 the plus + or minus sign -, non-negative integers and empty spaces .

 Example 1:
 Input: "1 + 1"
 Output: 2

 Example 2:
 Input: " 2-1 + 2 "
 Output: 3

 Example 3:
 Input: "(1+(4+5+2)-3)+(6+8)"
 Output: 23

 Note:
    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
 *
 */
public class Pro_0224_BasicCalculator {

    public static void main(String[] args) {
        String s = "2-(1 + 2)";
        int result = calculate(s);
        System.out.println(result);
    }

    public static int calculate(String s) {
        int len = s.length(), begin = 0;
        Operator op = getOperator(s, begin);
        Number left, right;
        if(op.isLeftParentheses()) {
            left = getParenthesesResult(s, op.nextIndex);
        }else {
            left = getInteger(s, op.nextIndex-1);
        }
        begin = discardSpace(s, left.nextIndex);
        while(begin < len) {
            op = getOperator(s, begin);
            Operator next = getOperator(s, op.nextIndex);
            if(next.isLeftParentheses()) {
                right = getParenthesesResult(s, next.nextIndex);
            }else {
                right = getInteger(s, next.nextIndex-1);
            }
            left.num = op.apply(left.num, right.num);
            begin = discardSpace(s, right.nextIndex);
        }
        return (int)left.num;
    }

    private static int discardSpace(String s, int begin) {
        int len = s.length();
        for(; begin<len && s.charAt(begin)==' '; begin++);
        return begin;
    }

    private static Number getParenthesesResult(String s, int begin) {
        Operator op = getOperator(s, begin);
        Number left, right;
        if(op.isLeftParentheses()) {
            left = getParenthesesResult(s, op.nextIndex);
        }else {
            left = getInteger(s, op.nextIndex-1);
        }
        op = getOperator(s, left.nextIndex);
        while(!op.isRightParentheses()) {
            Operator next = getOperator(s, op.nextIndex);
            if(next.isLeftParentheses()) {
                right = getParenthesesResult(s, next.nextIndex);
            }else {
                right = getInteger(s, next.nextIndex-1);
            }
            left.num = op.apply(left.num, right.num);
            op = getOperator(s, right.nextIndex);
        }
        left.nextIndex = op.nextIndex;
        return left;
    }

    private static Number getInteger(String s, int begin) {
        begin = discardSpace(s, begin);
        char ch = s.charAt(begin);
        long num = ch - '0';
        int end = begin + 1, len = s.length();
        while(end <len && isDigit(ch = s.charAt(end))) {
            num = num * 10 + (ch - '0');
            end++;
        }
        return new Number(num, end);
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

    private static Operator getOperator(String s, int begin) {
        begin = discardSpace(s, begin);
        char ch = s.charAt(begin++);
        return new Operator(ch, begin);
    }

    private static class Operator {
        char op;    // '+', '-', '(', ')'
        int nextIndex;
        Operator(char op, int nextIndex) {
            this.op = op;
            this.nextIndex = nextIndex;
        }
        boolean isLeftParentheses() {
            return op == '(';
        }
        boolean isRightParentheses() {
            return op == ')';
        }
        long apply(long left, long right) {
            return op == '+' ? left + right : left - right;
        }
    }
}
