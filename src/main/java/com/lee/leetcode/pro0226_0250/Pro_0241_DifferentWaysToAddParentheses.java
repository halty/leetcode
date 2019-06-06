package com.lee.leetcode.pro0226_0250;

import java.util.*;

/**
 *
 Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators.
 The valid operators are +, - and *.

 Example 1:
 Input: "2-1-1"
 Output: [0, 2]
 Explanation:
     ((2-1)-1) = 0
     (2-(1-1)) = 2

 Example 2:
 Input: "2*3-4*5"
 Output: [-34, -14, -10, -10, 10]
 Explanation:
     (2*(3-(4*5))) = -34
     ((2*3)-(4*5)) = -14
     ((2*(3-4))*5) = -10
     (2*((3-4)*5)) = -10
     (((2*3)-4)*5) = 10
 *
 */
public class Pro_0241_DifferentWaysToAddParentheses {

    public static void main(String[] args) {
        String input = "2*3-4*5";
        List<Integer> result = diffWaysToCompute(input);
        System.out.println(result);
    }

    public static List<Integer> diffWaysToCompute(String input) {
        if(input == null || input.isEmpty()) { return Collections.emptyList(); }
        List<Element> elements = parse(input);
        Map<Long, List<Integer>> cache = new HashMap<>();
        return compute(elements, 0, elements.size()-1, cache);
    }

    private static List<Element> parse(String input) {
        List<Element> elements = new ArrayList<>();
        int index = 0, len = input.length();
        char ch;
        while(true) {
            ch = input.charAt(index);
            if(isDigit(ch)) {
                int n = 0;
                do {
                    n = n*10 + ch - '0';
                    index++;
                }while(index<len && isDigit(ch=input.charAt(index)));
                elements.add(new Number(n));
                if(index == len) { break; }
                elements.add(new Operator(ch));
                index++;
            }else {
                elements.add(new Operator(ch));
                index++;
            }
        }
        return elements;
    }

    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static List<Integer> compute(List<Element> elements, int begin, int end, Map<Long, List<Integer>> cache) {
        long key = begin;
        key = (key << 32) | end;
        List<Integer> result = cache.get(key);
        if(result != null) { return result; }
        if(begin == end) {
            result = Arrays.asList(((Number)elements.get(begin)).value);
        }else {
            result = new ArrayList<>();
            for(int i = begin; i <= end; i++) {
                Element e = elements.get(i);
                if(e instanceof Operator) {
                    List<Integer> lefts = compute(elements, begin, i - 1, cache);
                    List<Integer> rights = compute(elements, i + 1, end, cache);
                    result.addAll(((Operator) e).compute(lefts, rights));
                }
            }
        }
        cache.put(key, result);
        return result;
    }

    private static abstract class Element {}

    private static class Number extends Element {
        int value;
        Number(int value) {
            this.value = value;
        }
        public String toString() {
            return String.valueOf(value);
        }
    }

    private static class Operator extends Element {
        char op;
        Operator(char op) {
            this.op = op;
        }
        List<Integer> compute(List<Integer> lefts, List<Integer> rights) {
            List<Integer> result = new ArrayList<>(lefts.size() * rights.size());
            switch(op) {
                case '+':
                    for(Integer a : lefts) {
                        for(Integer b : rights) {
                            result.add(a + b);
                        }
                    }
                    break;
                case '-':
                    for(Integer a : lefts) {
                        for(Integer b : rights) {
                            result.add(a - b);
                        }
                    }
                    break;
                default:
                    for(Integer a : lefts) {
                        for(Integer b : rights) {
                            result.add(a * b);
                        }
                    }
            }
            return result;
        }
        public String toString() {
            return String.valueOf(op);
        }
    }
}
