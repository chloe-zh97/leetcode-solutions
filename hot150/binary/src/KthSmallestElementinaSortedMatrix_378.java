public class KthSmallestElementinaSortedMatrix_378 {
    public static void main(String[] args) {
        int[][] matrix = {
                {-5}
        };
        int k = 1;
        System.out.println(kthSmallest(matrix, k));
    }

    /**
     * Method 1: 二分
     * */
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n-1][n-1];
        while(left < right) {
            int mid = left + right >> 1;
            if(check(matrix, k, mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private static boolean check(int[][] matrix, int k, int val) {
        // 遍历每一行
        int cnt = 0;
        int n = matrix.length;
        for(int i=0;i<n;i++) {
            if(matrix[i][0] > val) break;

            // matrix[i][0] <= val
            if(matrix[i][n-1] <= val) cnt += n;
            else {
                int pos = binarySearch(matrix[i], val);
                cnt += pos+1;
            }

            if(cnt >= k) return true;
        }
        return cnt >= k;
    }

    /**
     * 在 nums 中寻找 <= target 的元素位置
     * */
    private static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while(left < right) {
            int mid = left + right + 1>> 1;
            if(nums[mid] > target) right = mid - 1;
            else left = mid;
        }
        return nums[left] <= target ? left : -1;
    }
}
