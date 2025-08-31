import java.util.TreeSet;

public class MaxSumofRectangleNoLargerThanK_363 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,0,1},
                {0,-2,3}
        };
        int k = 2;
        System.out.println(maxSumSubmatrix_2(matrix, k));
//        TreeSet<Integer> set = new TreeSet<>();
//        set.add(9);
//        set.add(2);
//        set.add(4);
//        System.out.println(set.ceiling(3));
    }

    /**
     * Method 1: 前缀和+暴力
     * */
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m+1][n+1];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + matrix[i][j];
            }
        }

        int ans = Integer.MIN_VALUE;
        // 枚举右下角和左上角
        for(int r2=0;r2<m;r2++) {
            for(int c2=0;c2<n;c2++) {
                for(int r1=0;r1<=r2;r1++) {
                    for(int c1=0;c1<=c2;c1++) {
                        int temp = s[r2+1][c2+1] - s[r2+1][c1] - s[r1][c2+1] + s[r1][c1];
                        if(temp <= k) ans = Math.max(ans, temp);
                    }
                }
            }
         }
        return ans;
    }

    /**
     * Method 2: 枚举三条边
     * */
    public static int maxSumSubmatrix_2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m+1][n+1];

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + matrix[i][j];
            }
        }

        int ans = Integer.MIN_VALUE;
        for(int top=1;top<=m;top++) {
            for(int bot=top;bot<=m;bot++) {
                // 保存前缀和
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);

                for(int r=1;r<=n;r++) {
                    // 5，k=2
                    // 比3大的元素，越接近3越好
                    int right = s[bot][r] - s[top-1][r];
                    Integer left = set.ceiling(right-k);

                    if(left != null) {
                        ans = Math.max(ans, right-left);
                    }
                    set.add(right);
                }
            }
        }
        return ans;
    }
}
