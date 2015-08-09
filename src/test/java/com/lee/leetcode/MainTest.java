package com.lee.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainTest {

	public static void main(String[] args) {
		String s = "baa";
		String p = ".*a";
		System.err.println(isMatch(s, p));
	}

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
			copyNullReachedEdges();
			removeNullEdges();
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
