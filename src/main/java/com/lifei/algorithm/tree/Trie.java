package com.lifei.algorithm.tree;

class Trie {
    TrieNode trieNode;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        trieNode = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode newNode = trieNode;
        for (int i = 0; i < word.length(); i++) {
            if (!newNode.containsChar(word.charAt(i))) {
                newNode.addNode(word.charAt(i), new TrieNode());
            }
            newNode = newNode.getNode(word.charAt(i));
//            System.out.println("insert "+word.charAt(i));

        }
        newNode.setEnd();
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode newNode = trieNode;
        for (int i = 0; i < word.length(); i++) {
            System.out.println(newNode.containsChar(word.charAt(i)));
            if (newNode.containsChar(word.charAt(i))) {
                newNode = newNode.getNode(word.charAt(i));
            } else
                return false;
        }
        return newNode.getEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode newNode = trieNode;
        for (int i = 0; i < prefix.length(); i++) {
            if (newNode.containsChar(prefix.charAt(i))) {
                newNode = newNode.getNode(prefix.charAt(i));
            } else
                return false;
        }
        return true;
    }

    public class TrieNode {

        private TrieNode[] root;
        int R = 26;
        private boolean isEnd = false;

        public TrieNode() {
            root = new TrieNode[R];
        }

        public void addNode(char c, TrieNode node) {
            root[c - 'a'] = node;
        }

        public boolean containsChar(char c) {
            return root[c - 'a'] != null;
        }

        public TrieNode getNode(char c) {
            return root[c - 'a'];
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean getEnd() {
            return isEnd;
        }

    }

    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("word");
        boolean param_2 = obj.search("word");
        System.out.println(param_2);
        boolean param_3 = obj.startsWith("prefix");
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */