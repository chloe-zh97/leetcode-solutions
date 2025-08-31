import java.util.Arrays;

public class SetMatrixZeroes_73 {
    public static void main(String[] args) {
         int[][] matrix = {
                 {1,1,1},
                 {1,0,1},
                 {1,1,1}
         };
         setZeroes_3(matrix);
         printMatrix(matrix);
    }

    /**
     * Method 1: 二维数组模拟，非最优方法
     * */
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visit = new boolean[m][n];

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(visit[i][j] || matrix[i][j] != 0) continue;
                visit[i][j] = true;

                for(int k=0;k<n;k++) {
                    if(matrix[i][k] == 0) continue;
                    matrix[i][k] = 0;
                    visit[i][k] = true;
                }

                for(int k=0;k<m;k++) {
                    if(matrix[k][j] == 0) continue;
                    matrix[k][j] = 0;
                    visit[k][j] = true;
                }
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Method 2: 记录需要改变的行，列, O(m+n)
     * */
    public static void setZeroes_2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] rows = new int[m], cols = new int[n];

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j] == 0) {
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }

        for(int i=0;i<rows.length;i++) {
            if(rows[i] == 1) {
                Arrays.fill(matrix[i], 0);
            }
        }

        for(int j=0;j<cols.length;j++) {
            if(cols[j] == 1) {
                for(int i=0;i<m;i++)
                    matrix[i][j] = 0;
            }
        }

    }


    /**
     * Method 3: 用边界记录是否设置为0
     * */
    public static void setZeroes_3(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // r0 和 c0 表示首行和首列是否需要设置为 0
        boolean r0 = false, c0 = false;

        // 遍历上边界, 确定 r0
        for(int j=0;j<n;j++) {
            if(matrix[0][j] == 0) {
                r0 = true;
                break;
            }
        }

        for(int i=0;i<m;i++) {
            if(matrix[i][0] == 0) {
                c0 = true;
                break;
            }
        }

        // 遍历整个数组，设置需要变成0的行列
        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 遍历左边界，跳过(0,0)
        for(int i=1;i<m;i++) {
            if(matrix[i][0] == 0) {
                // i 行需要设置为 0
                Arrays.fill(matrix[i], 0);
            }
        }

        // 遍历上边界
        for(int j=1;j<n;j++) {
            if(matrix[0][j] == 0) {
                // 将某列设置为 0
                for(int i=0;i<m;i++)
                    matrix[i][j] = 0;
            }
        }

        if(r0) Arrays.fill(matrix[0], 0);
        if(c0) {
            for(int i=0;i<m;i++)
                matrix[i][0] = 0;
        }
    }

}
