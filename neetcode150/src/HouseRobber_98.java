import java.util.Arrays;

public class HouseRobber_98 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,7,9,11,13,15,17,19,2,4,6,8,10,12,14,16,18,20,
                21,23,25,27,29,31,33,35,37,39,40,42,44,46,48,50,52,54,
                56,58,60,62,64,66,68,70,72,74,76,78,80,82,84,86,88,
                90,92,94,96,98,100,99,97,95,93,91,89,87,85,83,81,79,
                77,75,73,71,69,67,65,63,61,59,57,55,53,51,49,47,45,43,41,39,37,35,33,31,29,27,25,23};
        System.out.println(rob_5(nums));
    }

    /**
     * Method 1: 暴力法 超时
     * */
    public static int rob(int[] nums) {
        return dfs(nums, 0, 0);
    }

    /**
     * 抢劫 [i...n-1] 房子能收获的最大金钱
     * */
    private static int dfs(int[] nums, int i, int money) {
        if(i >= nums.length) return money;
        // 抢劫这个房子
        int rob = dfs(nums, i+2, nums[i]+money);
        int noRob = dfs(nums, i+1, money);

        return Math.max(rob, noRob);
    }

    /**
     * Method 2: 记忆化搜索
     * */
    public static int rob_2(int[] nums) {
        int n = nums.length;
        int[] memos = new int[n+5];
        Arrays.fill(memos, -1);
        return dfs_2(nums, 0, memos);
    }

    /**
     * 抢 [i...n-1] 的房子最多能抢到多少钱
     * 注意不要把 path 放在参数上，应该放在返回值上
     * */
    private static int dfs_2(int[] nums, int i, int[] memos) {
        if(i >= nums.length) return 0;

        if(memos[i] != -1) return memos[i];

        int rob = dfs_2(nums, i+2, memos) + nums[i];
        int noRob = dfs_2(nums, i+1, memos);

        memos[i] = Math.max(rob, noRob);
        return memos[i];
    }

    /**
     * Method 3: dp 自顶向上
     * */
    public static int rob_3(int[] nums) {
        int n = nums.length;
        if(n < 2) return nums[0];
        // dp[i][0]: 不抢 i 号房子，[i,n-1]这些房子能抢到多少钱
        int[][] dp = new int[n+5][2];
        dp[n-1][0] = 0;
        dp[n-1][1] = nums[n-1];

       for(int i=n-2;i>=0;i--) {
            dp[i][1] = dp[i+1][0] + nums[i];
            dp[i][0] = Math.max(dp[i+1][0], dp[i+1][1]);
       }
       return Math.max(dp[0][0], dp[0][1]);
    }

    /**
     * Method 4: 优化，省去 dp 数组
     * */
    public static int rob_4(int[] nums) {
        int n = nums.length;
        if(n < 2) return nums[0];
        int f0_0 = 0;
        int f0_1 = nums[n-1];

        for(int i=n-2;i>=0;i--) {
            int f1_1 = f0_0 + nums[i];
            int f1_0 = Math.max(f0_0, f0_1);

            f0_0 = f1_0;
            f0_1 = f1_1;
        }
        return Math.max(f0_0, f0_1);
    }

    /**
     * Method 5: 自底向上的 dp
     * */
    public static int rob_5(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];

        // dp[i]: nums[0..i] 能打劫到的最大值
        int[] dp = new int[n+5];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for(int i=2;i<n;i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }

        return dp[n-1];
    }
}
