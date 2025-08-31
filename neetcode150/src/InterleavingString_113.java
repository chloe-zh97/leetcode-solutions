import java.util.Arrays;

public class InterleavingString_113 {
    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(isInterleave(s1, s2, s3));
    }



    /**
     * Method 1: dfs 暴力法
     * */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), N = s3.length();
        if(m+n != N) return false;
        return dfs(s1, 0, s2, 0, s3);
    }

    /**
     * s1[i..n-1] 和 s2[j...n-1]是否可以构成
     * */
    private static boolean dfs(String s1, int i, String s2, int j, String s3) {
        // add terminal condition
        int m = s1.length(), n = s2.length();

        if(i == m && j == n) return true;

        boolean res = false;
        if(i < m && s1.charAt(i) == s3.charAt(i+j)) res = dfs(s1, i+1, s2, j, s3);

        if(!res && j < n && s2.charAt(j) == s3.charAt(i+j)) res = dfs(s1, i, s2, j+1, s3);

        return res;
    }


    /**
     * Method 2: 记忆化搜索
     * */
    public static boolean isInterleave_2(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), N = s3.length();
        if(m+n != N) return false;

        // 这里初始化的时候+1
        int[][] memos = new int[m+1][n+1];
        for(int[] mm: memos)
            Arrays.fill(mm, -1);

        return dfs_2(s1, 0, s2, 0, s3, memos);
    }

    private static boolean dfs_2(String s1, int i, String s2, int j, String s3, int[][] memos) {
        int m = s1.length(), n = s2.length();
        if(i == m && j == n) return true;

        if(memos[i][j] != -1) return memos[i][j] == 1;

        boolean res = false;

        if(i < m && s1.charAt(i) == s3.charAt(i+j)) res = dfs_2(s1, i+1, s2, j, s3, memos);

        if(!res && j < n && s2.charAt(j) == s3.charAt(i+j)) res = dfs_2(s1, i, s2, j+1, s3, memos);

        memos[i][j] = res ? 1: 0;

        return res;
    }

    /**
     * Method 3: 递推，自顶向下
     * */
    public static boolean isInterleave_3(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), N = s3.length();
        if(m+n != N) return false;

        boolean[][] dp = new boolean[m+1][n+1];
        dp[m][n] = true;

        // 这里从边界值开始计算
        for(int i=m;i>=0;i--) {
            for(int j=n;j>=0;j--) {
                // if(i < m && s1.charAt(i) == s3.charAt(i+j)) res = dfs_2(s1, i+1, s2, j, s3, memos);
                if(i<m && s1.charAt(i) == s3.charAt(i+j)) dp[i][j] = dp[i+1][j];
                if(j<n &&s2.charAt(j) == s3.charAt(i+j)) dp[i][j] = dp[i][j] || dp[i][j+1];
            }
        }

        return dp[0][0];
    }


    /**
     * Method 4: 空间压缩
     * */
    public static boolean isInterleave_4(String s1, String s2, String s3) {
        if(s1.length() > s2.length()) return isInterleave_4(s2, s1, s3);
        // 确保 s1 的长度 <= s2 的长度

        int m = s1.length(), n = s2.length(), N = s3.length();
        if(m+n != N) return false;

        boolean[] dp = new boolean[n+1];
        dp[n] = true;

        for(int i=m;i>=0;i--) {
            boolean[] newDP = new boolean[n+1];
            newDP[n] = true;

            // 如果第二个字符串长度为空，那么永远不会被更新到
            for(int j=n;j>=0;j--) {
//                if(i<m && s1.charAt(i) == s3.charAt(i+j)) dp[i][j] = dp[i+1][j];
//                if(j<n &&s2.charAt(j) == s3.charAt(i+j)) dp[i][j] = dp[i][j] || dp[i][j+1];
                if(i < m && s1.charAt(i) == s3.charAt(i+j)) newDP[j] = dp[j];
                if(j < n && s2.charAt(j) == s3.charAt(i+j)) newDP[j] = newDP[j] || newDP[j+1];
            }

            dp = newDP;

        }

        // System.out.println(Arrays.toString(dp));
        return dp[0];
    }

}
