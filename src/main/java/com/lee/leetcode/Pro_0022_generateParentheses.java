package com.lee.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Pro_0022_generateParentheses {

	public static void main(String[] args) {
		List<String> list = generateParenthesis(4);
		System.out.println(list.size());
		for(String s : list) {
			System.out.println(s);
		}
	}

	public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>((int)Math.pow(3, n-1));
		char[] buf = new char[2*n];
        int left = 1, right = 0, index = 0;
        buf[index++] = '(';
        combine(left, right, buf, index, n, result);
        return result;
    }
	
	private static void combine(int left, int right, char[] buf, int index, int n, List<String> result) {
		if(left == n) {
			for(int i=index; i<buf.length; i++) { buf[i] = ')'; }
			result.add(new String(buf));
			return;
		}
		if(right < left) {
			buf[index]=')';
			combine(left, right+1, buf, index+1, n, result);
		}
		buf[index] = '(';
		combine(left+1, right, buf, index+1, n, result);
	}
}
