import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumDifferenceScoreinaGrid_3148 {
    public static void main(String[] args) {
        int[][] grid = {
                {9,5,7,3},
                {8,9,6,1},
                {6,7,14,3},
                {2,5,3,1}
        };
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<grid.length;i++) {
            List<Integer> row = new ArrayList<>();
            for(int j=0;j<grid[0].length;j++) {
                row.add(grid[i][j]);
            }
            list.add(row);
        }
        System.out.println(maxScore(list));
    }

    /**
     * Method 1: 动态规划
     * dp[i+1][j+1]: 以(i,j) 为右下角，左上角的最小值
     * */
    public static int maxScore(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] dp = new int[m+1][n+1];

        // initial dp
        for(int i=0;i<dp.length;i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        int ans = Integer.MIN_VALUE;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                int pre = Math.min(dp[i+1][j], dp[i][j+1]);
                ans = Math.max(ans, grid.get(i).get(j) - pre);
                dp[i+1][j+1] = Math.min(pre, grid.get(i).get(j));
            }
        }
        return ans;
    }
}
