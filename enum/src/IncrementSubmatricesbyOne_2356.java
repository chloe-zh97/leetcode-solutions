import java.util.Arrays;

public class IncrementSubmatricesbyOne_2356 {
    public static void main(String[] args) {
        int n = 2;
        int[][] queries = {
                {0,0,1,1}
        };
        queries = rangeAddQueries(n, queries);
        for(int[] q:queries) {
            System.out.println(Arrays.toString(q));
        }
    }

    public static int[][] rangeAddQueries(int n, int[][] queries) {
        int m = queries.length;
        int[][] diff = new int[n+2][n+2];

        for(int i=0;i<m;i++) {
            int r1 = queries[i][0], c1 = queries[i][1], r2 = queries[i][2], c2 = queries[i][3];
            diff[r1+1][c1+1]++;
            diff[r1+1][c2+2]--;
            diff[r2+2][c1+1]--;
            diff[r2+2][c2+2]++;
        }

        int[][] ans = new int[n][n];
        // 还原
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                diff[i+1][j+1] += diff[i+1][j] + diff[i][j+1] - diff[i][j];
                ans[i][j] = diff[i+1][j+1];
            }
        }

        return ans;
    }
}
