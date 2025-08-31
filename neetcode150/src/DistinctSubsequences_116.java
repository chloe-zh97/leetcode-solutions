import java.util.Arrays;

public class DistinctSubsequences_116 {
    public static void main(String[] args) {
        String s = "xxyxy";
        String t = "xy";
        System.out.println(numDistinct_6(s, t));
    }

    /**
     * s 选一些字母，能组成 t 的方案数
     * */
    public static int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        if(n > m) return 0;

        return dfs(s, 0, t, 0);
    }

    /**
     * s[i..n-1] 中能组成 t[j...n-1] 的种类数
     * */
    private static int dfs(String s, int i, String t, int j) {
        int m = s.length(), n = t.length();
        if(j == n) return 1;
        if(i == m) return 0;

        // 不选
        int res = dfs(s, i+1, t, j);

        if(s.charAt(i) == t.charAt(j)) {
            // 选
            res += dfs(s, i+1, t, j+1);
        }

        return res;
    }


    /**
     * Method 2: 改记忆化搜索
     * */
    public static int numDistinct_2(String s, String t) {
        int m = s.length(), n = t.length();
        if(n > m) return 0;

        int[][] memos = new int[m][n];
        for(int[] mm : memos) {
            Arrays.fill(mm, -1);
        }

        return dfs_2(s, 0, t, 0, memos);
    }

    private static int dfs_2(String s, int i, String t, int j, int[][] memos) {
        int m = s.length(), n = t.length();
        if(j == n) return 1;
        if(i == m) return 0;

        if(memos[i][j] != -1) return memos[i][j];

        // 不选
        int res = dfs_2(s, i+1, t, j, memos);

        if(s.charAt(i) == t.charAt(j)) {
            // 选
            res += dfs_2(s, i+1, t, j+1, memos);
        }

        memos[i][j] = res;
        return res;
    }


    /**
     * Method 3: 递推
     * */
    public static int numDistinct_3(String s, String t) {
        int m = s.length(), n = t.length();
        if(n > m) return 0;

        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<dp.length;i++)
            dp[i][n] = 1;

        // 自顶向下
        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {
                dp[i][j] = dp[i+1][j];
                if(s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i+1][j+1];
                }
            }
        }

//        for(int i=0;i<dp.length;i++) {
//            for(int j=0;j<dp[0].length;j++) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        return dp[0][0];
    }


    /**
     * Method 4: 自底向上
     * */
    public static int numDistinct_4(String s, String t) {
        int m = s.length(), n = t.length();
        if(n > m) return 0;

        // dp[i][j]: s[0...i-1] 中能组成 t[0...j-1] 的方案数
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<dp.length;i++)
            dp[i][0] = 1;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                // 不选当前这个字母匹配
//                dp[i+1][j+1] = dp[i][j+1];
//                if(s.charAt(i) == t.charAt(j)) {
//                    dp[i+1][j+1] += dp[i][j];
//                }
//
                dp[i+1][j+1] = dp[i][j+1] + (s.charAt(i) == t.charAt(j) ? dp[i][j] : 0);
            }
        }

        return dp[m][n];
    }


    /**
     * Method 5: 空间压缩 滚动数组
     * */
    public static int numDistinct_5(String s, String t) {
        int m = s.length(), n = t.length();
        if(n > m) return 0;

        int[] dp = new int[n+1];
        dp[0] = 1;

        for(int i=0;i<m;i++) {
            int[] newDp = new int[n+1];
            newDp[0] = 1;

            for(int j=0;j<n;j++) {
                // i+1 对应新，i 对应旧
                // dp[i+1][j+1] = dp[i][j+1] + (s.charAt(i) == t.charAt(j) ? dp[i][j] : 0);
                newDp[j+1] = dp[j+1] + (s.charAt(i) == t.charAt(j) ? dp[j] : 0);
            }

            dp = newDp;
        }

        return dp[n];
    }

    /**
     * Method 6: 单个数组
     * */
    public static int numDistinct_6(String s, String t) {
        int m = s.length(), n = t.length();
        if(n > m) return 0;

        int[] dp = new int[n+1];
        dp[0] = 1;

        for(int i=0;i<m;i++) {
            // j 必须倒序遍历
            for(int j=n-1;j>=0;j--) {
                 dp[j+1] = dp[j+1] + (s.charAt(i) == t.charAt(j) ? dp[j] : 0);
            }
        }
        return dp[n];
    }

}
