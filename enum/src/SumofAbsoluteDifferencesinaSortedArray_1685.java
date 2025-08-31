import java.util.Arrays;

public class SumofAbsoluteDifferencesinaSortedArray_1685 {
    public static void main(String[] args) {
        int[] nums = {1,4,6,8,10};
        int[] ans = getSumAbsoluteDifferences_2(nums);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * Method 1: 二分+前缀和
     * 关键是nums数组是有序的, 比如 2,3,5
     * 二分找到 < 2 的位置 x, > 2 的位置 y
     * x 部分的前缀和 + y 部分的前缀和
     * */
    public static int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        // 前缀和
        int[] s = new int[n+1];
        for(int i=1;i<s.length;i++)
            s[i] = s[i-1] + nums[i-1];

        int[] ans = new int[n];

        for(int i=0;i<n;i++) {
            int target = nums[i];
            // 二分查找
            int left = binarySearchLessThanTarget(nums, target);
            int right = binarSearchGreaterThan(nums, target);

            // 0...left的累加
            int leftSum = target * (left + 1) - s[left+1];
            // right, right+1, ... n-1
            int rightSum = (s[n] - s[right]) - target * (n-right);
            ans[i] = leftSum + rightSum;
        }

        return ans;
    }

    /**
     * 在 nums 中寻找 <= target 的位置, 左区间的右端点
     * */
    private static int binarySearchLessThanTarget(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while(left < right) {
            int mid = left + right + 1>> 1;
            if(nums[mid] <= target) left = mid;
            else right = mid - 1;
        }
        return nums[left] <= target ? left : -1;
    }

    /**
     * 在 nums 中寻找 >= target 的位置，右区间的左端点
     * */
    private static int binarSearchGreaterThan(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return nums[left] >= target ? left : -1;
    }

    /**
     * Method 2: 优化，只用前缀和
     * */
    public static int[] getSumAbsoluteDifferences_2(int[] nums) {
        int n = nums.length;
        int[] s = new int[n+1];
        for(int i=1;i<s.length;i++)
            s[i] = s[i-1] + nums[i-1];

        int[] ans = new int[n];
        for(int i=0;i<n;i++) {
            int left = i * nums[i] - s[i];
            int right = s[n] - s[i] - (n-i) * nums[i];  // right 的计算包括了 nums[i], 但不影响
            ans[i] = left + right;
        }
        return ans;
    }
}
