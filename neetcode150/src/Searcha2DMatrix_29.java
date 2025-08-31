public class Searcha2DMatrix_29 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,4,8},
                {10,11,12,13},
                {14,20,30,40}
        };
        int target = 10;
        System.out.println(searchMatrix_2(matrix, target));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int rowNumber = searchCol(matrix, target);
        if(rowNumber == -1) return false;

        int pos = searchRow(matrix[rowNumber], target);
        return pos!=-1 && matrix[rowNumber][pos] == target;
    }

    /**
     * 在 nums 中找 >= target 的元素
     */
    private static int searchRow(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return nums[left] >= target ? left : -1;
    }

    /**
     * 在 matrix 第0列找 <= target 的最大的元素，左区间的右端点
     * */
    private static int searchCol(int[][] matrix, int target) {
        int m = matrix.length;
        int left = 0, right = m-1;
        while(left<right) {
            int mid = left + right + 1 >> 1;
            if(matrix[mid][0] <= target) left = mid;
            else right = mid - 1;
        }
        // 所有的都 > target
        return matrix[left][0] <= target ? left : -1;
    }

    /**
     * Method 2: 一次二分，给matrix 的元素排序编号再转为行列
     * 编号范围 【0，m*n-1】
     * index / m , index % n
     * */
    public static boolean searchMatrix_2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m*n-1;

        while(left < right) {
            int mid = left + right + 1>> 1;
            int row = mid / n, col = mid % n;
            if(matrix[row][col] <= target) left = mid;
            else right = mid - 1;
        }
        System.out.println("left="+left);
        return matrix[left/m][left%n]==target;
    }
}
