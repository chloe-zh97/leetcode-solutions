public class SearchinRotatedSortedArray_32 {
    public static void main(String[] args) {
        int[] nums = {3,5,6,0,1,2};
        int target = 4;
        System.out.println(search_2(nums, target));
    }

    /**
     * Method 1: 找到最小值，然后用二分
     * 3,4,5,1,2
     * */
    public static int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;

        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] <= nums[n-1]) right = mid;
            else left = mid + 1;
        }

        int pivot = left;
        int ans = binarySearch(nums, 0, pivot-1, target);
        if(ans!=-1) return ans;
        return binarySearch(nums, pivot, n-1,target);
    }

    /**
     * 在 nums[left,right] 中找 >= target 的元素
     * */
    private static int binarySearch(int[] nums, int left, int right, int target) {
        if(left > right) return -1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return nums[left]==target ? left : -1;
    }

    /**
     * Method 2: One pass binary search
     * */
    public static int search_2(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + right>> 1;
            if(nums[mid] >= nums[0]) {
                // mid 落入左半边
                if(target >= nums[0] && target <= nums[mid]) {
                    // 在左边
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                // mid 落入右半边
                if(target > nums[mid] && target <= nums[n-1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[left] == target ? left: -1;
    }

}
