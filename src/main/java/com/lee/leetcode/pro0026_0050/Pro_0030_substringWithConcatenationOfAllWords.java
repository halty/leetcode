package com.lee.leetcode.pro0026_0050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class Pro_0030_substringWithConcatenationOfAllWords {

	public static void main(String[] args) {
		String s = "barfoothefoobarman";
		String[] words = {"foo", "bar"};
		System.out.println(findSubstring(s, words));
		System.out.println(findSubstring1(s, words));
	}

	public static List<Integer> findSubstring1(String s, String[] words) {
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
        Map<String, Count> wordCnt = preProcess(words);
        Map<String, Count> wordMap = new HashMap<String, Count>(wordCnt.size(), 1);
        int max = Math.min(wLen, sLen-wsLen+1);
        for(int i=0; i<max; i++) {
        	Queue<Element> queue = new LinkedList<Element>();
        	initCnt(wordCnt, wordMap);
        	int begin = i, reservedLen = count*wLen;
        	while(reservedLen+begin <= sLen) {
        		String w = s.substring(begin, begin+wLen);
        		Count cnt = wordMap.get(w);
        		if(cnt == null || cnt.count == 0) {
        			Element e = null;
        			while((e = queue.poll()) != null) {
        				if(w.equals(e.word)) {
        					e.beginIndex = begin;
        					queue.offer(e);
        					break;
        				}else {
        					reservedLen += wLen;
        					wordMap.get(e.word).count++;
        				}
        			}
        			begin += wLen;
        		}else {
        			cnt.count--;
        			reservedLen -= wLen;
        			queue.offer(new Element(w, begin));
        			if(reservedLen == 0) {
        				Element e = queue.poll();
        				list.add(e.beginIndex);
        				reservedLen += wLen;
        				wordMap.get(e.word).count++;
        			}
        			begin += wLen;
        		}
        	}
        }
        return list;
	}
	
	private static final class Element {
		String word;
		int beginIndex;
		Element(String word, int beginIndex) {
			this.word = word;
			this.beginIndex = beginIndex;
		}
	}
	
	private static Map<String, Count> preProcess(String[] words) {
		Map<String, Count> wordMap = new HashMap<String, Count>(words.length/2+1, 1);
		for(String word : words) {
			Count cnt = wordMap.get(word);
			if(cnt == null) {
				cnt = new Count(0);
				wordMap.put(word, cnt);
			}
			cnt.increment();
		}
		return wordMap;
	}
	
	private static void initCnt(Map<String, Count> wordCnt, Map<String, Count> wordMap) {
		for(Entry<String, Count> entry : wordCnt.entrySet()) {
			Count cnt = wordMap.get(entry.getKey());
			if(cnt == null) {
				cnt = new Count(entry.getValue().count);
				wordMap.put(entry.getKey(), cnt);
			}else {
				cnt.count = entry.getValue().count;
			}
		}
	}
	
	private static final class Count {
		int count;
		Count(int count) { this.count = count; }
		void increment() { count++; }
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
