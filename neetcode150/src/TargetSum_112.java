
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TargetSum_112 {
    public static void main(String[] args) {
        int[] nums = {1,0};
        int target = 1;
        System.out.println(findTargetSumWays_10(nums, target));
    }


    public static int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, 0, 0, target);
    }

    /**
     * 在 nums[i..n-1] 中和为 target 的方案数
     * */
    private static int dfs(int[] nums, int i, int path, int target) {
        int n = nums.length;
        // i 一定要走到叶子节点
        if(i == n) return path == target ? 1: 0;

        return dfs(nums, i+1, path+nums[i], target) + dfs(nums, i+1, path-nums[i], target);
    }

    /**
     * Method 2: 记忆化搜索
     * */
    private static int SHIFT_2 = 0;
    public static int findTargetSumWays_2(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for(int num: nums) sum += num;

        // 增加一个 shift
        int[][] memos = new int[n][sum*2+1];
        SHIFT_2 = sum;

        for(int[] mm: memos) Arrays.fill(mm, -1);

        return dfs_2(nums, 0, 0, target, memos);
    }

    private static int dfs_2(int[] nums, int i, int path, int target, int[][] memos) {
        int n = nums.length;
        if(i == n) return path == target ? 1: 0;

        if(memos[i][path+SHIFT_2] != -1) return memos[i][path+SHIFT_2];

        int res = dfs_2(nums, i+1, path+nums[i], target, memos) +
                dfs_2(nums, i+1, path-nums[i], target, memos);

        memos[i][path+SHIFT_2] = res;
        return res;
    }


    /**
     * Method 3: 递推，自底向上
     * */
    public static int findTargetSumWays_3(int[] nums, int target) {
        int n = nums.length;
        // target 可能为负数，用 hashMap 代替 int[][] memos
        Map<Integer, Integer>[] map = new HashMap[n+1];
        for(int i=0;i<n+1;i++)
            map[i] = new HashMap<>();

        // map[i].get(j): 在 nums[0..i-1] 中和为 j 的方案数
        // 自底向上
        map[0].put(0, 1);

        for(int i=0;i<n;i++) {
            for(Map.Entry<Integer, Integer> entry: map[i].entrySet()) {
                // 取出这一行的所有相关的 j
                int j = entry.getKey();
                int count = entry.getValue();
                // map[i].get(j): 在 nums[0...i-1] 中和为 j 的种类数

                // 如果 nums[i] 取到负数，将 map[i+1][j-nums[i] 的值更新为 count
                // 如果 nums[i] 取的正数，将 map[i+1][j+nums[i]] 的值更新为 count
                map[i+1].merge(j-nums[i], count, Integer::sum);
                map[i+1].merge(j+nums[i], count, Integer::sum);
            }
        }
        return map[n].getOrDefault(target, 0);
    }


    /**
     * Method 4: 递推，自顶向下
     * */
    public static int findTargetSumWays_4(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer>[] map = new HashMap[n+1];
        for(int i=0;i<n+1;i++)
            map[i] = new HashMap<>();

        // target 有可能是负数
        // map[i][j]: nums[i...n-1]中和为 j 的方案数
        // 初始化的是边界条件
        map[n].put(0, 1);

        for(int i=n-1;i>=0;i--) {
//            int res = dfs_2(nums, i+1, path+nums[i], target, memos) +
//                    dfs_2(nums, i+1, path-nums[i], target, memos);

            for(Map.Entry<Integer, Integer> entry: map[i+1].entrySet()) {
                int j = entry.getKey();
                int count = entry.getValue();

                // 如果 nums[i] 取正数，那么对应的更新 j-nums[i]
                // 如果 nums[i] 取负数，对应的更新为 j+nums[i]
                map[i].merge(j-nums[i], count, Integer::sum);
                map[i].merge(j+nums[i], count, Integer::sum);
            }
        }

        return map[0].getOrDefault(target, 0);
    }

    /**
     * Method 5: 滚动数组
     * */
    public static int findTargetSumWays_5(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for(int i=0;i<n;i++) {

            Map<Integer, Integer> newMap = new HashMap<>();

            for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int j = entry.getKey();
                int count = entry.getValue();

                newMap.merge(j+nums[i], count, Integer::sum);
                newMap.merge(j-nums[i], count, Integer::sum);
            }

            map = newMap;
        }

        return map.getOrDefault(target, 0);
    }


    /**
     * Method 6: dfs, 转化为背包容量
     * */
    public static int findTargetSumWays_6(int[] nums, int target) {
        int sum = 0;
        for(int num: nums) sum += num;

        // 问题转换为，在 nums 中选一些数，让它们的和为 sum
        sum -= Math.abs(target);

        if(sum < 0 || sum % 2 == 1) return 0;

        // 在 nums[0...n-1] 中选数，使得和为 target
        return dfs_6(nums, 0, sum/2);

    }

    private static int dfs_6(int[] nums, int i, int target) {
        int n = nums.length;

        if(i == n) return target == 0 ? 1: 0;

        // 选或者不选
        return dfs_6(nums, i+1, target) + dfs_6(nums, i+1, target-nums[i]);
    }


    /**
     * Method 7: 改记忆化搜索
     * */
    public static int findTargetSumWays_7(int[] nums, int target) {
        int n = nums.length;

        int sum = 0;
        for(int num: nums) sum += num;

        // 问题转换为，在 nums 中选一些数，让它们的和为 sum
        sum -= Math.abs(target);

        if(sum < 0 || sum % 2 == 1) return 0;

        target = sum / 2;
        int[][] memos = new int[n][target+1];
        for(int[] mm: memos)
            Arrays.fill(mm, -1);

        return dfs_7(nums, n-1, target, memos);
    }

    private static int dfs_7(int[] nums, int i, int target, int[][] memos) {
        if(i < 0) return target == 0 ? 1: 0;

        if (memos[i][target] != -1) return memos[i][target];

        // 不选
        int res = dfs_7(nums, i-1, target, memos);
        if(target >= nums[i]) {
            res += dfs_7(nums, i-1, target-nums[i], memos);
        }

        memos[i][target] = res;
        return res;
    }


    /**
     * Method 8: 改递推 自底向上
     * */
    public static int findTargetSumWays_8(int[] nums, int target) {
        int n = nums.length;

        int sum = 0;
        for(int num: nums) sum += num;

        // 问题转换为，在 nums 中选一些数，让它们的和为 sum
        sum -= Math.abs(target);

        if(sum < 0 || sum % 2 == 1) return 0;

        target = sum / 2;

        // dp[i][j]: 在 nums[0...i-1] 中和为 target 的方案数
        int[][] dp = new int[n+1][target+1];
        // 初始化
        dp[0][0] = 1;

        // 自底向上
        for(int i=0;i<n;i++) {
            for(int j=0;j<=target;j++) {
//                int res = dfs_7(nums, i-1, target, memos);
//                if(target >= nums[i]) {
//                    res += dfs_7(nums, i-1, target-nums[i], memos);
//                }

                // 不选
                dp[i+1][j] = dp[i][j];

                if(j >= nums[i]) {
                    dp[i+1][j] += dp[i][j-nums[i]];
                }
            }
        }

        return dp[n][target];
    }


    /**
     * Method 9: 改递推 自顶向下
     * */
    public static int findTargetSumWays_9(int[] nums, int target) {
        int n = nums.length;

        int sum = 0;
        for(int num: nums) sum += num;

        // 问题转换为，在 nums 中选一些数，让它们的和为 sum
        sum -= Math.abs(target);

        if(sum < 0 || sum % 2 == 1) return 0;

        target = sum / 2;

        // dp[i][j]: nums[i...n-1] 中和为 j 的方案数
        int[][] dp = new int[n+1][target+1];
        dp[n][0] = 1;

        // 初始化完成，开始 dp, 自顶向下
        for(int i=n-1;i>=0;i--) {
            for(int j=target;j>=nums[i];j--) {
                // 不选
                dp[i][j] = dp[i+1][j];

                if(nums[i] <= j) {
                    // 选
                    // 给这个容量腾地方
                    dp[i][j] += dp[i+1][j-nums[i]];
                }
            }
        }

        return dp[0][target];
    }


    /**
     * Method 10: 空间压缩 滚动数组 自底向上
     * */
    public static int findTargetSumWays_10(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for(int num: nums) sum += num;

        // 问题转换为，在 nums 中选一些数，让它们的和为 sum
        sum -= Math.abs(target);

        if(sum < 0 || sum % 2 == 1) return 0;
        target = sum / 2;


        int[] dp = new int[target+1];
        dp[0] = 1;

        for(int i=0;i<n;i++) {
            int[] newDp = new int[target+1];

            for(int j=0;j<=target;j++) {

                newDp[j] = dp[j];
                if(j >= nums[i])
                    newDp[j] += dp[j-nums[i]];
            }
            dp = newDp;
        }

        return dp[target];
    }



}
