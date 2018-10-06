package com.lee.leetcode;

import java.util.regex.Pattern;

public class MainTest {

	public static void main(String[] args) {
		long r = Long.MAX_VALUE - 1000;
		int x = Integer.MAX_VALUE;
		boolean b = r > x;
		 System.out.println(x);
	}

	public static boolean isMatch(String s, String p) {
        return Pattern.matches(p, s);
    }
}
