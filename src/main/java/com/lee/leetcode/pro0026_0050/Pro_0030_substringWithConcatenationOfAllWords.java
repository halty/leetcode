package com.lee.leetcode.pro0026_0050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Pro_0030_substringWithConcatenationOfAllWords {

	public static void main(String[] args) {
		String s = "barfoothefoobarman";
		String[] words = {"foo", "bar"};
		System.out.println(findSubstring(s, words));
	}

    public static List<Integer> findSubstring(String s, String[] words) {
    	int sLen = s.length();
        int count = words.length;
        if(count == 0) { return Arrays.asList(0); }
        int wLen = words[0].length();
        if(wLen == 0) { return Arrays.asList(0); }
        int wsLen = wLen*count;
        if(wsLen > sLen) { return Collections.emptyList(); }
        
        if(count == 1) {
        	List<Integer> list = new ArrayList<Integer>();
        	int max = sLen - wLen;
        	String w = words[0];
        	for(int i=0; i<=max; i++) {
        		boolean match = true;
        		for(int j=0; j<wLen; j++) {
        			if(s.charAt(i+j) != w.charAt(j)) {
        				match = false;
        				break;
        			}
        		}
        		if(match) { list.add(i); }
        	}
        	return list;
        }
        
        
        List<Integer> list = new ArrayList<Integer>();
        boolean[] flags = new boolean[count];
        int max = sLen - wsLen;
        for(int i=0; i<=max; i++) {
        	boolean match = true;
        	int matchCount = 0;
        	int begin = i;
        	while(matchCount < count) {
        		if(matchOneWord(s, begin, words, matchCount, flags, wLen)) {
        			matchCount++;
        			begin += wLen;
        		}else {
        			match = false;
        			break;
        		}
        	}
        	if(match) { list.add(i); }
        	Arrays.fill(flags, false);
        }
    	return list;
    }
    
    private static final boolean matchOneWord(String s, int begin, String[] words, int matchCount, boolean[] flags, int wLen) {
    	for(int i=0; i<words.length; i++) {
    		if(!flags[i] && match(s, begin, words[i], wLen)) {
    			flags[i] = true;
    			return true;
    		}
    	}
    	return false;
    }
    
    private static final boolean match(String s, int begin, String word, int wLen) {
    	boolean match = true;
    	for(int i=0; i<wLen; i++) {
    		if(s.charAt(begin+i) != word.charAt(i)) {
				match = false;
				break;
			}
    	}
    	return match;
    }
}
