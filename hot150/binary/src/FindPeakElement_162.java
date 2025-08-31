public class FindPeakElement_162 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,5,6,4};
        System.out.println(findPeakElement_2(nums));
    }
    /**
     * Method 1: 二分
     * */
    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + right >> 1;
            int ll = Math.max(0, mid-1);
            int rr = Math.min(mid+1, n-1);
            if(nums[mid] >= nums[ll] && nums[mid] >= nums[rr]) return mid;
            else if(nums[mid] < nums[ll]) right = mid-1;
            else left = mid+1;
        }
        return left;
    }

    /**
     * Method 2: nums[mid] < nums[mid+1], 峰值在右边
     * */
    public static int findPeakElement_2(int[] nums) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(mid+1 < n && nums[mid] < nums[mid+1]) left = mid+1;
            else right = mid;
        }
        return left;
    }
}
