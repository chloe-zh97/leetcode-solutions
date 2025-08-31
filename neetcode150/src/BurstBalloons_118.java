import java.util.Arrays;

public class BurstBalloons_118 {
    public static void main(String[] args) {
        int[] nums = {4,2,3,7};
        System.out.println(maxCoins_2(nums));
    }

    /**
     * Method 1: 逆向思考，如果最后一个 pop nums[i] 能得到多少得分
     * 记忆化搜索
     * */
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] copyNums = new int[n+2];
        for(int i=0;i<n;i++)
            copyNums[i+1] = nums[i];

        copyNums[0] = 1;
        copyNums[n+1] = 1;

        int[][] memos = new int[n+2][n+2];
        for(int[] mm: memos)
            Arrays.fill(mm, -1);

        return dfs(copyNums, 1, n, memos);
    }

    /**
     * pop nums[left...right] 的气球最多能得到多少分
     * */
    private static int dfs(int[] nums, int left, int right, int[][] memos) {
        if(left > right) return 0;

        if(memos[left][right] != -1) return memos[left][right];

        // 枚举在 nums[left...right] 中，最后一个射击的气球
        int res = 0;
        for(int i=left;i<=right;i++) {
            int tmp = nums[left-1] * nums[i]* nums[right+1] + dfs(nums, left, i-1, memos) + dfs(nums, i+1, right, memos);
            res = Math.max(tmp, res);
        }

        memos[left][right] = res;
        return res;
    }

    /**
     * Method 2: 改递推
     * */
    public static int maxCoins_2(int[] nums) {
        int n = nums.length;

        int[] copyNums = new int[n+2];
        for(int i=0;i<n;i++)
            copyNums[i+1] = nums[i];

        copyNums[0] = 1;
        copyNums[n+1] = 1;

        int[][] dp = new int[n+2][n+2];

        for(int i=n;i>=1;i--) {
            for(int j=i;j<=n;j++) {

                int res = 0;
                for(int k=i;k<=j;k++) {
                    int tmp = copyNums[i-1] * copyNums[k] * copyNums[j+1] + dp[i][k-1] + dp[k+1][j];
                    res = Math.max(res, tmp);
                }

                dp[i][j] = res;
            }
        }

        return dp[1][n];
    }


}
