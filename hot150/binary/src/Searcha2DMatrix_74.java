public class Searcha2DMatrix_74 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        int target = 13;
        System.out.println(searchMatrix(matrix, target));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;

        // 竖着二分, 找到 <= target 的元素
        int left = 0, right = m-1;
        while(left < right) {
            int mid = left + right + 1>> 1;
            if(matrix[mid][0] > target) right = mid - 1;
            else left = mid;
        }
        int row = left;

        // 横着二分，找到 <= target 的元素
        left = 0; right = n -1;
        while(left < right) {
            int mid = left + right + 1>> 1;
            if(matrix[row][mid] <= target) left = mid;
            else right = mid - 1;
        }
        return matrix[row][left] == target;
    }
}
