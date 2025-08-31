public class HouseRobberIV_2560 {
    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        int k = 2;
        System.out.println(minCapability_2(nums, k));
    }

    /**
     * Method 1: 二分法，二分Capacity 24.95%
     * */
    public static int minCapability(int[] nums, int k) {
        int left = 0, right = 0;
        for(int num: nums)
            right = Math.max(num, right);

        // 二分
        while(left < right) {
            int mid = left + right >> 1;
            // capacity 越大越满足条件
            if(check(nums, mid, k)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    /**
     * 在 nums 数组中能不能选到 k 个 <= capacity的数，且不能相邻
     * */
    private static boolean check(int[] nums, int capacity, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0] <= capacity ? 1: 0;
        if(n == 1) return dp[n-1] >= k;

        dp[1] = Math.min(nums[0], nums[1]) <= capacity ? 1: 0;
        for(int i=2;i<n;i++) {
            if(nums[i] > capacity) {
                dp[i] = dp[i-1];
            } else {
                dp[i] = Math.max(dp[i-2]+1, dp[i-1]);
            }
            if(dp[i] >= k) return true;
        }
        return dp[n-1] >= k;
    }

    /**
     * Method 2: 贪心 + 二分
     * */
    public static int minCapability_2(int[] nums, int k) {
        int left = 0, right = 0;
        for(int a: nums)
            right = Math.max(right, a);

        while(left < right) {
            int mid = left + right >> 1;
            if(check_2(nums, mid, k)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    /**
     * 能偷就偷
     * */
    private static boolean check_2(int[] nums, int capacity, int k) {
        int n = nums.length;
        int cnt = 0;
        for(int i=0;i<n;i++) {
            if(nums[i] <= capacity) {
                cnt++;
                // 跳过
                i+=1;
            }
            if(cnt >= k) return true;
        }
        return false;
    }
}
