package com.lee.leetcode;

import java.util.regex.Pattern;

public class MainTest {

	public static void main(String[] args) {
		String s = "aaa";
		String p = "ab*ac*a";
		System.err.println(isMatch(s, p));
	}

	public static boolean isMatch(String s, String p) {
        return Pattern.matches(p, s);
    }
}
