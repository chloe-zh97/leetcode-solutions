import java.util.Arrays;

public class MaxPairSuminanArray_2815 {
    public static void main(String[] args) {
        int[] nums = {51,71,17,24,42};
        System.out.println(maxSum_2(nums));
    }

    public static int maxSum(int[] nums) {
        int[][] map = new int[10][10005];
        for(int i=0;i<nums.length;i++) {
            int d = findMaxDigit(nums[i]);
            map[d][nums[i]]++;
        }

        int ans = -1;
        for(int i=0;i<map.length;i++) {
            int cnt = 0, sum = 0;
            for(int j=map[i].length-1;j>=0 && cnt < 2;j--) {
                while(map[i][j] > 0 && cnt < 2) {
                    sum += j;
                    cnt++;
                    map[i][j]--;
                }
            }
            if(cnt == 2) ans = Math.max(ans, sum);
        }

        return ans;
    }

    /**
     * 找到 x 的最大 digit
     * */
    private static int findMaxDigit(int x) {
        int ans = 0;
        while(x > 0) {
            ans = Math.max(ans, x%10);
            x/=10;
        }
        return ans;
    }

    /**
     * Method 2: 优化
     * */
    public static int maxSum_2(int[] nums) {
        // 保存各个 digit 的最大数
        int[] maxVal = new int[10];
        Arrays.fill(maxVal, Integer.MIN_VALUE);
        int ans = -1;

        for(int i=0;i< nums.length;i++) {
            int v = nums[i];
            // 计算最大的 digit
            int maxD = 0;
            for(int x=v;x>0;x/=10) {
                maxD = Math.max(maxD, x%10);
            }
            ans = Math.max(ans, maxVal[maxD]+v);
            maxVal[maxD] = Math.max(maxVal[maxD], v);
        }
        return ans;
    }
}
