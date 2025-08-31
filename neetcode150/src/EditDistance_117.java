import java.util.Arrays;

public class EditDistance_117 {
    public static void main(String[] args) {
        String word1 = "monkeys";
        String word2 = "money";
        System.out.println(minDistance(word1, word2));
    }


    /**
     * Method 1: 暴力法
     * */
    public static int minDistance(String word1, String word2) {
        return dfs(word1, 0, word2, 0);
    }

    /**
     * word1[i...m-1] 完全变成 word2[j...n-1] 最少需要几次操作
     * */
    private static int dfs(String word1, int i, String word2, int j) {
        int m = word1.length(), n = word2.length();
        // j 已经走到末尾了，i 还有没走完的字母，全部删除
        if(j == n) return m-i;

        // j 没走到末尾，但是 i 走到末尾了，插入单词
        if(i == m) return n-j;

        // 插入，删除，替换，不做操作

        // 插入一个和 j 一样的字母，j 往下继续匹配
        int r1 = dfs(word1, i, word2, j+1) + 1;
        // 删除 i 这个字母
        int r2 = dfs(word1, i+1, word2, j) + 1;
        // 替换i这个字母让它和j一样
        int r3 = dfs(word1, i+1, word2, j+1) + (word1.charAt(i) == word2.charAt(j) ? 0: 1);

        return Math.min(Math.min(r1, r2), r3);
    }


    /**
     * Method 2: 记忆化搜索
     * */
    public static int minDistance_2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] memos = new int[m][n];
        for(int[] mm: memos)
            Arrays.fill(mm, -1);
        return dfs_2(word1, 0, word2, 0, memos);
    }

    private static int dfs_2(String word1, int i, String word2, int j, int[][] memos) {
        int m = word1.length(), n = word2.length();
        // j 已经走到末尾了，i 还有没走完的字母，全部删除
        if(j == n) return m-i;

        // j 没走到末尾，但是 i 走到末尾了，插入单词
        if(i == m) return n-j;

        if(memos[i][j] != -1) return memos[i][j];

        // 插入一个和 j 一样的字母，j 往下继续匹配
        int r1 = dfs_2(word1, i, word2, j+1, memos) + 1;
        // 删除 i 这个字母
        int r2 = dfs_2(word1, i+1, word2, j, memos) + 1;
        // 替换i这个字母让它和j一样
        int r3 = dfs_2(word1, i+1, word2, j+1, memos) + (word1.charAt(i) == word2.charAt(j) ? 0: 1);

        int res = Math.min(Math.min(r1, r2), r3);
        memos[i][j] = res;
        return res;
    }


    /**
     * Method 3: 递推 自顶向下  44%
     * */
    public static int minDistance_3(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i=0;i<dp.length;i++) {
            dp[i][n] = m-i;
        }

        for(int j=0;j<dp[0].length;j++) {
            dp[m][j] = n-j;
        }

        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {

//                int r1 = dfs_2(word1, i, word2, j+1, memos) + 1;
//                int r2 = dfs_2(word1, i+1, word2, j, memos) + 1;
//                int r3 = dfs_2(word1, i+1, word2, j+1, memos) + (word1.charAt(i) == word2.charAt(j) ? 0: 1);

                int r1 = dp[i][j+1]+1;
                int r2 = dp[i+1][j]+1;
                int r3 = dp[i+1][j+1] + (word1.charAt(i) == word2.charAt(j) ? 0: 1);
                dp[i][j] = Math.min(Math.min(r1, r2), r3);
            }
        }
        return dp[0][0];
    }

    /**
     * Method 4: 滚动数组
     * */
    public static int minDistance_4(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n+1];

        for(int j=0;j<n+1;j++) dp[j] = n-j;

        for(int i=m-1;i>=0;i--) {

            // 两个初始化都要放进来
            int[] ndp = new int[n+1];
            ndp[n] = m-i;

            for(int j=n-1;j>=0;j--) {
                int r1 = ndp[j+1]+1;
                int r2 = dp[j]+1;
                int r3 = dp[j+1] + (word1.charAt(i) == word2.charAt(j) ? 0: 1);
                ndp[j] = Math.min(Math.min(r1, r2), r3);
            }

            dp = ndp;
        }

        return dp[0];

    }


}
