package com.lee.leetcode.pro0301_0325;

import java.util.*;

/**
 *
 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Example 1:
 Input: "()())()"
 Output: ["()()()", "(())()"]

 Example 2:
 Input: "(a)())()"
 Output: ["(a)()()", "(a())()"]

 Example 3:
 Input: ")("
 Output: [""]
 *
 */
public class Pro_0301_RemoveInvalidParentheses {

    public static void main(String[] args) {
        String s = "(((k()((";
        List<String> result = removeInvalidParentheses(s);
        System.out.println(result);
    }

    public static List<String> removeInvalidParentheses(String s) {
        int len = s.length();
        if(len == 0) { return Arrays.asList(""); }
        if(len == 1) {
            char ch = s.charAt(0);
            if(ch == '(' || ch == ')') {
                return Arrays.asList("");
            }else {
                return Arrays.asList(s);
            }
        }
        Context ctx = trim(s);
        if(ctx.chars.length == 0) {
            return Arrays.asList(ctx.prefix+ctx.suffix);
        }
        Set<String> set = remove(ctx.chars);
        return compose(ctx.prefix, set, ctx.suffix);
    }

    private static Context trim(String s) {
        char[] chars = s.toCharArray();
        int b = 0, i = 0;
        for(; i<chars.length; i++) {
            char ch = chars[i];
            if(ch == ')') {
                continue;
            }else if(ch == '(') {
                break;
            }else {
                chars[b++] = ch;
            }
        }
        if(i == chars.length) {
            return new Context(new String(chars, 0, b), new char[0], "");
        }
        int e = chars.length-1, j = e;
        for(; j>=i; j--) {
            char ch = chars[j];
            if(ch == '(') {
                continue;
            }else if(ch == ')') {
                break;
            }else {
                chars[e--] = ch;
            }
        }
        e += 1;
        return new Context(new String(chars, 0, b),
                           Arrays.copyOfRange(chars, i, j+1),
                           new String(chars, e, chars.length-e));
    }

    private static Set<String> remove(char[] chars) {
        long removeCount = check(chars);
        if(removeCount == 0) {
            Set<String> set = new HashSet<>(1);
            set.add(new String(chars));
            return set;
        }
        Set<String> set = new HashSet<>();
        int removeLeft = (int)(removeCount >>> 32);
        int removeRight = (int)removeCount;
        char[][] stack = new char[removeLeft+removeRight+1][];
        stack[0] = chars;
        remove(set, stack, 0, removeLeft, 0, removeRight, 0);
        return set;
    }

    private static long check(char[] chars) {
        int removeLeft = 0, removeRight = 0;
        for(char ch : chars) {
            if(ch == '(') {
                removeLeft++;
            }else if(ch == ')') {
                if(removeLeft == 0) {
                    removeRight++;
                }else {
                    removeLeft--;
                }
            }
        }
        return (((long)removeLeft) << 32) + removeRight;
    }

    private static void remove(Set<String> result, char[][] stack, int index,
                               int removeLeft, int nextLeftIdx, int removeRight, int nextRightIdx) {
        char[] target = stack[index];
        if(removeLeft > 0) {
            index++;
            char[] current = stack[index];
            if(current == null) { stack[index] = current = new char[target.length-1]; }
            int lastLeftIdx = lastIndexOf(target, removeLeft, '(');
            for(int i=nextLeftIdx; i<=lastLeftIdx; i++) {
                if(target[i] == '(' && (i == nextLeftIdx || target[i-1] != '(')) {
                    System.arraycopy(target, 0, current, 0, i);
                    System.arraycopy(target, i+1, current, i, current.length-i);
                    remove(result, stack, index, removeLeft-1, i, removeRight, nextRightIdx);
                }
            }
            return;
        }
        if(removeRight > 0) {
            index++;
            char[] current = stack[index];
            if(current == null) { stack[index] = current = new char[target.length-1]; }
            int lastRightIdx = lastIndexOf(target, removeRight, ')');
            for(int i=nextRightIdx; i<=lastRightIdx; i++) {
                if(target[i] == ')' && (i == nextRightIdx || target[i-1] != ')')) {
                    System.arraycopy(target, 0, current, 0, i);
                    System.arraycopy(target, i+1, current, i, current.length-i);
                    remove(result, stack, index, removeLeft, nextLeftIdx, removeRight-1, i);
                }
            }
            return;
        }
        if(check(target) == 0) {
            result.add(new String(target));
        }
    }

    private static int lastIndexOf(char[] chars, int count, char ch) {
        int i = chars.length - 1;
        for(; i>=0; i--) {
            if(chars[i] == ch && --count == 0) {
                break;
            }
        }
        return i;
    }

    private static List<String> compose(String prefix, Set<String> set, String suffix) {
        if(prefix.isEmpty()) {
            if(suffix.isEmpty()) {
                return new ArrayList<>(set);
            }else {
                List<String> result = new ArrayList<>(set.size());
                for(String s : set) {
                    result.add(s + suffix);
                }
                return result;
            }
        }else {
            if(suffix.isEmpty()) {
                List<String> result = new ArrayList<>(set.size());
                for(String s : set) {
                    result.add(prefix + s);
                }
                return result;
            }else {
                List<String> result = new ArrayList<>(set.size());
                for(String s : set) {
                    result.add(prefix + s + suffix);
                }
                return result;
            }
        }
    }

    private static class Context {
        String prefix;
        char[] chars;
        String suffix;
        Context(String prefix, char[] chars, String suffix) {
            this.prefix = prefix;
            this.chars = chars;
            this.suffix = suffix;
        }
    }
}
