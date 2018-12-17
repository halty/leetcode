package com.lee.leetcode.pro0126_0150;

/**
 *
 Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 Note:
   1. Division between two integers should truncate toward zero.
   2. The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.

 Example 1:
 Input: ["2", "1", "+", "3", "*"]
 Output: 9
 Explanation: ((2 + 1) * 3) = 9

 Example 2:
 Input: ["4", "13", "5", "/", "+"]
 Output: 6
 Explanation: (4 + (13 / 5)) = 6

 Example 3:
 Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 Output: 22
 Explanation:
   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 = ((10 * (6 / (12 * -11))) + 17) + 5
 = ((10 * (6 / -132)) + 17) + 5
 = ((10 * 0) + 17) + 5
 = (0 + 17) + 5
 = 17 + 5
 = 22
 *
 */
public class Pro_0150_EvaluateReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
//        int result = evalRPN(tokens);
        int result = evalRPN1(tokens);
        System.out.println(result);
    }

    public static int evalRPN(String[] tokens) {
        int len = tokens.length;
        int k = 1;
        for(int i=2; i<len; i++) {
            String token = tokens[i];
            switch(token) {
                case "+":
                    int s = Integer.parseInt(tokens[k-1]) + Integer.parseInt(tokens[k]);
                    tokens[--k] = Integer.toString(s);
                    break;
                case "-":
                    int d = Integer.parseInt(tokens[k-1]) - Integer.parseInt(tokens[k]);
                    tokens[--k] = Integer.toString(d);
                    break;
                case "*":
                    int p = Integer.parseInt(tokens[k-1]) * Integer.parseInt(tokens[k]);
                    tokens[--k] = Integer.toString(p);
                    break;
                case "/":
                    int q = Integer.parseInt(tokens[k-1]) / Integer.parseInt(tokens[k]);
                    tokens[--k] = Integer.toString(q);
                    break;
                default:
                    tokens[++k] = token;
            }
        }
        return Integer.parseInt(tokens[0]);
    }

    public static int evalRPN1(String[] tokens) {
        int len = tokens.length;
        if(len <= 2) { return Integer.parseInt(tokens[0]); }
        int[] stack = new int[(len+1)/2];
        int k  = 0;
        stack[k++] = Integer.parseInt(tokens[0]);
        stack[k] = Integer.parseInt(tokens[1]);
        for(int i=2; i<len; i++) {
            String token = tokens[i];
            if(token.length() == 1) {
                char ch = token.charAt(0);
                switch(ch) {
                    case '+':
                        stack[k-1] += stack[k];
                        k--;
                        break;
                    case '-':
                        stack[k-1] -= stack[k];
                        k--;
                        break;
                    case '*':
                        stack[k-1] *= stack[k];
                        k--;
                        break;
                    case '/':
                        stack[k-1] /= stack[k];
                        k--;
                        break;
                    default:
                        stack[++k] = Integer.parseInt(token);
                }
            }else {
                stack[++k] = Integer.parseInt(token);
            }
        }
        return stack[0];
    }
}
