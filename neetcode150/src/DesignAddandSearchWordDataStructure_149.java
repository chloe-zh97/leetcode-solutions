public class DesignAddandSearchWordDataStructure_149 {
    public static void main(String[] args) {

    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean endOfWord = false;
    }

    static class WordDictionary {
        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode p = root;
            for(char c: word.toCharArray()) {
                if(p.children[c-'a'] == null) {
                    p.children[c-'a'] = new TrieNode();
                }
                p = p.children[c-'a'];
            }
            p.endOfWord = true;
        }

        public boolean search(String word) {
            return dfs(word, 0, root);
        }

        private boolean dfs(String word, int index, TrieNode root) {
            TrieNode p = root;

            for(int i=index;i<word.length();i++) {
                char c = word.charAt(i);
                if(c == '.') {
                    // 所有不为空的位置
                    for(TrieNode child: p.children) {
                        if(child != null) {
                            boolean res = dfs(word, i+1, child);
                            if(res) return true;
                        }
                    }
                    return false;
                } else {
                    if(p.children[c-'a'] == null) return false;
                    p = p.children[c-'a'];
                }
            }
            return p.endOfWord;
        }

    }
}
