package com.lee.leetcode.pro0001_0025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
 * 
 */
public class Pro_0010_regularExpressionMatching_ {

	public static void main(String[] args) {
		String s = "aaa";
		String p = ".*a";
		System.err.println(isMatch2(s, p));
	}
	
	/** DP **/
	public static boolean isMatch2(String s, String p) {
		 int pLen = p.length();
		 int sLen = s.length();
		 if(pLen == 0) { return sLen == 0; }
		 boolean[] match = new boolean[sLen+1];
		 match[sLen] = true;
		 for(int i=pLen-1; i>=0; i--) {
			 char pch = p.charAt(i);
			 if(pch == '*') {
				 char ch = p.charAt(i-1);
				 for(int j=sLen-1; j>=0; j--) {
					 match[j] = match[j] || match[j+1] && (ch == '.' || ch == s.charAt(j));
				 }
				 i--;
			 }else {
				 for(int j=0; j<sLen; j++) {	// 基于前一次p[i+1]的扫描结果进行匹配，故FOR循环顺序必须从小到大
					 match[j] = match[j+1] && (pch == '.' || pch == s.charAt(j));
				 }
				 match[sLen] = false;
			 }
		 }
		 return match[0];
    }
	
	public static boolean isMatch1(String s, String p) {
        return java.util.regex.Pattern.matches(p, s);
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
		return Pattern.compile(p).match(s);
    }
	
	static class Pattern {
		static final int DEF_EDGE_NUM = 4;
		static final State END = new State(State.END, 0, true);
		
		public static Pattern compile(String p) { return new Pattern(p); }
		
		private State start = new State(State.START, DEF_EDGE_NUM, false);
		private Set<State> validStates;
		
		private Pattern(String p) {
			buildStateGraph(p);
			System.out.println("plain: \n"+this);
			copyNullReachedEdges();
			removeNullEdges();
			System.out.println("after adjusted: \n"+this);
		}
		
		private void buildStateGraph(String p) {
			int i = 0, len = p.length();
			validStates = new HashSet<State>(len, 1);
			validStates.add(start);
			State prev = start;
			int id = 1;		// state node id begin with 1
			while(i<len) {
				char first = p.charAt(i);
				if(i+1 < len) {
					char second = p.charAt(i+1);
					if(second == '*') {
						State s = new State(id++, 2);
						State e = new State(id++, DEF_EDGE_NUM);
						prev.addEdge(first, s);
						s.addEdge(first, s);
						s.addEdge(Edge.NULL_EDGE, e);
						prev.addEdge(Edge.NULL_EDGE, e);
						validStates.add(s);
						prev = e;
						i += 2;
					}else {
						State s = new State(id++, DEF_EDGE_NUM);
						prev.addEdge(first, s);
						validStates.add(s);
						prev = s;
						i += 1;
					}
				}else {
					State s = new State(id++, 1);
					prev.addEdge(first, s);
					validStates.add(s);
					prev = s;
					i += 1;
				}
			}
			prev.addEdge(Edge.NULL_EDGE, END);
		}
		
		private void copyNullReachedEdges() {
			Set<State> reachedSet = new HashSet<State>(validStates.size());
			for(State s : validStates) {
				reachedSet.clear();				
				s.collectNullReachedState(reachedSet);
				for(State r : reachedSet) {
					for(Edge e : r.edgeList) {
						if(e.ch != Edge.NULL_EDGE) { s.addEdge(e.ch, e.target); }
					}
				}
			}
		}
		
		private void removeNullEdges() {
			for(State s : validStates) { s.removeNullEdges(); }
		}
		
		boolean match(String s) {
			return start.match(s, 0);
		}
		
		public String toString() {
			Set<State> visited = new HashSet<State>();
			Queue<State> needVisiting = new LinkedList<State>();
			needVisiting.offer(start);
			StringBuilder buf = new StringBuilder(64);
			State s = null;
			buf.append("state - relationship - graph: \n");
			while((s = needVisiting.poll()) != null) {
				if(visited.contains(s)) { continue; }
				buf.append("{").append(s.id());
				if(!s.edgeList.isEmpty()) {
					buf.append(" : ");
					for(Edge e : s.edgeList) {
						if(e.ch == Edge.NULL_EDGE) {
							buf.append("--->");
						}else {
							buf.append("->(").append(e.ch).append(")->");
						}
						buf.append(e.target.id()).append(", ");
						needVisiting.offer(e.target);
					}
					buf.setLength(buf.length()-2);
				}
				buf.append("}\n");
				visited.add(s);
			}
			buf.append("valid state: \n");
			buf.append("{");
			if(!validStates.isEmpty()) {
				for(State e : validStates) {
					buf.append(e.id()).append(", ");
				}
				buf.setLength(buf.length()-2);
			}
			buf.append("}\n");
			return buf.toString();
		}
	}
	
	static class State {
		static final int INVLID_ID = 0;
		static final int START = -1;
		static final int END = -2;
		
		boolean isEnd;
		int id;
		List<Edge> edgeList = Collections.emptyList();
		
		State(int id, int edgeNum) { this(id, edgeNum, false); }
		State(int id, int edgeNum, boolean isEnd) {
			this.isEnd = isEnd;
			this.id = id;
			if(edgeNum > 0) { edgeList = new ArrayList<Edge>(edgeNum); }
		}
		void markAsEndState() { isEnd = true; }
		String id() {return id==START ? "S" : id==END ? "E" : String.valueOf(id)+(isEnd ? "[E]":""); }
		void addEdge(Character edgeChar, State targetState) {
			edgeList.add(new Edge(edgeChar, targetState));
		}
		void collectNullReachedState(Set<State> reachedSet) {
			for(Edge e : edgeList) {
				if(e.ch == Edge.NULL_EDGE) {
					reachedSet.add(e.target);
					e.target.collectNullReachedState(reachedSet);
				}
			}
		}
		void removeNullEdges() {
			Iterator<Edge> iter = edgeList.iterator();
			while(iter.hasNext()) {
				Edge e = iter.next();
				if(e.ch == Edge.NULL_EDGE) {
					iter.remove();
					if(e.target.canNullReachEnd()) { markAsEndState(); }
				}
			}
		}
		private boolean canNullReachEnd() {
			if(isEnd) { return true; }
			for(Edge e : edgeList) {
				if(e.ch == Edge.NULL_EDGE && e.target.canNullReachEnd()) { return true; }
			}
			return false;
		}
		/** don't need (NFA -> DFA) conversation **/
		boolean match(String s, int index) {
			if(index == s.length()) { return isEnd; }
			char ch = s.charAt(index);
			for(Edge e : edgeList) {
				if((e.ch == ch || e.ch == Edge.WILDCARD)
					&& e.target.match(s, index+1)) {
					return true;
				}
			}
			return false;
		}
	}

	static class Edge {
		static final Character NULL_EDGE = null;
		static final Character WILDCARD = '.';
		Character ch;
		State target;
		Edge(Character ch, State target) {
			this.ch = ch;
			this.target = target;
		}
	}
}
