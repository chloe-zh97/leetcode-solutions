import java.util.Arrays;

public class LongestIncreasingSubsequence_106 {
    public static void main(String[] args) {
        int[] nums = {7,7,7,7,7,7,7};
        System.out.println(lengthOfLIS_6(nums));
    }



    /**
     * Method 1: dfs
     * dfs[i]: nums[i...n-1] 的最长严格递增子序列长度
     * */
    public static int lengthOfLIS(int[] nums) {
       return dfs(nums, 0, Integer.MIN_VALUE / 2);
    }

    private static int dfs(int[] nums, int i, int pre) {
        if(i == nums.length) return 0;

        // not include
        int res = dfs(nums, i+1, pre);

        if(nums[i] > pre) {
            // 可以选 nums[i]
            pre = nums[i];
            res = Math.max(res, dfs(nums, i+1, pre) + 1);
        }

        return res;
    }

    /**
     * Method 2: 改记忆化搜索
     * */
    public static int lengthOfLIS_2(int[] nums) {
        int n = nums.length;

        int[][] memos = new int[n][n+1];
        for(int[] mm: memos) Arrays.fill(mm, -1);

        // j 位置存储的是子序列的最后一个元素

        return dfs_2(nums, 0, -1, memos);
    }

    /**
     * dfs[i][j]: 表示 nums[i...n-1] 且最后一个元素是 j 位置的最长子序列长度
     * */
    private static int dfs_2(int[] nums, int i, int j, int[][] memos) {
        if(i == nums.length) return 0;

        if(memos[i][j+1] != -1) return memos[i][j+1];

        // 不选 i
        int res = dfs_2(nums, i+1, j, memos);

        if(j==-1 || nums[i] > nums[j]) {
            res = Math.max(res, dfs_2(nums, i+1,i, memos)+1);
        }

        memos[i][j+1] = res;

        return res;
    }

    /**
     * Method 3: dfs(i) : 以 i 结尾的，最长子序列长度
     * */
    public static int lengthOfLIS_3(int[] nums) {
        int ans = 0;
        for(int i=0;i<nums.length;i++)
            ans = Math.max(ans, dfs_3(nums, i));
        return ans;
    }


    /**
     * 以 i 结尾的，最长子序列的长度
     * */
    private static int dfs_3(int[] nums, int i) {
        // 需要遍历每一个比 i 小的位置
        int res = 1;
        for(int j=0;j<i;j++) {
            if(nums[i] > nums[j]) res = Math.max(dfs_3(nums, j)+1, res);
        }

        return res;
    }


    /**
     * Method 4: 改记忆化搜索
     * */
    public static int lengthOfLIS_4(int[] nums) {
        int n = nums.length;
        // memos[i]: 以 i 结尾的子串，最长是多少
        int[] memos = new int[n];
        Arrays.fill(memos, -1);

        int ans = 0;
        for(int i=0;i<n;i++) {
            ans = Math.max(dfs_4(nums, i, memos),ans);
        }

        return ans;
    }

    private static int dfs_4(int[] nums, int i, int[] memos) {
        if(memos[i] != -1) return memos[i];

        int res = 1;
        for(int j=i-1;j>=0;j--) {
            if(nums[i] > nums[j]) {
                res = Math.max(res, dfs_4(nums, j, memos)+1);
            }
        }
        memos[i] = res;
        return res;
    }

    /**
     * Method 5: 递推
     * */
    public static int lengthOfLIS_5(int[] nums) {
        int n = nums.length;

        // dp[i]: 以 nums[i] 结尾的最长子序列长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int ans = 0;

        for(int i=0;i<n;i++) {
            for(int j=i-1;j>=0;j--) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    /**
     * Method 6: 二分
     * 维护 int[] tails 数组，tails[k] 代表长度为 k 的连续子串，末尾最小值是多少
     * */
    public static int lengthOfLIS_6(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];

        int k = 0;
        for(int num: nums) {
            int l = 0, r = k;
            while(l < r) {
                int mid = l + r >> 1;
                // 找到第一个 >= num 的元素位置
                if(tails[mid] >= num) r = mid;
                else l = mid + 1;
            }

            tails[l] = num;
            if(l == k) k++;
           // System.out.println("num="+num+" tails="+ Arrays.toString(tails));
        }
        return k;
    }




}
