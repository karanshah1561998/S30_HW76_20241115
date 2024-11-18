// Problem 425. Word Squares
// Time Complexity : O(p⋅k⋅n^k)
// Space Complexity :O(n⋅k)+O(k^2)+O(n^k⋅k^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class Solution {
    class TrieNode {
        TrieNode[] children;
        List<String> startsWith;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.startsWith = new ArrayList<>();
        }
    }

    private void insert(TrieNode root, String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.startsWith.add(word);
        }
    }

    private List<String> search(TrieNode root, String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.children[c - 'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.startsWith;
    }

    List<List<String>> result;

    public List<List<String>> wordSquares(String[] words) {
        this.result = new ArrayList<>();
        TrieNode root = new TrieNode();

        // Build the Trie
        for (String word : words) {
            insert(root, word);
        }

        List<String> li = new ArrayList<>();
        for (String word : words) {
            li.add(word); // Action: Add the word to the current list
            backtrack(root, li, result); // Recurse to find other words
            li.remove(li.size() - 1);   // Backtrack: Remove the last word
        }
        return result;
    }

    private void backtrack(TrieNode root, List<String> li, List<List<String>> result) {
        // Base case: If the list size equals the word length, it's a valid square
        if (li.size() == li.get(0).length()) {
            result.add(new ArrayList<>(li));
            return;
        }

        // Build the prefix for the next word
        int index = li.size();
        StringBuilder prefix = new StringBuilder();
        for (String word : li) {
            prefix.append(word.charAt(index));
        }

        // Get all words with the given prefix
        List<String> startsWith = search(root, prefix.toString());
        for (String word : startsWith) {
            li.add(word); // Action: Add the word to the current list
            backtrack(root, li, result); // Recurse
            li.remove(li.size() - 1); // Backtrack
        }
    }
}