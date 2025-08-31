public class BinarySearch_28 {
    public static void main(String[] args) {
        int[] nums = {-1,0,2,4,6,8};
        int target = 4;
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return nums[left] == target ? left : -1;
    }
}
