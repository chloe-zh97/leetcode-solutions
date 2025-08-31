import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LC132Pattern_456 {
    public static void main(String[] args) {
        int[] nums = {-1,3,2,0};
        System.out.println(find132pattern_2(nums));
    }

    /**
     * Method 1: 前缀和， 不可行！
     * prefix[i]: nums[0...i-1]中比 nums[i] 小的最小数
     * suffix[i]: nums[i+1,..n-1] 中比 nums[i] 小的最大数
     * */
    public static boolean find132pattern(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n+1];
        Arrays.fill(prefix, Integer.MAX_VALUE);
        for(int i=1;i<prefix.length;i++) {
            // prefix[i]: nums[0...i-1]的最小值
            prefix[i] = Math.min(prefix[i-1], nums[i-1]);
        }

        int[] suffix = new int[n+1];
        Arrays.fill(suffix, Integer.MIN_VALUE);
        for(int i=n-1;i>=0;i--) {
            // suffix[i]: nums[i...n-1]的最小值
            suffix[i] = Math.max(suffix[i+1], nums[i]);
        }
        int ans = 0;
        // 枚举中间值 j
        for(int j=1;j<n-1;j++) {
            int left = prefix[j];
            int mid = nums[j];
            int right = suffix[j+1];
            System.out.println("left="+left+" mid="+mid+" right="+right);
            if(prefix[j] < suffix[j+1] && nums[j] > right) {
                ans++;
            }
        }
        return ans > 0;
    }

    /**
     * Method 2: 单调栈,132
     * 维护一个单调递减的栈，使用 k 记录出栈元素的最大值
     * 如果有 nums[i] < k, 找到了符合条件的 i,j,k
     * 3,1,4,2
     * 从右往左遍历
     *
     * 栈中维护的是3, maxK 维护的是2, 枚举的是1
     * */
    public static boolean find132pattern_2(int[] nums) {
        int n = nums.length;
        Deque<Integer> queue = new ArrayDeque<>();
        int maxK = Integer.MIN_VALUE;

        for(int i=n-1;i>=0;i--) {
            if(nums[i] < maxK) return true;

            // queue 中比 nums[i] 小的全部出队列，更新 maxK
            while(!queue.isEmpty() && queue.peekLast() < nums[i]) {
                maxK = Math.max(queue.pollLast(), maxK);
            }

            queue.offerLast(nums[i]);
        }
        return false;
    }
}
