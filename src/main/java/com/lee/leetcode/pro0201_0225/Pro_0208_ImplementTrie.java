package com.lee.leetcode.pro0201_0225;

/**
 *
 Implement a trie with insert, search, and startsWith methods.

 Example:
     Trie trie = new Trie();

     trie.insert("apple");
     trie.search("apple");   // returns true
     trie.search("app");     // returns false
     trie.startsWith("app"); // returns true
     trie.insert("app");
     trie.search("app");     // returns true

 Note:
     You may assume that all inputs are consist of lowercase letters a-z.
     All inputs are guaranteed to be non-empty strings.
 *
 */
public class Pro_0208_ImplementTrie {

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    public static void main(String[] args) {
        Pro_0208_ImplementTrie trie = new Pro_0208_ImplementTrie();
        boolean result = false;
        trie.insert("apple");
        result = trie.search("apple");   // returns true
        System.out.println(result);
        result = trie.search("app");     // returns false
        System.out.println(result);
        result = trie.startsWith("app"); // returns true
        System.out.println(result);
        trie.insert("app");
        result = trie.search("app");     // returns true
        System.out.println(result);
    }

    private static class Node {
         private Node[] childs;
         private int hasWord;

         Node() {
             childs = new Node[26];
             hasWord = 0;
         }
         Node addInnerChar(char ch) {
             int index = ch - 'a';
             Node child = childs[index];
             if(child == null) {
                 childs[index] = child = new Node();
             }
             return child;
         }
         Node childNode(char ch) {
             return childs[ch-'a'];
         }
         void addEndChar(char ch) {
             int index = ch - 'a';
             hasWord |= (1 << index);
         }
         boolean hasWord(char ch) {
             return ((1 << (ch-'a')) & hasWord) != 0;
         }
    }

    private Node root;

    /** Initialize your data structure here. */
    public Pro_0208_ImplementTrie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        int endIndex = word.length() - 1;
        Node parent = root;
        for(int i=0; i<endIndex; i++) {
            parent = parent.addInnerChar(word.charAt(i));
        }
        parent.addEndChar(word.charAt(endIndex));
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        int endIndex = word.length() - 1;
        Node parent = root;
        for(int i=0; i<endIndex; i++) {
            parent = parent.childNode(word.charAt(i));
            if(parent == null) { return false; }
        }
        return parent.hasWord(word.charAt(endIndex));
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        int endIndex = prefix.length() - 1;
        Node parent = root;
        for(int i=0; i<endIndex; i++) {
            parent = parent.childNode(prefix.charAt(i));
            if(parent == null) { return false; }
        }
        char ch = prefix.charAt(endIndex);
        return parent.childNode(ch) != null || parent.hasWord(ch);
    }
}
