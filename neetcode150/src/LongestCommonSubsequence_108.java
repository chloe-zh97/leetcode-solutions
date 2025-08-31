import java.util.Arrays;

public class LongestCommonSubsequence_108 {
    public static void main(String[] args) {
        String text1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String text2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(longestCommonSubsequence_5(text1, text2));
    }

    /**
     * Method 1: 暴力法 超时
     * */
//    public static int longestCommonSubsequence(String text1, String text2) {
//        if(text1.length() > text2.length()) return dfs(text2, 0, text1, 0);
//
//        // 在 text2[j..n-1]中最长的公共子串
//        // 等价于 text2[1j+1...n-1]的最长公共子串
//        return dfs(text1, 0, text2, 0);
//    }
//
//
//    /**
//     * text2[j...n-1]中最长公共子串长度
//     * */
//    private static int dfs(String text1, int i, String text2, int j) {
//        if(i >= text1.length()) return 0;
//
//        if(j >= text2.length()) return 0;
//
//        // 不匹配
//        int res = Math.max(dfs(text1, i, text2, j+1), dfs(text1, i+1, text2, j));
//
//        // 匹配
//        if(text1.charAt(i) == text2.charAt(j)) {
//            // 根据贪心思想，越早匹配越好
//            res = Math.max(res, dfs(text1, i+1, text2, j+1)+1);
//        }
//        return res;
//    }


    /**
     * Method 2: 记忆化搜索
     * */
    public static int longestCommonSubsequence_2(String text1, String text2) {
        if(text1.length() > text2.length()) return longestCommonSubsequence_2(text2, text1);

        // if(text2.contains(text1)) return text1.length();

        int m = text1.length(), n = text2.length();
        int[][] memos = new int[m][n];
        for(int[] mm: memos) {
            Arrays.fill(mm, -1);
        }

        return dfs_2(text1, 0, text2, 0, memos);
    }

    private static int dfs_2(String text1, int i, String text2, int j, int[][] memos) {
        if(i >= text1.length()) return 0;

        if(j >= text2.length()) return 0;

        if(memos[i][j] != -1) return memos[i][j];

        int res = Math.max(dfs_2(text1, i, text2, j+1, memos), dfs_2(text1, i+1, text2, j, memos));

        // 匹配
        if(text1.charAt(i) == text2.charAt(j)) {
            // 根据贪心思想，越早匹配越好
            res = Math.max(res, dfs_2(text1, i+1, text2, j+1, memos)+1);
        }

        memos[i][j] = res;
        return res;

    }

    /**
     * Method 3: 改递推
     * dp[i][j] = dp[i][j+1], dp[i+1][j]
     * int res = Math.max(dfs_2(text1, i, text2, j+1, memos), dfs_2(text1, i+1, text2, j, memos));
     * */
    public static int longestCommonSubsequence_3(String text1, String text2) {
        if(text1.length() > text2.length()) return longestCommonSubsequence_3(text2,text1);

        int m = text1.length(), n = text2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {
                // 不匹配
                dp[i][j] = Math.max(dp[i][j+1], dp[i+1][j]);
                if(text1.charAt(i) == text2.charAt(j)) {
                    // res = Math.max(res, dfs_2(text1, i+1, text2, j+1, memos)+1);
                    dp[i][j] = Math.max(dp[i][j], dp[i+1][j+1]+1);
                }
            }
        }
        return dp[0][0];
    }

    /**
     * Method 4: 压缩空间
     * */
    public static int longestCommonSubsequence_4(String text1, String text2) {
        if (text1.length() > text2.length()) return longestCommonSubsequence_4(text2, text1);

        int m = text1.length(), n = text2.length();

        // 从底下开始推上面
        int[] dp = new int[n+1];

        for (int i = m - 1; i >= 0; i--) {
            int[] newdp = new int[n+1];
            for (int j = n - 1; j >= 0; j--) {
                newdp[j] = Math.max(newdp[j+1], dp[j]);
                if(text1.charAt(i) == text2.charAt(j)) {
                    newdp[j] = Math.max(newdp[j], dp[j+1]+1);
                }
            }

            dp = newdp;
        }

        return dp[0];
    }

    /**
     * Method 5: 优化，变成一个数组
     * */
    public static int longestCommonSubsequence_5(String text1, String text2) {
        if (text1.length() > text2.length()) return longestCommonSubsequence_4(text2, text1);

        int m = text1.length(), n = text2.length();

        // 从底下开始推上面
        int[] dp = new int[n+1];

        for(int i=m-1;i>=0;i--) {
            int prev = 0;

            for(int j=n-1;j>=0;j--) {
                int tmp = dp[j];

                if(text1.charAt(i) == text2.charAt(j)) {
                    dp[j] = Math.max(tmp, prev+1);
                } else {
                    // newdp[j] = Math.max(newdp[j+1], dp[j]);
                    dp[j] = Math.max(dp[j+1], tmp);
                }
                prev = tmp;
            }
        }

        return dp[0];
    }

}
