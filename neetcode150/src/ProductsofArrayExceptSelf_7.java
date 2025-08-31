import java.util.Arrays;

public class ProductsofArrayExceptSelf_7 {
    public static void main(String[] args) {
        int[] nums = {1,2,4,6};
        System.out.println(Arrays.toString(productExceptSelf_2(nums)));
    }

    /**
     * Method 1: int[] left, int[] right
     * left[i+1]: nums[0...i] 的乘
     * right[i]: nums[i...n-1] 的乘
     * */
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n+1];
        left[0] = 1;

        for(int i=1;i<left.length;i++) {
            left[i] = left[i-1] * nums[i-1];
        }

        int[] right = new int[n+1];
        right[n] = 1;
        for(int i=right.length-2;i>=0;i--) {
            right[i] = right[i+1] * nums[i];
        }

        int[] ans = new int[n];
        for(int i=0;i<n;i++) {
            ans[i] = left[i] * right[i+1];
        }
        return ans;
    }

    /**
     * 优化，省去 right 数组
     * */
    public static int[] productExceptSelf_2(int[] nums) {
        int n = nums.length;
        int[] left = new int[n+1];
        left[0] = 1;
        for(int i=0;i<n;i++) {
            left[i+1] = left[i] * nums[i];
        }

        int[] ans = new int[n];
        int right = 1;
        for(int i=n-1;i>=0;i--) {
            ans[i] = left[i] * right;
            right *= nums[i];
        }
        return ans;
    }

    /**
     * Method 2: division, 统计总乘，以及总共的0的个数，如果0的个数超过1个，全是0
     * */
    public static int[] productExceptSelf_3(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int prod = 1, countZero = 0;
        for(int num: nums) {
            if(num == 0) countZero++;
            else prod *= num;
        }

        if(countZero > 1) return ans;

        for(int i=0;i<n;i++) {
            int num = nums[i];
            if(countZero > 0) {
                ans[i] = num == 0 ? prod: 0;
            } else {
                ans[i] = prod / num;
            }
        }
        return ans;
    }
}
