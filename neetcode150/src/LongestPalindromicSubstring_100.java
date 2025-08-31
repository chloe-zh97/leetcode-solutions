import java.util.Arrays;

public class LongestPalindromicSubstring_100 {
    public static void main(String[] args) {
        String s = "abbc";
        System.out.println(longestPalindrome_2(s));
    }

    /**
     * Method 1: two pointer
     * 指针从中间开始向两边扩展，区分奇数和偶数的情况
     * */
    public static String longestPalindrome(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();

        int maxLen = 0, start = -1, end = -1;
        for(int i=0;i<n;i++) {
            int left = i, right = i;
            while(left >= 0 && right < n && chs[left] == chs[right]) {
                if(maxLen < right-left+1) {
                    maxLen = right-left+1;
                    start = left;
                    end = right;
                }

                left--;
                right++;
            }

            // even
            left = i-1; right = i;
            while(left >= 0 && right < n && chs[left] == chs[right]) {
                if(maxLen < right-left+1) {
                    maxLen = right-left+1;
                    start = left;
                    end = right;
                }

                left--;
                right++;
            }
        }

        return s.substring(start, end+1);
    }


    /**
     * Method 2: dp
     * boolean[][] dp: dp[i][j] 表示 chs[i...j] 是否是回文子串
     * */
    public static String longestPalindrome_2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for(int i=0;i<n;i++) dp[i][i] = true;

        char[] chs = s.toCharArray();

        int maxLen = 0, start = -1, end = -1;

        // dp[i][j] 从 dp[i+1][j-1] 推演而来，所以 i 逆序， j 顺序
        for(int i=n-1;i>=0;i--) {
            for(int j=i;j<n;j++) {
                // [i,j] 的长度小于等于1
                if(chs[i] == chs[j] && (j-i<=2 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    // 新的回文子串长度
                    if(maxLen < j-i+1) {
                        maxLen = j-i+1;
                        start = i;
                        end = j;
                    }
                }
            }
        }

        return s.substring(start, end+1);
    }

}
