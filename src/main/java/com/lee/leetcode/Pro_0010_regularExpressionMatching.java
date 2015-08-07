package com.lee.leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Pro_0010_regularExpressionMatching {

	public static void main(String[] args) {
		String s = "aab";
		String p = "c*a*b";
		System.out.println(isMatch(s, p));
	}

	/**
	 * 任意2个字符的组合：
	 * 
	 * ''    -->  start -> end
	 * 
	 * 'a'   -->  start -> a -> end
	 * 
	 * '.'   -->  start -> . -> end
	 * 
	 * 'an'  -->  start -> a -> n -> end
	 * 
	 * 'a.'  -->  start -> a -> . -> end
	 * 
	 * '.a'  -->  start -> . -> a -> end
	 * 
	 *            --------------
	 *            |            |
	 * 'a*'  -->  start ->  a  -> end
	 *                   | |
	 *                   ---
	 * 
	 *            --------------
	 *            |            |
	 * .*  -->  start ->  .  -> end
	 *                   | | 
	 *                   ---   
	 * 
	 */
	public static boolean isMatch(String s, String p) {
        return p.isEmpty() ? s.isEmpty() : new StateGraph(p).match(s);
    }
	
	static class StateGraph {
		static final int DEF_EDGE_NUM = 4;
		static final Character NULL_EDGE = null;
		
		State START = new State(DEF_EDGE_NUM);
		State END = new State(0);
		
		StateGraph(String p) {
			buildGraph(p);
			removeNullEdges();
		}
		
		private void buildGraph(String p) {
			int i = 0, len = p.length();
			State start = START;
			while(i<len) {
				char first = p.charAt(i);
				if(i+1 < len) {
					char second = p.charAt(i+1);
					if(second == '*') {
						State s = new State(2);
						State e = new State(DEF_EDGE_NUM);
						start.addEdge(first, s);
						s.addEdge(first, s);
						s.addEdge(NULL_EDGE, e);
						start.addEdge(NULL_EDGE, e);
						start = e;
					}else {
						State s = new State(1);
						start.addEdge(first, s);
						State e = new State(DEF_EDGE_NUM);
						s.addEdge(second, e);
						start = e;
					}
					i += 2;
				}else {
					State s = new State(1);
					start.addEdge(first, s);
					start = s;
					i += 1;
				}
			}
			start.addEdge(NULL_EDGE, END);
		}
		
		private void removeNullEdges() {
			
		}
		
		boolean match(String s) {
			return false;
		}
	}
	
	static class State {
		Map<Character, State> edges;
		State(int edgeNum) {
			edges = edgeNum <= 0 ? Collections.emptyMap() : new HashMap<Character, State>(edgeNum, 1);
		}
		void addEdge(char edgeChar, State targetState) {
			
		}
		State targetState(char edgeChar) { return edges.get(edgeChar); }
	}
}
