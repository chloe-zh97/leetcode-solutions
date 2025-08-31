import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix_54 {
    public static void main(String[] args) {
        int[][] matrix = {
                {7},
                {9},
                {6}
        };
        List<Integer> ans = spiralOrder_2(matrix);
        System.out.println(ans);
    }

    /**
     * Method 1: 一圈圈处理
     * */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;

        int i = 0, j = 0, dx = m, dy = n;

        while(dx >= 1 && dy >= 1) {
            // 从 (i,j) 位置开始, 长为 dx, 宽为 dy 的矩形
            rotateMatrix(matrix, i,j, dx, dy, ans);
            // 更新坐标
            i += 1;
            j += 1;
            dx -= 2;
            dy -= 2;
        }
        return ans;
    }

    /**
     * 从 (x,y) 开始以 m，n 为横向和纵向宽度遍历矩阵
     * */
    public static void rotateMatrix(int[][] matrix, int x, int y, int m, int n,List<Integer> ans) {
        for(int j=y;j-y<n;j++){
            ans.add(matrix[x][j]);
        }

        if(m < 2) return;
        for(int i=x+1;i-x<m;i++) {
            ans.add(matrix[i][y + n - 1]);
        }

        if(n < 2) return;
        for(int j=n+y-2;j>=y;j--)
            ans.add(matrix[x + m - 1][j]);

        for(int i=x+m-2;i>x;--i)
            ans.add(matrix[i][y]);
    }

    /**
     * Method 2: dirs 四个方向，转向
     * */
    public static List<Integer> spiralOrder_2(int[][] matrix) {
        int[][] dirs = {
                {0,1},
                {1,0},
                {0,-1},
                {-1,0}
        };
        int m = matrix.length, n = matrix[0].length;
        List<Integer> ans = new ArrayList<>(m*n);

        int i = 0, j = 0, d = 0;

        for(int k=0;k<m*n;k++) {
            ans.add(matrix[i][j]);
            matrix[i][j] = Integer.MAX_VALUE;

            int x = i+dirs[d][0], y = j+dirs[d][1];
            // 是否要转向
            if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == Integer.MAX_VALUE) {
                d = (d+1) % 4;
            }
            // 加上转向后的偏移量
            i += dirs[d][0];
            j += dirs[d][1];
        }
        return ans;
    }

}
