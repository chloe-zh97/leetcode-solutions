import java.util.Arrays;

public class CountBits_143 {
    public static void main(String[] args) {
        int n = 4;
        int[] ans = countBits(n);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * Method 1: dp
     * */
    public static int[] countBits(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i=1;i<=n;i++) {
            // 2 ^ mi = i
            double mi = Math.log(i) / Math.log(2);

            // 当前这个数是 2 的倍数
            if(mi * mi == i) {
                dp[i] = 1;
            } else {
                int offset = (int) Math.pow(2, (int)mi);
                dp[i] = 1 + dp[i-offset];
            }
        }

        return dp;
    }

    /**
     * Method 2: dp[i] = dp[i-offset]
     * 当 i 要达到下一个2 的倍数时，更新
     * */
    public static int[] countBits_2(int n) {
        int offset = 1;
        int[] dp = new int[n+1];
        for(int i=1;i<=n;i++) {
            if(offset * 2 == i) {
                offset = i;
            }
            dp[i] = 1 + dp[i-offset];
        }
        return dp;
    }

    /**
     * Method 3: dp optimal
     * 0: 0 0 0 0
     * 1: 0 0 0 1
     * 2: 0 0 1 0
     * 3: 0 0 1 1
     * 4: 0 1 0 0
     * 5: 0 1 0 1
     * 6: 0 1 1 0
     * 7: 0 1 1 1
     * */

}
