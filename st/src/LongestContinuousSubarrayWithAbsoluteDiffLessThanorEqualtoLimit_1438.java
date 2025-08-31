import java.util.ArrayDeque;
import java.util.Deque;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit_1438 {
    public static void main(String[] args) {
        int[] nums = {4,2,2,2,4,4,2,2};
        int limit = 0;
        System.out.println(longestSubarray(nums, limit));
    }

    /**
     * Method 1: Sliding window + deque
     * */
    public static int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int ans = 0;
        int j = 0, i = 0;

        // O(n)
        while(j < n) {
            // element in, add to two queues
            while(!maxDeque.isEmpty() && nums[maxDeque.peekLast()] < nums[j]) {
                // 待加入的元素更大
                maxDeque.pollLast();
            }
            maxDeque.offerLast(j);

            while(!minDeque.isEmpty() && nums[minDeque.peekLast()] > nums[j]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(j);

            // 走到这里，两个队列一定不为空，所以可以直接取 peek
            while(!maxDeque.isEmpty() && maxDeque.peekFirst() < i) maxDeque.pollFirst();
            while(!minDeque.isEmpty() && minDeque.peekFirst() < i) minDeque.pollFirst();

            // check
            if(nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                // 需要改变左端点
                int l1 = maxDeque.peekFirst();
                int l2 = minDeque.peekFirst();
                // 那个更小 pop 哪个
                if(l1 < l2) maxDeque.pollFirst();
                else minDeque.pollFirst();
                i = Math.min(l1, l2)+1;
            } else {
                ans = Math.max(ans, j-i+1);
            }

            j++;
        }
        return ans;
    }
}
