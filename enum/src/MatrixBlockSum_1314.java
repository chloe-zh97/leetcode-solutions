public class MatrixBlockSum_1314 {
    public static void main(String[] args) {
        int[][] mat = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        int k = 2;
        mat = matrixBlockSum(mat, k);
        for(int i=0;i<mat.length;i++) {
            for(int j=0;j<mat[0].length;j++) {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] preSum = new int[m+1][n+1];

        // preSum
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                preSum[i+1][j+1] = preSum[i+1][j] + preSum[i][j+1] - preSum[i][j] + mat[i][j];
            }
        }

        int[][] ans = new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                int r1 = Math.max(i-k, 0);
                int c1 = Math.max(j-k, 0);
                int r2 = Math.min(i+k, m-1);
                int c2 = Math.min(j+k, n-1);

                ans[i][j] = preSum[r2+1][c2+1] - preSum[r2+1][c1] - preSum[r1][c2+1] + preSum[r1][c1];
            }
        }

        return ans;

    }

}
