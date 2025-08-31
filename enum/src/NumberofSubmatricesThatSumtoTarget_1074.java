import java.util.HashMap;

public class NumberofSubmatricesThatSumtoTarget_1074 {
    public static void main(String[] args) {
        int[][] matrix = {
                {0,1,0},
                {1,1,1},
                {0,1,0}
        };
        int target = 0;
        System.out.println(numSubmatrixSumTarget(matrix, target));
    }

    /**
     * Method 1: 暴力法
     * */
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m+1][n+1];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + matrix[i][j];
            }
        }

        int ans = 0;
        // 枚举右下角
        for(int r2=0;r2<m;r2++) {
            for(int c2=0;c2<n;c2++) {
                for(int r1=r2;r1>=0;r1--) {
                    for(int c1=c2;c1>=0;c1--) {
                        int temp = s[r2+1][c2+1] - s[r2+1][c1] - s[r1][c2+1] + s[r1][c1];
                        if(temp == target) ans++;
                    }
                }
            }
        }

        return ans;
    }


    public static int numSubmatrixSumTarget_2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m+1][n+1];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + matrix[i][j];
            }
        }

        int ans = 0;
        for(int top=1;top<=m;top++) {
            for(int bot=top;bot<=m;bot++) {
                HashMap<Integer, Integer> map = new HashMap<>();
                // 枚举列
                for(int col=1;col<=n;col++) {
                    int cur = s[bot][col] - s[top-1][col];
                    if(cur == target) ++ans;
                    ans += map.getOrDefault(cur-target, 0);

                    map.merge(cur, 1, Integer::sum);
                }
            }
        }
        return ans;
    }

}
