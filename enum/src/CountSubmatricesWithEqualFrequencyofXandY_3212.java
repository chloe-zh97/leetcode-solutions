public class CountSubmatricesWithEqualFrequencyofXandY_3212 {
    public static void main(String[] args) {
        char[][] grid = {
                {'.', '.'},
                {'.', '.'}
        };
        System.out.println(numberOfSubmatrices(grid));
    }

    public static int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] s = new int[m+1][n+1];

        // exist[i+1][j+1]: grid[0][0] - grid[i][j] 范围内是否有 X
        boolean[][] exist = new boolean[m+1][n+1];

        int ans = 0;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                int v = 0;
                if(grid[i][j] == 'X') v = 1;
                else if(grid[i][j] == 'Y') v = -1;

                // 计算频率
                s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + v;

                exist[i+1][j+1] = exist[i+1][j] || exist[i][j+1] || v == 1;

                if(s[i+1][j+1] == 0 && exist[i+1][j+1]) ans++;
            }
        }
        return ans;
    }
}
