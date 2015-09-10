package com.lee.leetcode.pro0001_0025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

	1        2(abc)   3(def)
	4(ghi)   5(jkl)   6(mno)
	7(pqrs)  8(tuv)   9(wxyz)
	*(+)     0( )     #

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 */
public class Pro_0017_letterCombinationsOfAPhoneNumber {

	public static void main(String[] args) {
		String digits = "23";
		System.out.println(letterCombinations1(digits));
	}
	
	public static List<String> letterCombinations1(String digits) {
		int len = digits.length();
		int nums = 1;
		char[][] table = new char[len][];
		for(int i=0; i<len; i++) {
			table[i] = TABLE[digits.charAt(i)-'0'];
			nums *= table[i].length;
		}
		char[] buf = new char[len];
		List<String> list = new ArrayList<String>(nums);
		recursive(table, buf, 0, list);
		return list;
	}
	
	private static void recursive(char[][] table, char[] buf, int index, List<String> list) {
		if(index == buf.length) { list.add(new String(buf)); return; }
		for(char ch : table[index]) {
			buf[index] = ch;
			recursive(table, buf, index+1, list);
		}
	}

	public static List<String> letterCombinations(String digits) {
		int len = digits.length();
		if(len == 0) { return Collections.emptyList(); }
        
		char[] buf = new char[digits.length()];
        int nums = 1;
		for(int i=0; i<len; i++) {
        	char ch = digits.charAt(i);
			if(ch == '0' || ch == '1') { continue; }
        	if(ch == '7' || ch == '9') { nums *= 4; }
        	nums *= 3;
        }
		List<String> resultList = new ArrayList<String>(nums);
        append(digits, 0, buf, 0, resultList);
        return resultList;
    }
	
	private static void append(String digits, int index, char[] buf, int end, List<String> resultList) {
		if(index == digits.length() - 1) {
			int d = digits.charAt(index) - '0';
			int len = end + 1;
			for(char ch : TABLE[d]) {
				buf[end] = ch;
				resultList.add(new String(buf, 0, len));
			}
			return;
		}
		int d = digits.charAt(index) - '0';
		for(char ch : TABLE[d]) {
			buf[end] = ch;
			append(digits, index+1, buf, end+1, resultList);
		}
	}
	
	private static final char[][]  TABLE = new char[][] {
		{' '}, {},
		{'a', 'b', 'c'},
		{'d', 'e', 'f'},
		{'g', 'h', 'i'},
		{'j', 'k', 'l'},
		{'m', 'n', 'o'},
		{'p', 'q', 'r', 's'},
		{'t', 'u', 'v'},
		{'w', 'x', 'y', 'z'}
	};
}
