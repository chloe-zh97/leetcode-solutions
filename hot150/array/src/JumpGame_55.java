public class JumpGame_55 {
    public static void main(String[] args) {
        int[] nums = {0};
        System.out.println(canJump(nums));
    }

    /**
     * Method 1: dp
     * */
    public static boolean canJump(int[] nums) {
       int n = nums.length;
       // dp[i]: index i 位置能跳到的最远的坐标
       int[] dp = new int[n];
       dp[0] = nums[0];
       for(int i=1;i<n;i++) {
           // 从上一步无法跳到当前位置
           if(dp[i-1] < i) return false;
           dp[i] = Math.max(dp[i-1], i+nums[i]);
       }
       return dp[n-1] >= n-1;
    }

    /**
     * 优化,简化变量
     * */
    public static boolean canJump_2(int[] nums) {
        int n = nums.length;
        int far = 0; // 能跳到的最远距离
        for(int i=0;i<n;i++) {
            if(far < i) return false;
            far = Math.max(far, i+nums[i]);
        }
        return far >= n-1;
    }
}
