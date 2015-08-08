package com.lee.leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class Pro_0010_regularExpressionMatching {

	public static void main(String[] args) {
		String s = "aa";
		String p = ".*";
		System.err.println(isMatch(s, p));
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
        return new StateGraph(p).match(s);
    }
	
	static class StateGraph {
		static final char WILDCARD = '.';
		static final int DEF_EDGE_NUM = 4;
		static final Character NULL_EDGE = null;
		static final State END = new State("E", 0, true);
		
		private State start = new State("S", DEF_EDGE_NUM, false);
		private Set<State> validStates;
		
		StateGraph(String p) {
			buildGraph(p);
			System.out.println("plain: \n"+this);
			removeNullEdges();
			System.out.println("after adjusted: \n"+this);
		}
		
		private void buildGraph(String p) {
			int i = 0, len = p.length();
			validStates = new HashSet<State>(len, 1);
			validStates.add(start);
			State prev = start;
			int id = 1;
			while(i<len) {
				char first = p.charAt(i);
				if(i+1 < len) {
					char second = p.charAt(i+1);
					if(second == '*') {
						State s = new State(id++, 2);
						State e = new State(id++, DEF_EDGE_NUM);
						prev.addEdge(first, s);
						s.addEdge(first, s);
						s.addEdge(NULL_EDGE, e);
						prev.addEdge(NULL_EDGE, e);
						validStates.add(s);
						prev = e;
					}else {
						State s = new State(id++, 1);
						prev.addEdge(first, s);
						State e = new State(id++, DEF_EDGE_NUM);
						s.addEdge(second, e);
						validStates.add(s);
						validStates.add(e);
						prev = e;
					}
					i += 2;
				}else {
					State s = new State(id++, 1);
					prev.addEdge(first, s);
					validStates.add(s);
					prev = s;
					i += 1;
				}
			}
			prev.addEdge(NULL_EDGE, END);
		}
		
		private void removeNullEdges() {
			Set<State> reachedSet = new HashSet<State>(validStates.size());
			for(State s : validStates) {
				reachedSet.clear();				
				collectNullReachedState(s, reachedSet);
				for(State r : reachedSet) {
					for(Entry<Character, State> entry : r.edges.entrySet()) {
						if(entry.getKey() != NULL_EDGE) {
							s.addEdge(entry.getKey(), entry.getValue());
						}
					}
				}
			}
			
			// remove null edges
			for(State s : validStates) {
				State e = s.removeEdge(NULL_EDGE);
				while(e != null) {
					if(e == END) {
						s.markAsEndState();
						break;
					}
					e = e.edges.get(NULL_EDGE);
				}
			}
		}
		
		private void collectNullReachedState(State source, Set<State> reachedSet) {
			State e = source.edges.get(NULL_EDGE);
			while(e != null) {
				reachedSet.add(e);
				e = e.edges.get(NULL_EDGE);
			}
		}
		
		boolean match(String s) {
			State e = start;
			int len = s.length();
			for(int i=0; i<len; i++) {
				char ch = s.charAt(i);
				State n = e.edges.get(ch);
				if(n == null) {
					n = e.edges.get(WILDCARD);
					if(n == null) {
						e = n;
						break;
					}
				}
				e = n;
			}
			return e != null && e.isEnd;
		}
		
		public String toString() {
			Set<State> visited = new HashSet<State>();
			Queue<State> needVisiting = new LinkedList<State>();
			needVisiting.offer(start);
			StringBuilder buf = new StringBuilder(32);
			State s = null;
			buf.append("state - relationship - graph: \n");
			while((s = needVisiting.poll()) != null) {
				if(visited.contains(s)) { continue; }
				buf.append("{").append(s.id);
				if(s.isEnd) { buf.append("(E)"); }
				if(!s.edges.isEmpty()) {
					buf.append(" : ");
					for(Entry<Character, State> entry : s.edges.entrySet()) {
						Character ch = entry.getKey();
						State e = entry.getValue();
						if(ch == null) {
							buf.append("--->");
						}else {
							buf.append("->(").append(ch).append(")->");
						}
						buf.append(e.id).append(", ");
						needVisiting.offer(e);
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
					buf.append(e.id).append(", ");
				}
				buf.setLength(buf.length()-2);
			}
			buf.append("}\n");
			return buf.toString();
		}
	}
	
	static class State {
		boolean  isEnd;
		String id;
		Map<Character, State> edges = Collections.emptyMap();
		State(int id, int edgeNum) { this(String.valueOf(id), edgeNum, false); }
		State(String id, int edgeNum, boolean isEnd) {
			this.id = id;
			this.isEnd = isEnd;
			if(edgeNum > 0) { edges = new HashMap<Character, State>(edgeNum, 1); }
		}
		void addEdge(Character edgeChar, State targetState) {
			edges.put(edgeChar, targetState);
		}
		State removeEdge(Character edgeChar) { return edges.remove(edgeChar); }
		void markAsEndState() { isEnd = true; }
	}
}
