public class RangeSumQuery2DImmutable_304 {
    public static void main(String[] args) {

    }

    static class NumMatrix {

        private int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            preSum = new int[m+1][n+1];

            for(int i=1;i<preSum.length;i++) {
                for(int j=1;j<preSum[0].length;j++) {
                    // matrix[0][0] 到 matrix[i-1][j-1]
                    preSum[i][j] = preSum[i][j-1] + preSum[i-1][j] - preSum[i-1][j-1] + matrix[i-1][j-1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2+1][col2+1] - preSum[row2+1][col1] - preSum[row1][col2+1] + preSum[row1][col1];
        }
    }
}
