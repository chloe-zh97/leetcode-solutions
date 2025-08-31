public class ContainerWithMostWater_11 {
    public static void main(String[] args) {
        int[] height = {1,1};
        System.out.println(maxArea(height));
    }

    /**
     * Method 1: 双指针
     * */
    public static int maxArea(int[] height) {
        int n = height.length;
        int i = 0, j = n-1;
        int ans = 0;

        while(i < j) {
            // 更新水容量
            ans = Math.max(ans, Math.min(height[i], height[j]) * (j-i));
            if(height[i] <= height[j]) i++;
            else j--;
        }
        return ans;
    }
}
