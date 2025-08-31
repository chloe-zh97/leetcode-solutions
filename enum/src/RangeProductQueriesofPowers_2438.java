import java.util.Arrays;

public class RangeProductQueriesofPowers_2438 {
    public static void main(String[] args) {
        int n = 15;
        int[][] queries = {
                {0,1},
                {2,2},
                {0,3}
        };
        int[] ans = productQueries(n, queries);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * Method 1: 预处理
     * */
    public static int[] productQueries(int n, int[][] queries) {
       int[] power = new int[Integer.bitCount(n)];
       final int mod = (int)1e9+7;

       int n1 = 0;
       while(n > 0) {
           int t = n & (-n);
           n -= t;
           power[n1++] = t;
       }

       int m = queries.length;
       int[] ans = new int[m];

       // 注意溢出
       long[][] res = new long[n1][n1];
       for(int i=0;i<power.length;i++) {
           res[i][i] = power[i];
           for(int j=i+1;j<power.length;j++) {
               res[i][j] = (res[i][j-1] * power[j]) % mod;
           }
       }

       for(int i=0;i<m;i++) {
           ans[i] = (int) res[queries[i][0]][queries[i][1]];
       }
        return ans;
    }
}
