import java.util.HashMap;
import java.util.Map;

public class ImplementTrie_148 {
    public static void main(String[] args) {

    }

    /**
     * Method 1: ArrayList
     * */
    static class TrieNode_1 {
        TrieNode_1[] children = new TrieNode_1[26];
        boolean endOfWord = false;
    }


    static class PrefixTree_1 {
        TrieNode_1 root;

        public PrefixTree_1() {
            // 初始化根节点
            root = new TrieNode_1();
        }

        public void insert(String word) {
            // 从根节点开始找
            TrieNode_1 p = root;
            for(char c: word.toCharArray()) {
                if(p.children[c-'a'] == null) {
                    // 没有这个孩子节点
                    p.children[c-'a'] = new TrieNode_1();
                }

                p = p.children[c-'a'];
            }
            p.endOfWord = true;
        }

        public boolean search(String word) {
            TrieNode_1 p = root;
            for(char c: word.toCharArray()) {
                if(p.children[c-'a'] == null) return false;
                p = p.children[c-'a'];
            }
            return p.endOfWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode_1 p = root;
            for(char c: prefix.toCharArray()) {
                if(p.children[c-'a'] == null) return false;
                p = p.children[c-'a'];
            }
            return true;
        }
    }

    /**
     * Method 2: hashMap
     * */
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean endOfWord = false;
    }

    static class PrefixTree {
        TrieNode root;

        public PrefixTree() {
           root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode p = root;
            for(char c: word.toCharArray()) {
                if(!p.children.containsKey(c)) {
                    p.children.put(c, new TrieNode());
                }
                p = p.children.get(c);
            }
            p.endOfWord = true;
        }

        public boolean search(String word) {
            TrieNode p = root;
            for(char c: word.toCharArray()) {
                if(!p.children.containsKey(c)) return false;
                p = p.children.get(c);
            }
            return p.endOfWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode p = root;
            for(char c: prefix.toCharArray()) {
                if(!p.children.containsKey(c)) return false;
                p = p.children.get(c);
            }
            return true;
        }
    }


}
