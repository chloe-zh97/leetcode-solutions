public class CountSubmatricesWithAllOnes_1504 {
    public static void main(String[] args) {
        int[][] mat = {
                {1,0,1},
                {1,1,0},
                {1,1,0}
        };
        System.out.println(numSubmat(mat));
    }

    /**
     * Method 1: dp
     * */
    public static int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        // dp[i][j]: 以 (i,j) 为右端点，向左延伸，最多有多少个连续的1
        int[][] dp = new int[m][n];

        int ans = 0;
        // 统计 dp, 1*1， 1*2， 1*3 的情况
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(j == 0) dp[i][j] = mat[i][j];
                else dp[i][j] = mat[i][j] == 1 ? dp[i][j-1] + 1 : 0;
            }
        }

        // 枚举每一个可能的 (i,j)
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                // 统计以 (i,j) 为右下角的情况
                int min = Integer.MAX_VALUE;
                for(int k=i;k>=0;k--) {
                    min = Math.min(min, dp[k][j]);
                    ans += min;
                    if(min == 0) break;
                }
            }
        }
        return ans;
    }
}
