package com.lee.leetcode.pro0201_0225;

/**
 *
 Design a data structure that supports the following two operations:
    void addWord(word)
    bool search(word)
 search(word) can search a literal word or a regular expression string containing only letters 'a-z' or '.'.
 A '.' means it can represent any one letter.

 Example:
     addWord("bad")
     addWord("dad")
     addWord("mad")
     search("pad") -> false
     search("bad") -> true
     search(".ad") -> true
     search("b..") -> true

 Note:
    You may assume that all words are consist of lowercase letters 'a-z'.
 *
 */
public class Pro_0211_AddAndSearchWord {

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
    public static void main(String[] args) {
        Pro_0211_AddAndSearchWord dict = new Pro_0211_AddAndSearchWord();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        boolean result = false;
        result = dict.search("pad");
        System.out.println(result);
        result = dict.search("bad");
        System.out.println(result);
        result = dict.search(".ad");
        System.out.println(result);
        result = dict.search("b..");
        System.out.println(result);
    }

    private static class Node {
        private Node[] childs;
        private boolean hasWord;

        Node() {
            childs = null;
            hasWord = false;
        }
        Node addChar(char ch) {
            Node child;
            if(childs == null) {
                childs = new Node[26];
                child = new Node();
                childs[ch-'a'] = child;
            }else {
                child = childs[ch-'a'];
                if(child == null) {
                    childs[ch-'a'] = child = new Node();
                }
            }
            return child;
        }
        void setWord() {
            hasWord = true;
        }
        Node childNode(char ch) {
            return childs == null ? null : childs[ch-'a'];
        }
        boolean match(String word, int index) {
            Node p = this;
            while(index < word.length()) {
                char ch = word.charAt(index);
                if(p.childs == null) { return false; }
                if(ch == '.') {
                    for(Node child : p.childs) {
                        if(child != null && child.match(word, index+1)) {
                            return true;
                        }
                    }
                    return false;
                }else {
                    p = p.childs[ch-'a'];
                    if(p == null) { return false; }
                    index++;
                }
            }
            return p.hasWord;
        }
    }

    private Node root;

    /** Initialize your data structure here */
    public Pro_0211_AddAndSearchWord() {
        root = new Node();
    }

    /** Adds a word into the data structure */
    public void addWord(String word) {
        int length = word.length();
        Node parent = root;
        for(int i=0; i<length; i++) {
            char ch = word.charAt(i);
            parent = parent.addChar(ch);
        }
        parent.setWord();
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter */
    public boolean search(String word) {
        return root.match(word, 0);
    }
}
