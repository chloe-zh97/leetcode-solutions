public class MaximalSquare_221 {
    public static void main(String[] args) {
        char[][] matrix = {
                {'0'}
        };
        System.out.println(maximalSquare_2(matrix));
    }

    /**
     * Method 2: 二分
     * */
    public static int maximalSquare_2(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // preSum
        int[][] s = new int[m+1][n+1];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + (matrix[i][j] == '1' ? 1: 0);
            }
        }

        int left = 0, right = Math.min(m,n);
        while(left < right) {
            int mid = left + right + 1 >> 1;
            if(check(s, mid)) left = mid;
            else right = mid - 1;
        }
        return left * left;
    }

    private static boolean check(int[][] s, int k) {
        int m = s.length-1, n = s[0].length-1;
        for(int i=0;i<=m-k;i++) {
            for(int j=0;j<=n-k;j++) {
                int r1 = i, c1 = j, r2 = i+k-1, c2 = j+k-1;
                int temp = s[r2+1][c2+1] - s[r2+1][c1] - s[r1][c2+1] + s[r1][c1];
                if(temp == k * k) return true;
            }
        }
        return false;
    }

    /**
     * Method 1: 前缀和+暴力法 11%
     * */
    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // preSum
        int[][] s = new int[m+1][n+1];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + (matrix[i][j] == '1' ? 1: 0);
            }
        }

        // 枚举可能的正方形边长
        for(int k=Math.min(m,n);k>0;k--) {
            for(int i=0;i<=m-k;i++) {
                for(int j=0;j<=n-k;j++) {
                    int r1 = i, c1 = j, r2 = i+k-1, c2 = j+k-1;
                    int temp = s[r2+1][c2+1] - s[r2+1][c1] - s[r1][c2+1] + s[r1][c1];
                    if(temp == k * k) return k*k;
                }
            }
        }
        return 0;
    }
}
