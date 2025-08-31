import java.util.Arrays;
import java.util.TreeSet;

public class GetBiggestThreeRhombusSumsinaGrid_1878 {
    public static void main(String[] args) {
        int[][] grid = {
                {20,17,9,13,5,2,9,1,5},
                {14,9,9,9,16,18,3,4,12},
                {18,15,10,20,19,20,15,12,11},
                {19,16,19,18,8,13,15,14,11},
                {4,19,5,2,19,17,7,2,2}
        };
        int[] ans = getBiggestThree(grid);
        System.out.println(Arrays.toString(ans));
    }

    public static int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // s1 左上 preSum, s2 右上 preSum
        int[][] s1 = new int[m+5][n+5];
        int[][] s2 = new int[m+5][n+5];

        // 往左上走
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                s1[i][j] = s1[i-1][j-1] + grid[i-1][j-1];
                s2[i][j] = s2[i-1][j+1] + grid[i-1][j-1];
            }
        }

        TreeSet<Integer> set = new TreeSet<>();
        // 枚举最上方的定点，以及菱形宽度的一半

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                // 单独的一个点
                set.add(grid[i][j]);

                // 枚举可能的 k 的值，k 代表一半宽度
                for(int k=1;i+2*k<m && j-k>=0 && j+k<n;k++) {
                    // System.out.println("i="+i+" j="+j+" k="+k);
                    // 计算四个顶点
                    int p1 = grid[i][j];
                    int p2 = grid[i+k][j-k];
                    int p3 = grid[i+2*k][j];
                    int p4 = grid[i+k][j+k];

                    // 四个方向不包括顶点
                    int d1 = s2[i+k][j-k] - s2[i+1][j-1];
                    int d2 = s1[i+2*k][j] - s1[i+k+1][j-k+1];
                    int d3 = s2[i+2*k][j] - s2[i+k+1][j+k-1];
                    int d4 = s1[i+k][j+k] - s1[i+1][j+1];
                    set.add(p1+p2+p3+p4+d1+d2+d3+d4);
                }
                // 只维护最大的3个
                while(set.size() > 3) set.pollFirst();
            }
        }

        int[] ans = new int[set.size()];
        int k = 0;
        while(!set.isEmpty()) ans[k++] = set.pollLast();
        return ans;
    }
}
