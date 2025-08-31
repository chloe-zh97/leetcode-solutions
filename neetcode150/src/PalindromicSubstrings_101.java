public class PalindromicSubstrings_101 {
    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(countSubstrings_2(s));
    }


    /**
     * Method 1: dp
     * boolean[][] dp, dp[i][j]: 是否为回文
     * */
    public static int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int ans = 0;
        char[] chs = s.toCharArray();

        // dp[i][j] 由 dp[i+1][j-1] 推理而来
        for(int i=n-1;i>=0;i--) {
            for(int j=i;j<n;j++) {
                // j 需要比 i 大
                if(chs[i] == chs[j] && (j-i<=2 || dp[i+1][j-1])) {
                    ans += 1;
                    dp[i][j] = true;
                }
            }
        }
        return ans;
    }

    /**
     * Method 2: 中心扩散
     * */
    public static int countSubstrings_2(String s) {
        int n = s.length();

        char[] chs = s.toCharArray();
        int ans = 0;
        for(int i=0;i<n;i++) {
            int left = i, right = i;
            while(left > -1 && right < n && chs[left] == chs[right]) {
                ans++;
               // System.out.println("left="+left+" right="+right);
                left--;
                right++;
            }

            left = i; right = i+1;
            while(left > -1 && right < n && chs[left]==chs[right]) {
                ans++;
               // System.out.println("left="+left+" right="+right);
                left--;
                right++;
            }
        }
        return ans;
    }
}
