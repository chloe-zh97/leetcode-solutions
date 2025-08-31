public class TrappingRainWater_42 {
    public static void main(String[] args) {
        int[] height = {4,2,0,3,2,5};
        System.out.println(trap_3(height));
    }

    /**
     * Method 1: 动态规划
     * */
    public static int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for(int i=1;i<n;i++)
            leftMax[i] = Math.max(leftMax[i-1], height[i]);

        int[] rightMax = new int[n];
        rightMax[n-1] = height[n-1];
        for(int i=n-2;i>=0;i--)
            rightMax[i] = Math.max(rightMax[i+1], height[i]);

        int ans = 0;
        for(int i=0;i<n;i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }


    /**
     * Method 2: 找到最高柱子，从左往右，从右往左两遍遍历
     * */
    public static int trap_2(int[] height) {
        int n = height.length;
        int maxHeight = 0, maxPos = -1;
        for(int i=0;i<n;i++) {
            if(height[i] > maxHeight) {
                maxHeight = height[i];
                maxPos = i;
            }
        }

        int ans = 0;
        int curHeight = 0;
        for(int i=0;i<maxPos;i++) {
            // 当前最高值
            curHeight = Math.max(curHeight, height[i]);
            ans += curHeight - height[i];
        }

        // 从右边向左边遍历
        curHeight = 0;
        for(int i=n-1;i>maxPos;i--) {
            curHeight = Math.max(curHeight, height[i]);
            ans += curHeight - height[i];
        }

        return ans;
    }

    /**
     * Method 3: 优化 dp, 用 maxLeft 和 maxRight 两个变量代替数组，存储左边和右边的最大值
     * */
    public static int trap_3(int[] height) {
       int n = height.length;
       // 双指针
       int left = 0, right = n-1;
       int maxLeft = 0, maxRight = 0;
       int ans = 0;
       while(left < right) {
           maxLeft = Math.max(maxLeft, height[left]);
           maxRight = Math.max(maxRight, height[right]);
           if(height[left] < height[right]) {
               // 此时一定有 maxLeft < maxRight
               ans += maxLeft - height[left];
               left++;
           } else {
               ans += maxRight - height[right];
               right--;
           }
       }
       return ans;
    }
}
