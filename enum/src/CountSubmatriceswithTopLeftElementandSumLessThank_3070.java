public class CountSubmatriceswithTopLeftElementandSumLessThank_3070 {
    public static void main(String[] args) {
        int[][] grid = {
                {7,2,9},
                {1,5,0},
                {2,6,6}
        };
        int k = 20;
        System.out.println(countSubmatrices(grid, k));
    }

    public static int countSubmatrices(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        // preSum
        int[][] preSum = new int[m+1][n+1];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                preSum[i+1][j+1] = preSum[i+1][j] + preSum[i][j+1] - preSum[i][j] + grid[i][j];
            }
        }

        int ans = 0;
        // 枚举可能的右下端点
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                // 右下角端点(i,j)
                if(preSum[i+1][j+1] <= k) ans++;
            }
        }
        return ans;
    }
}
