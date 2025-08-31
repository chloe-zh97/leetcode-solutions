import java.util.Arrays;

public class JumpGameII_121 {
    public static void main(String[] args) {
        int[] nums = {2,1,2,1,0};
        System.out.println(jump_3(nums));
    }

    public static int jump(int[] nums) {
        int n = nums.length;
        int l = 0, r = 0;
        int ans = 0;
        while(r < n-1) {

            // [l,r] 的区间还没有到末尾
            int further = 0;
            // 更新 [l, r] 能到的最远范围
            for(int i=l;i<=r;i++)
                further = Math.max(further, i+nums[i]);

            // 到这里[l, r] 能到的最远的地方已经更新完了
            ans++;
            // 更新下一段区间
            l = r+1;
            r = further;
        }
        return ans;
    }

    /**
     * Method 2: dfs + 记忆化搜索
     * */
    public static int jump_2(int[] nums) {
        int n = nums.length;
        int[] memos = new int[n];
        Arrays.fill(memos, -1);

        return dfs_2(nums, n-1, memos);
    }

    /**
     * 从 0 跳到 i 位置花费的最少跳数
     * */
    private static int dfs_2(int[] nums, int i, int[] memos) {
        if(i == 0) return 0;

        if(memos[i] != -1) return memos[i];

        // 0 -> j -> i
        int res = Integer.MAX_VALUE;
        for(int j=0;j<i;j++) {
            if(j+nums[j] >= i) res = Math.min(res, dfs_2(nums, j, memos) + 1);
        }

        memos[i] = res;
        return res;
    }

    /**
     * Method 3: 动态规划
     * */
    public static int jump_3(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);

        // dp[i]: nums 从 0 跳到 i 最少花费多少跳
        dp[0] = 0;

        for(int i=1;i<n;i++) {
            for(int j=0;j<i;j++) {
                if(j+nums[j] >= i) dp[i] = Math.min(dp[i], dp[j]+1);
            }
        }

        return dp[n-1];

    }
}
