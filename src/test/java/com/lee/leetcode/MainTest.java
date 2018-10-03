package com.lee.leetcode;

import java.util.regex.Pattern;

public class MainTest {

	public static void main(String[] args) {
		long r = 1;
		for(int i=10; i<=18; i++) {
			r *= i;
		}
		System.out.println(r);
		// System.out.println(r*1276);
	}

	public static boolean isMatch(String s, String p) {
        return Pattern.matches(p, s);
    }
}
