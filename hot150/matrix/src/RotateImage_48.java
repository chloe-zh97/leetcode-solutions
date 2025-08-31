public class RotateImage_48 {
    public static void main(String[] args) {
        int[][] matrix = {
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16}
        };
        rotate(matrix);
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        // 左右列对换对换
        int left = 0, right = n-1;
        while(left < right) {
            for(int i=0;i<n;i++) {
                int tmp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = tmp;
            }
            left++;
            right--;
        }

        printMatrix(matrix);

        // 对角线对换
        for(int i=0;i<n;i++) {
            for(int j=0;i+j<n;j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][n-i-1];
                matrix[n-j-1][n-i-1] = tmp;
            }
        }
        printMatrix(matrix);
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
}
