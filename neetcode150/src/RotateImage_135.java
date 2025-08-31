import java.util.Arrays;

public class RotateImage_135 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9},
        };
        rotate(matrix);

        for(int[] m: matrix)
            System.out.println(Arrays.toString(m));
    }

    /**
     * Top Left  ----- Top Right
     * |                    |
     * |                    |
     * |                    |
     * Bottom Left ---- Bottom Right
     * */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        // 从外层到里层处理
        int l = 0, r = n-1;
        while(l < r) {
            // 对每个位置进行处理
            for(int i=0;i<r-l;i++) {
                int top = l, bottom = r;

                int topLeft = matrix[top][l+i];

                matrix[top][l+i] = matrix[bottom-i][l];

                matrix[bottom-i][l] = matrix[bottom][r-i];

                matrix[bottom][r-i] = matrix[top+i][r];

                matrix[top+i][r] = topLeft;
            }

            l++;
            r--;
        }
    }
}
