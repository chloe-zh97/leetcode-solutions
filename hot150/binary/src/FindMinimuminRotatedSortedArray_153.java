public class FindMinimuminRotatedSortedArray_153 {
    public static void main(String[] args) {
        int[] nums = {11,13,15,17};
        System.out.println(findMin(nums));
    }

    public static int findMin(int[] nums) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] <= nums[n-1]) right = mid;
            else left = mid + 1;
        }
        return nums[left];
    }
}
