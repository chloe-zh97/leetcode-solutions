import java.util.Arrays;

public class PartitionEqualSubsetSum_107 {
    public static void main(String[] args) {
        int[] nums = {1,2,5};
        System.out.println(canPartition_5(nums));
    }

    /**
     * Method 1: dfs
     * 在 nums[i...n-1]
     * */
//    public static boolean canPartition(int[] nums) {
//        int sum = 0;
//        for(int num: nums) sum += num;
//
//        if(sum == 0 || sum % 2 == 1) return false;
//
//        return dfs(nums, 0, sum / 2);
//    }
//
//    private static boolean dfs(int[] nums, int i, int target) {
//        if(target == 0) return true;
//
//        if(target < 0 || i >= nums.length) return false;
//
//        return dfs(nums, i+1, target) || dfs(nums, i+1, target-nums[i]);
//    }

    /**
     * Method 2: 记忆化搜索
     * */
    public static boolean canPartition_2(int[] nums) {
        int sum = 0;
        for(int num: nums) sum += num;

        if(sum == 0 || sum % 2 == 1) return false;

        int n = nums.length;
        // 这里设置为 n+1 防止越界
        int[][] memos = new int[n+1][sum/2+1];
        for(int[] memo: memos)
            Arrays.fill(memo, -1);

        return dfs_2(nums, 0, sum/2, memos);
    }


    private static boolean dfs_2(int[] nums, int i, int target, int[][] memos) {
        if(target == 0) {
            memos[i][target] = 1;
            return true;
        }

        if(target < 0 || i >= nums.length) return false;

        if(memos[i][target] != -1) return memos[i][target] == 1;

        boolean res = dfs_2(nums, i+1, target, memos) || dfs_2(nums, i+1, target-nums[i], memos);

        memos[i][target] = res ? 1: 0;

        return res;
    }

    /**
     * Method 3: 递推
     * */
    public static boolean canPartition_3(int[] nums) {
        int sum = 0;
        for(int num: nums) sum += num;
        if(sum == 0 || sum % 2 == 1) return false;

        int n = nums.length;

        // dp[i+1][j+1]: nums[0...i]的数是否能组成和为j
        boolean[][] dp = new boolean[n+1][sum/2+1];
        dp[0][0] = true;

        for(int i=0;i<n;i++) {
            for(int j=0;j<=sum/2;j++) {
                if(j < nums[i]) {
                    // 只能不选
                    dp[i+1][j] = dp[i][j];
                } else {
                    dp[i+1][j] = dp[i][j] || dp[i][j-nums[i]];
                }
            }
        }
        return dp[n][sum/2];
    }

    /**
     * Method 4: 压缩空间
     * */
    public static boolean canPartition_4(int[] nums) {
        int sum = 0;
        for(int num: nums) sum += num;
        if(sum == 0 || sum % 2 == 1) return false;

        int n = nums.length;
        boolean[][] dp = new boolean[2][sum/2+1];
        dp[0][0] = true;

        for(int i=0;i<n;i++) {
            for(int j=0;j<=sum/2;j++) {
                if(j < nums[i]) {
                    // 只能不选
                    dp[1][j] = dp[0][j];
                } else {
                    dp[1][j] = dp[0][j] || dp[0][j-nums[i]];
                }
            }
            boolean[] tmp = dp[0];
            dp[0] = dp[1];
            dp[1] = tmp;
        }
        return dp[0][sum/2];
    }

    /**
     * Method 5: 继续优化，optimal
     * */
    public static boolean canPartition_5(int[] nums) {
        int sum = 0;
        for(int num: nums) sum += num;
        if(sum == 0 || sum % 2 == 1) return false;

        int n = nums.length;
        int target = sum / 2;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;

        for(int i=0;i<n;i++) {
            for(int j=target;j>=0;j--) {
                if(j>=nums[i]) dp[j] = dp[j] || dp[j-nums[i]];
            }
        }
        return dp[target];
    }

}
