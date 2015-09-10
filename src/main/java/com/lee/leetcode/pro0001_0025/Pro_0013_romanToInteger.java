package com.lee.leetcode.pro0001_0025;

import java.util.HashMap;
import java.util.Map;

/*
 * 
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
 * 
 */
public class Pro_0013_romanToInteger {

	public static void main(String[] args) {
		System.out.println(romanToInt1("M"));
	}
	
	private static int E = -1;
	private static int[][] MATRIX = {
									{1, 2, 3, E, E, 6, 7, 8, E, E},
									{5, 4, E, E, E, E, E, E, E, E},
									{E, 9, E, E, E, E, E, E, E, E}
							   		  };

	public static int romanToInt1(String s) {
		int sum = 0, idx = 0, len = s.length();
		while(idx < len && s.charAt(idx) == 'M') { idx++; }
		sum += idx*1000;
		
		int st = 0, state = 0;
		while(idx < len) {
			int type = hundred(s.charAt(idx));
			if(type == E || (st = MATRIX[type][state]) == E) { break; }
			state = st;
			idx++;
		}
		sum += state*100;
		
		st = 0; state = 0;
		while(idx < len) {
			int type = ten(s.charAt(idx));
			if(type == E || (st = MATRIX[type][state]) == E) { break; }
			state = st;
			idx++;
		}
		sum += state*10;
		
		st = 0; state = 0;
		while(idx < len) {
			int type = one(s.charAt(idx));
			if(type == E || (st = MATRIX[type][state]) == E) { break; }
			state = st;
			idx++;
		}
		sum += state;
		return sum;
	}
	
	private static int hundred(char ch) {
		switch(ch) {
		case 'C': return 0;
		case 'D': return 1;
		case 'M': return 2;
		default: return E; 
		}
	}
	
	private static int ten(char ch) {
		switch(ch) {
		case 'X': return 0;
		case 'L': return 1;
		case 'C': return 2;
		default: return E; 
		}
	}
	
	private static int one(char ch) {
		switch(ch) {
		case 'I': return 0;
		case 'V': return 1;
		case 'X': return 2;
		default: return E; 
		}
	}
	
	private static final Map<String, Integer> MAP = new HashMap<String, Integer>(32, 1);
	static {
		MAP.put("M", 1000); MAP.put("MM", 2000); MAP.put("MMM", 3000);
		MAP.put("C", 100); MAP.put("CC", 200); MAP.put("CCC", 300); MAP.put("CD", 400); MAP.put("D", 500);
		MAP.put("DC", 600); MAP.put("DCC", 700); MAP.put("DCCC", 800); MAP.put("CM", 900);
		MAP.put("X", 10); MAP.put("XX", 20); MAP.put("XXX", 30); MAP.put("XL", 40); MAP.put("L", 50);
		MAP.put("LX", 60); MAP.put("LXX", 70); MAP.put("LXXX", 80); MAP.put("XC", 90);
		MAP.put("I", 1); MAP.put("II", 2); MAP.put("III", 3); MAP.put("IV", 4); MAP.put("V", 5);
		MAP.put("VI", 6); MAP.put("VII", 7); MAP.put("VIII", 8); MAP.put("IX", 9);
	}

	public static int romanToInt(String s) {
        int sum = 0, len = s.length();
        
        int begin = 0, end = 0;
        while(end < len && s.charAt(end) == 'M') { end++; }
        sum += 1000 * (end-begin);
        if((begin = end) == len) { return sum; }
        
        while(end < len) {
        	char ch = s.charAt(end);
        	if(ch == 'C' || ch == 'D' || ch == 'M') {
        		end++;
        	}else { break; }
        }
        if(begin < end) {
        	Integer num = MAP.get(s.substring(begin, end));
        	if(num != null) { sum += num; }
        }
        if((begin = end) == len) { return sum; }
        
        while(end < len) {
        	char ch = s.charAt(end);
        	if(ch == 'X' || ch == 'L' || ch == 'C') {
        		end++;
        	}else { break; }
        }
        if(begin < end) {
        	Integer num = MAP.get(s.substring(begin, end));
        	if(num != null) { sum += num; }
        }
        if((begin = end) == len) { return sum; }
        
        if(begin < len) {
        	Integer num = MAP.get(s.substring(begin, len));
        	if(num != null) { sum += num; }
        }
        return sum;
    }
}
