import java.util.Arrays;

public class RegularExpressionMatching_119 {
    public static void main(String[] args) {
        String s = "xyz";
        String p = ".*z";
        System.out.println(isMatch_5(s, p));
    }

    /**
     * Method 1: 暴力法
     * */
    public static boolean isMatch(String s, String p) {
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        return dfs(ss, 0, pp, 0);
    }

    private static boolean dfs(char[] s, int i, char[] p, int j) {
        int m = s.length, n = p.length;

        // p 已经没有了，但是还剩字符没有匹配
        if(j == n) return i == m;

        // 到这里，j < n, i 是否还有没有不确定
        boolean isMatch = i<m && (p[j] == '.' || s[i] == p[j]);

        if(j+1 < n && p[j+1] == '*') {
            // 选j这个字符，那必须要匹配
            return  (isMatch && dfs(s, i+1, p, j)) || dfs(s, i, p, j+2);
        }

        return isMatch && dfs(s,i+1, p, j+1);
    }


    /**
     * Method 2: 记忆化搜索
     * */
    public static boolean isMatch_2(String s, String p) {
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        int m = s.length(), n = p.length();
        int[][] memos = new int[m+1][n+1];
        for(int[] mm: memos)
            Arrays.fill(mm, -1);

        return dfs_2(ss, 0, pp, 0, memos);
    }

    private static boolean dfs_2(char[] s, int i, char[] p, int j, int[][] memos) {
        int m = s.length, n = p.length;

        if(j == n) return i == m;

        if(memos[i][j] != -1) return memos[i][j] == 1;

        boolean res;
        boolean isMatch = i < m && (p[j] == '.' || s[i] == p[j]);
        if(j+1 < n && p[j+1] == '*') {
            res = isMatch && dfs_2(s, i+1, p, j, memos) || dfs_2(s, i, p, j+2, memos);
        } else {
            res = isMatch && dfs_2(s, i+1, p, j+1, memos);
        }

        memos[i][j] = res ? 1: 0;
        return res;
    }

    /**
     * Method 3: 递推 自底向上
     * */
    public static boolean isMatch_3(String s, String p) {
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        int m = s.length(), n = p.length();

        // dp[i][j]: s[0..i-1] 和 t[0..j-1]能否匹配
        boolean[][] dp = new boolean[m+1][n+1];
        dp[m][n] = true;

        // 这里为什么是 i=m?
        for(int i=m;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {
                boolean isMatch = i<m && (pp[j] == '.' || ss[i] == pp[j]);

                if(j+1 < n && pp[j+1] == '*') {
                   // res = isMatch && dfs_2(s, i+1, p, j, memos) || dfs_2(s, i, p, j+2, memos);
                    dp[i][j] = isMatch && dp[i+1][j] || dp[i][j+2];
                } else {
                    dp[i][j] = isMatch && dp[i+1][j+1];
                }
            }
        }

        return dp[0][0];
    }


    /**
     * Method 4: 滚动数组
     * */
    public static boolean isMatch_4(String s, String p) {
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        int m = s.length(), n = p.length();

        boolean[] dp = new boolean[n+1];
        dp[n] = true;

        for(int i=m;i>=0;i--) {
            boolean[] newdp = new boolean[n+1];
            newdp[n] = i==m;

            for(int j=n-1;j>=0;j--) {
                boolean isMatch = i<m && (pp[j] == '.' || ss[i] == pp[j]);
                if(j+1 < n && pp[j+1] == '*') {
                    // i+1 旧， i 新的
                    // dp[i][j] = isMatch && dp[i+1][j] || dp[i][j+2];
                    newdp[j] = isMatch && dp[j] || newdp[j+2];
                } else {
                    // dp[i][j] = isMatch && dp[i+1][j+1];
                    newdp[j] = isMatch && dp[j+1];
                }
            }
            dp = newdp;
        }
        return dp[0];
    }

    /**
     * Method 5: 单个数组
     * */
    public static boolean isMatch_5(String s, String p) {
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        int m = s.length(), n = p.length();

        boolean[] dp = new boolean[n+1];
        dp[n] = true;

        for(int i=m;i>=0;i--) {
            // 不管 i 是多少 dp[n]
            boolean pre = dp[n];
            dp[n] = i==m;

            for(int j=n-1;j>=0;j--) {
                boolean isMatch = i<m && (pp[j] == '.' || ss[i] == pp[j]);
                boolean tmp;
                if(j+1 < n && pp[j+1] == '*') {
                   //  newdp[j] = isMatch && dp[j] || newdp[j+2];
                    tmp = isMatch && dp[j] || dp[j+2];
                } else {
                    // newdp[j] = isMatch && dp[j+1];
                    tmp = isMatch && pre;
                }
                pre = dp[j];
                dp[j] = tmp;
            }
        }
        return dp[0];
    }
}
