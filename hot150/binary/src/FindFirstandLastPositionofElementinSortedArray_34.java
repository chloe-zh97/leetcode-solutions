public class FindFirstandLastPositionofElementinSortedArray_34 {
    public static void main(String[] args) {
        int[] nums = {};
        int target = 0;
        nums = searchRange(nums, target);
        for(int i: nums)
            System.out.print(i+" ");
        System.out.println();
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if(nums.length == 0) return res;
        res[1] = binarySearch_lessThan(nums, target);
        if(res[1] == -1) return res;

        res[0] = binarySearch_largerThan(nums, target);
        return res;
    }

    /**
     * 在 nums 中找 <= target 的元素位置
     * */
    private static int binarySearch_lessThan(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + right + 1>> 1;
            if(nums[mid] <= target) left = mid;
            else right = mid - 1;
        }
        return nums[left] == target ? left : -1;
    }

    private static int binarySearch_largerThan(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return nums[left] == target ? left : -1;
    }
}
