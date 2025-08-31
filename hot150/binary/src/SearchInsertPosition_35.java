public class SearchInsertPosition_35 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 7;
        System.out.println(searchInsert(nums, target));
    }

    public static int searchInsert(int[] nums, int target) {
        int pos = binarySearch(nums, target);
        if(pos != -1 && nums[pos] == target) return pos;
        else return pos+1;
    }

    private static int binarySearch(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + right + 1 >> 1;
            if(nums[mid] > target) right = mid - 1;
            else left = mid;
        }
        return nums[left] <= target ? left : -1;
    }
}
