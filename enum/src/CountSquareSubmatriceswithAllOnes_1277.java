public class CountSquareSubmatriceswithAllOnes_1277 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,0,1},
                {1,1,0},
                {1,1,0}
        };
        System.out.println(countSquares_2(matrix));
    }

    public static int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int[][] s = new int[m+1][n+1];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++)
                s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + matrix[i][j];
        }

        int ans = 0;
        // 枚举可能的 k 值
        for(int k=Math.min(m,n);k>0;k--) {
            for(int i=0;i<=m-k;i++) {
                for(int j=0;j<=n-k;j++) {
                    int r1 = i, c1 = j, r2 = i+k-1, c2 = j+k-1;
                    int temp = s[r2+1][c2+1] - s[r2+1][c1] - s[r1][c2+1] + s[r1][c1];
                    if(temp == k*k) ans++;
                }
            }
        }
        return ans;
    }

    /**
     * Method 2: dp[i][j]: 表示以(i,j)为右下角端点能取到的最大的正方形边长
     * dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])+1
     * */
    public static int countSquares_2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int ans = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j] == 0) continue;
                else if(i==0 || j==0) ans++;
                else {
                    matrix[i][j] = Math.min(Math.min(matrix[i-1][j], matrix[i][j-1]), matrix[i-1][j-1]) + 1;
                    ans += matrix[i][j];
                }
            }
        }
        return ans;
    }
}
