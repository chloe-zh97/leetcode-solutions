public class TrappingRainWater_14 {
    public static void main(String[] args) {
        int[] height = {0,2,0,3,1,0,1,3,2,1};
        System.out.println(trap_2(height));
    }

    /**
     * Method 1: int[] leftMax, int[] rightMax
     * 分别存储左右的最大值
     * */
    public static int trap(int[] height) {
        int n = height.length;
        // left[i+1]: height[0...i]的最大值
        int[] left = new int[n+1];
        for(int i=0;i<n;i++) {
            left[i+1] = Math.max(left[i], height[i]);
        }

        // right[i]: height[i...n-1]的最大值
        int[] right = new int[n+1];
        for(int i=n-1;i>=0;--i) {
            right[i] = Math.max(right[i+1], height[i]);
        }

        int ans = 0;
        for(int i=0;i<n;i++) {
            int bound = Math.min(left[i], right[i+1]);
            ans += Math.max(0, bound-height[i]);
        }
        return ans;
    }

    /**
     * Method 2: 双指针
     * */
    public static int trap_2(int[] height) {
       int n = height.length;
       int ans = 0;
       int i = 0, j = n - 1;
       int leftMax = height[i], rightMax = height[j];
       while(i < j) {
           if(leftMax <= rightMax) {
               ++i;
               leftMax = Math.max(leftMax, height[i]);
               ans += leftMax-height[i];
           } else {
               // 右边更小
               --j;
               rightMax = Math.max(rightMax, height[j]);
               ans += rightMax-height[j];
           }
       }
       return ans;
    }
}
