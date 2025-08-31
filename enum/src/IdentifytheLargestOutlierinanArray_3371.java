import java.util.Arrays;
import java.util.HashMap;

public class IdentifytheLargestOutlierinanArray_3371 {
    public static void main(String[] args) {
        int[] nums = {-2,-1,-3,-6,4};
        System.out.println(getLargestOutlier_2(nums));
    }

    /**
     * Method 1: 枚举
     * 假设异常值为 x, 元素和为 y, 剩余 n-2 个数的和也是 y
     * total = x + 2y
     * 求最大的 x 是多少
     * */
    public static int getLargestOutlier(int[] nums) {
        // 记录每个数出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        int total = 0;
        for(int num: nums) {
            map.merge(num, 1, Integer::sum);
            total += num;
        }

        int ans = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++) {
            int x = nums[i];
            map.merge(x, -1, Integer::sum);

            if((total-x)%2==0 && map.getOrDefault((total-x)/2, 0) > 0) {
                // 剩下的数含 y
                ans = Math.max(ans, x);
            }
            // 还原
            map.merge(x, 1, Integer::sum);
        }
        return ans;
    }


    /**
     * Method 2: 双指针
     * */
    public static int getLargestOutlier_2(int[] nums) {
        Arrays.sort(nums);
        int total = 0;
        for(int num: nums) total += num;

        // 不应该设置为 left < right 返回
        int left = 0, right = nums.length-1;
        while(left < nums.length && right >= 0) {
            int outlier = nums[right];
            int sum = nums[left];
            if(outlier+2*sum == total && left != right) return outlier;
            else if(outlier+2*sum < total) ++left;
            else --right;
        }
        return nums[left];
    }
}
