public class JumpGameII_45 {
    public static void main(String[] args) {
        int[] nums = {2,3,0,1,4};
        System.out.println(jump(nums));
    }

    public static int jump(int[] nums) {
        int n = nums.length;
        // curRight 表示当前桥的最远端，nextRight 表示下一座桥的最远
        int curRight=0, nextRight = 0;
        int jump = 0;
        for(int i=0;i<n-1;i++) {
            // 动态记录当前能走到的最远端
            nextRight = Math.max(nextRight, i+nums[i]);

            // 走到了尽头
            if(i == curRight) {
                if(i == nextRight) return -1;
                curRight = nextRight;
                jump++;
            }
        }
        return jump;
    }
}
