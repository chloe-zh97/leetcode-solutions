public class RightTriangles_3128 {
    public static void main(String[] args) {
        int[][] grid = {
                {1,0,1},
                {1,0,0},
                {1,0,0}
        };
        System.out.println(numberOfRightTriangles(grid));
    }
    /**
     * Method 1: 暴力法
     * */
    public static long numberOfRightTriangles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 每一行有多少个1
        int[] row = new int[m];
        int[] col = new int[n];

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }

        long ans = 0L;
        // 遍历每个位置 (i,j)
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == 1) {
                    int r = row[i] - 1;
                    int c = col[j] - 1;
                    ans += (long)r * c;
                }
            }
        }
        return ans;
    }

}
