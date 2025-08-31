import java.util.Arrays;

public class SetMatrixZeroes_137 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,0,5},
                {6,7,8}
        };
        setZeroes_2(matrix);
        for(int[] mm: matrix)
            System.out.println(Arrays.toString(mm));
    }

    /**
     * Method 1: 存储为0的行，列
     * */
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] cols = new int[n];
        int[] rows = new int[m];

        Arrays.fill(cols, -1);
        Arrays.fill(rows, -1);

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j] == 0) {
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }

        for(int i=0;i<m;i++) {
            if(rows[i] == 1) Arrays.fill(matrix[i], 0);
        }

        for(int j=0;j<n;j++) {
            if(cols[j] == 1) {
                for(int i=0;i<m;i++)
                    matrix[i][j] = 0;
            }
        }
    }

    /**
     * Method 2: 用第0行和第0列存储
     * */
    public static void setZeroes_2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        boolean rowZero = false;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j] == 0) {
                    // 将第 i 行标记为 0
                    if(i == 0) rowZero = true;
                    else matrix[i][0] = 0;

                    // 将第 j 列标记为0
                    matrix[0][j] = 0;
                }
            }
        }

        // 所有行和列为0的情况都已经被统计了
        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            }
        }

        // 处理第 0 列
        if(matrix[0][0] == 0) {
            for(int i=0;i<m;i++)
                matrix[i][0] = 0;
        }

        // 处理第0行
        if(rowZero) Arrays.fill(matrix[0], 0);
    }
}
