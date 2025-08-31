import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix_136 {
    public static void main(String[] args) {
        int[][] matrix = {
                {5},
                {6},
                {7}
        };
        List<Integer> ans = spiralOrder(matrix);
        System.out.println(ans);
    }


    /**
     * Method 1: 一圈一圈打印
     * */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;

        // L = 0, R = 2, T = 0, B = 2
        int L = 0, R = n-1, T = 0, B = m-1;

        while(L <= R && T <= B) {
            // 打印四个方向
            // 1. [0,2] ans = 1,2,3
            // 2.  L=1, R = 1, T = 1, B = 1
            //System.out.println("L="+L+" R="+R+" B="+B+" T="+T);

            for (int j = L; j <= R; j++) {
                ans.add(matrix[T][j]);
            }

            // 1. [1,2] ans = 1,2,3,6,9
            for (int i = T + 1; i < B; i++) {
                ans.add(matrix[i][R]);
            }


            // 1. [1,0] ans = 1,2,3,6,9,8,7
            for (int j = R; j >= L && T!=B; j--) {
                ans.add(matrix[B][j]);
            }

            // 1. [1] ans = 1,2,3,6,9,8,7,4
            for (int i = B - 1; i > T && L!=R; i--) {
                ans.add(matrix[i][L]);
            }


            // 1. L=1, R = 1, T = 1, B = 1
            L += 1;
            R -= 1;
            T += 1;
            B -= 1;
        }
        return ans;
    }

}
