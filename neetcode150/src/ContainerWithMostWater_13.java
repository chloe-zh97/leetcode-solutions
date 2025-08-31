public class ContainerWithMostWater_13 {
    public static void main(String[] args) {
        int[] heights = {2,2,2};
        System.out.println(maxArea(heights));
    }

    /**
     * Method 1: two pointers
     * */
    public static int maxArea(int[] heights) {
        int n = heights.length;
        int i = 0, j = n-1;
        int ans = 0;

        while(i < j) {
            ans = Math.max(ans, Math.min(heights[i], heights[j]) * (j-i));
            if(heights[i] <= heights[j]) {
                i++;
            } else {
                j--;
            }
        }

        return ans;
    }
}
