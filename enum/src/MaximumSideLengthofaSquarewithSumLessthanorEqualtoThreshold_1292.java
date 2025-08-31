public class MaximumSideLengthofaSquarewithSumLessthanorEqualtoThreshold_1292 {
    public static void main(String[] args) {
        int[][] mat = {
                {2,2,2,2,2},
                {2,2,2,2,2},
                {2,2,2,2,2},
                {2,2,2,2,2},
                {2,2,2,2,2}
        };
        int threshold = 1;
        System.out.println(maxSideLength_2(mat, threshold));
    }

    public static int maxSideLength_2(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        // preSum
        int[][] s = new int[m+1][n+1];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++)
                s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + mat[i][j];
        }

        // binary search
        int left = 0, right = Math.min(m,n);
        while(left < right) {
            int mid = left + right + 1 >> 1;
            if(check(s, mid, threshold)) left = mid;
            else right = mid - 1;
        }
        return left;
    }

    /**
     * 前缀和 s 中是否存在变长为 k 的正方形，sum <= threshold
     * */
    private static boolean check(int[][] s, int k, int threshold) {
        int m = s.length-1, n = s[0].length-1;
        for(int i=0;i<=m-k;i++) {
            for(int j=0;j<=n-k;j++) {
                int r1 = i, c1 = j, r2 = i+k-1, c2 = j+k-1;
                int temp = s[r2+1][c2+1] - s[r2+1][c1] - s[r1][c2+1] + s[r1][c1];
                if(temp <= threshold) return true;
            }
        }
        return false;
    }


    /**
     * Method 1: 模拟 34%
     * */
    public static int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        // preSum
        int[][] s = new int[m+1][n+1];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++)
                s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + mat[i][j];
        }

        // 枚举 k 的值
        for(int k=Math.min(m,n);k>0;k--) {
            // 枚举左上端点
            for(int i=0;i<=m-k;i++) {
                for(int j=0;j<=n-k;j++) {
                    int r1 = i, c1 = j, r2 = i+k-1, c2 = j+k-1;
                    int temp = s[r2+1][c2+1] - s[r2+1][c1] - s[r1][c2+1] + s[r1][c1];
                    if(temp <= threshold) return k;
                }
            }
        }
        return 0;
    }
}
