import java.util.*;

public class WordBreak_105 {
    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String[] wordDict = {"aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa","ba"};
        List<String> dict = new ArrayList<>(Arrays.asList(wordDict));
        System.out.println(wordBreak_4(s, dict));
    }

    /**
     * Method 1: 暴力法，dfs 超时
     * */
    private static Map<Character, List<String>> dict;
    public static boolean wordBreak(String s, List<String> wordDict) {
        dict = new HashMap<>();
        // 构造词典
        for(String word: wordDict) {
            char c = word.charAt(0);
            List<String> list = dict.getOrDefault(c, new LinkedList<>());
            list.add(word);
            dict.put(c, list);
        }

        return dfs(s, 0);
    }

    /**
     * 从 s[i...n-1] 是否能构造完成
     * */
    private static boolean dfs(String s, int i) {
        int n = s.length();
        // 匹配到了末尾
        if(i >= n) return true;

        if(!dict.containsKey(s.charAt(i))) return false;

        for(String word: dict.get(s.charAt(i))) {
            int wordLen = word.length();
            if(i+wordLen <= n && s.substring(i, i+wordLen).equals(word)) {
                boolean res = dfs(s, i+wordLen);
                if(res) return true;
            }
        }

        return false;
    }


    /**
     * Method 2: 完全背包
     * */
    public static boolean wordBreak_2(String s, List<String> wordDict) {
        int n = s.length();

        // dp[i+1]: s[0...i] 是否能构成合理串
        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for(int i=0;i<n;i++) {
            for(String word: wordDict) {
                int len = word.length();
                if(i+len <= n && s.substring(i,i+len).equals(word)) {
                    if(!dp[i+len]) dp[i+len] = dp[i];
                }
            }
        }

        return dp[n];
    }


    /**
     * Method 3: dfs
     * */
    public static boolean wordBreak_3(String s, List<String> wordDict) {
        return dfs_3(s, 0, wordDict);
    }

    private static boolean dfs_3(String s, int i, List<String> wordDict) {
        int n = s.length();
        if(i >= n) return true;

        // 遍历每个word
        for(String word: wordDict) {
            int wordLen = word.length();
            if(i+wordLen > n || !s.substring(i,i+wordLen).equals(word)) continue;

            boolean res = dfs_3(s, i+wordLen, wordDict);
            if(res) return true;
        }

        return false;
    }

    /**
     * Method 4: 优化，用 hashset 存储 wordDict 超时
     * */
    public static boolean wordBreak_4(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        int[] memos = new int[n];
        Arrays.fill(memos, -1);

        return dfs_4(s, 0, wordSet, memos);
    }

    private static boolean dfs_4(String s, int i, Set<String> wordSet, int[] memos) {
        int n = s.length();
        if(i >= n) return true;

        if(memos[i] != -1) return memos[i] == 1;

        for(int j=i;j<n;j++) {
            // j 表示终点元素
            if(wordSet.contains(s.substring(i, j+1))) {
                boolean res = dfs_4(s, j+1, wordSet, memos);
                if(res) {
                    memos[i] = 1;
                    return true;
                }
            }
        }
        memos[i] = 0;
        return false;
    }


}
