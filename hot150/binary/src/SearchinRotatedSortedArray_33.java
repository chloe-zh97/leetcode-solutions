
public class SearchinRotatedSortedArray_33 {
    public static void main(String[] args) {
        int[] nums = {1, 3};
        System.out.println(search(nums, 3));
    }

    /**
     * Method 1: 两次二分
     */
    public static int search(int[] nums, int target) {
        int n = nums.length;
        // 先找分割点
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (nums[mid] >= nums[0]) left = mid + 1;
            else right = mid;
        }

        int p = left, ans = -1;
        if (nums[p] == target) return p;

        if (target >= nums[0]) {
            ans = binarySearch(nums, 0, left - 1, target);
        } else {
            ans = binarySearch(nums, p, n - 1, target);
        }
        return ans;
    }

    private static int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right) return -1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (nums[mid] <= target) left = mid;
            else right = mid - 1;
        }
        return nums[left] == target ? left : -1;
    }
}
