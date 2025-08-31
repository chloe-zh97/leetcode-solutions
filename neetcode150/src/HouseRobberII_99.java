import java.util.Arrays;

public class HouseRobberII_99 {
    public static void main(String[] args) {
        int[] nums = {2,9,8,3,6};
        System.out.println(rob(nums));
    }

    /**
     * Method 1: 复用 rob 1
     * */
    public static int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];

        int[] nums1 = Arrays.copyOfRange(nums, 1, n);
        int[] nums2 = Arrays.copyOfRange(nums, 0, n-1);
        return Math.max(helper(nums1), helper(nums2));
    }

    private static int helper(int[] nums) {
        int f0 = 0, f1 = 0;

        for(int num: nums) {
            int newF = Math.max(f0+num, f1);
            f0 = f1;
            f1 = newF;
        }
        return f1;
    }

}
