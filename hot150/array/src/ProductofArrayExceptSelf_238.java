import java.util.Arrays;

public class ProductofArrayExceptSelf_238 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] ans = productExceptSelf_2(nums);
        System.out.println(Arrays.toString(ans));
    }
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        // left[i]: nums[0...i-1]的乘积
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = 1;
        // 从左往右
        for(int i=1;i<n;i++)
            left[i] = left[i-1] * nums[i-1];

        right[n-1] = 1;
        for(int i=n-2;i>=0;i--)
            right[i] = right[i+1] * nums[i+1];

        for(int i=0;i<n;i++)
            nums[i] = left[i] * right[i];

        return nums;
    }


    /**
     * Method 2: 先计算右边
     * */
    public static int[] productExceptSelf_2(int[] nums) {
        int n = nums.length;
        int[] right = new int[n];
        right[n-1] = 1;

        for(int i=n-2;i>=0;i--) {
            right[i] = right[i+1] * nums[i+1];
        }

        int pre = 1;
        for(int i=0;i<n;i++) {
            right[i] *= pre;
            pre *= nums[i];
        }
        return right;
    }
}
