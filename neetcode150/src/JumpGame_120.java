import java.util.Arrays;

public class JumpGame_120 {
    public static void main(String[] args) {
        int[] nums = {1,2,0,1,0};
        System.out.println(canJump_4(nums));
    }

    /**
     * Method 1: 双指针模拟
     * */
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        for(int i=0;i<n;i++) {

            int start = i;
            // 最远可以跳到哪个地方
            int end = i+nums[i];

            while(start <= end) {
                end = Math.max(start+nums[start], end);
                start++;
                if(end >= n-1) return true;
            }

            // 跳不动了
            if(nums[end] == 0) break;
            i = end-1;
        }
        return false;
    }


    /**
     * Method 2: dfs
     * */
    public static boolean canJump_2(int[] nums) {
        return dfs_2(nums, 0);
    }

    /**
     * nums 数组从 i 位置开始跳，是否可以跳到末尾
     * */
    private static boolean dfs_2(int[] nums, int i) {
        int n = nums.length;
        if(i >= n-1) return true;

        int end = nums[i] + i;
        for(int j=end;j>i;j--) {
            boolean res = dfs_2(nums, j);
            if(res) return true;
        }
        return false;
    }

    /**
     * Method 3: 记忆化搜索
     * */
    public static boolean canJump_3(int[] nums) {
        int n = nums.length;
        int[] memos = new int[n];
        Arrays.fill(memos, -1);

        return dfs_3(nums, 0, memos);
    }

    private static boolean dfs_3(int[] nums, int i, int[] memos) {
        if(i >= nums.length-1) return true;
        if(memos[i] != -1) return memos[i] == 1;

        boolean res = false;
        int end = nums[i] + i;
        for(int j=end;j>i;j--) {
            res = dfs_3(nums, j, memos);
            if(res) {
                memos[i] = 1;
                return res;
            }
        }

        memos[i] = 0;
        return res;
    }


    /**
     * Method 4: 贪心，逐渐让 goal 变小
     * */
    public static boolean canJump_4(int[] nums) {
        int n = nums.length;
        int goal = n-1;
        for(int i=n-2;i>=0;i--) {
            if(nums[i]+i >= goal) goal = i;
        }
        return goal == 0;
    }

}
