import java.util.Arrays;

public class ValidParenthesisString_126 {
    public static void main(String[] args) {
        String s = "(*))";
        System.out.println(checkValidString_4(s));
    }


    /**
     * Method 1: Greedy, 用左变量统计
     * */
    public static boolean checkValidString(String s) {
        char[] chs = s.toCharArray();
        int n = s.length();

        int minL = 0, maxL = 0;
        for(int i=0;i<n;i++) {
            if(chs[i] == '(') {
                minL++;
                maxL++;
            } else if(chs[i] == ')') {
                minL--;
                maxL--;
            } else {
                minL++;
                maxL++;
            }
            if(maxL < 0) return false;
            if(minL < 0) minL = 0;
        }

        // 需要恰好是0
        return minL == 0;
    }


    /**
     * Method 2: dfs
     * */
    public static boolean checkValidString_2(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        int[][] memos = new int[n][n];
        for(int[] mm: memos)
            Arrays.fill(mm, -1);
        return dfs(chs, 0, 0, memos);
    }

    /**
     * chs[i..n-1] 是否是一个合理串
     * */
    private static boolean dfs(char[] chs, int i, int open, int[][] memos) {
       int n = chs.length;

       if(open < 0) return false;
       if(i == n) return open == 0;
       // 任何时候，如果 open < 0, 直接返回 false

       if(memos[i][open] != -1) return memos[i][open] == 1;

       boolean res = false;

       if(chs[i] == '(') {
           res = dfs(chs, i+1, open+1, memos);
       } else if(chs[i] == ')') {
           res = dfs(chs, i+1, open-1, memos);
       } else {
           res = dfs(chs, i+1, open, memos)
                   || dfs(chs, i+1, open-1, memos)
                   || dfs(chs,i+1, open+1, memos);
       }

       memos[i][open] = res ? 1: 0;
       return res;
    }

    /**
     * Method 3: dp
     * */
    public static boolean checkValidString_3(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();

        boolean[][] dp = new boolean[n+1][n+1];
        dp[n][0] = true;

        for(int i=n-1;i>=0;i--) {
            for(int j=0;j<n;j++) {
                if(chs[i] == '(') {
                    dp[i][j] |= dp[i+1][j+1];
                } else if(chs[i] == ')') {
                    if(j > 0) dp[i][j] |= dp[i+1][j-1];
                } else {
                    dp[i][j] |= dp[i+1][j];
                    dp[i][j] |= dp[i+1][j+1];
                    if(j > 0) dp[i][j] |= dp[i+1][j-1];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * Method 4: 滚动数组
     * */
    public static boolean checkValidString_4(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();

        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for(int i=n-1;i>=0;i--) {
            boolean[] newdp = new boolean[n+1];

            for(int j=0;j<n;j++) {
                boolean res = false;
                // i+1 是旧的
                if(chs[i] == '(') {
                    res |= dp[j+1];
                } else if(chs[i] == ')') {
                    if(j > 0) res |= dp[j-1];
                } else {
                    res |= dp[j];
                    res |= dp[j+1];
                    if(j > 0) res |= dp[j-1];
                }
                newdp[j] =res;
            }

            dp = newdp;
        }
        return dp[0];
    }


}
