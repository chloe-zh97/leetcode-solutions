import java.util.*;

public class SlidingWindowMaximum_20 {
    public static void main(String[] args) {
        int[] nums = {1,-1};
        int k = 1;
        System.out.println(Arrays.toString(maxSlidingWindow_4(nums, k)));
    }

    /**
     * Method 1: 暴力法 O(n^2)
     * */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        int j = 0;
        int[] ans = new int[n-k+1];

        int i = 0;
        while(i < n) {
            // element in
            if(i<k-1) {
                i++;
                continue;
            }

            // 达到滑动窗口的值
            ans[j++] = findMax(nums, i-k+1,i);

            // element out
            i++;
        }
        return ans;
    }

    /**
     * 在 nums[left...right] 中寻找最大值
     * */
    private static int findMax(int[] nums, int left, int right) {
        int max = 0;
        for(int i=left;i<=right;i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    /**
     * Method 2: TreeMap O(nlogn)
     * */
    public static int[] maxSlidingWindow_2(int[] nums, int k) {
        int n = nums.length;
        // val, index
        TreeMap<Integer, Integer> map = new TreeMap<>((a,b)->b-a);

        int t = 0;
        int[] ans = new int[n-k+1];

        int i = 0, j = 0;
        while(j < n) {
            // element in
            // O(logn)
            map.put(nums[j], j);

            if(j-i+1 < k) {
                j++;
                continue;
            }

            // window size reach k
            while(map.firstEntry().getValue() < i) {
                map.pollFirstEntry();
            }

            ans[t++] = map.firstKey();

            j++;
            i++;
        }
        return ans;
    }


    /**
     * Method 3: Priority Queue
     * O(nlogn)
     * */
    public static int[] maxSlidingWindow_3(int[] nums, int k) {
        int n = nums.length;
        // {val, index}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0] - a[0]);

        int i = 0;
        int[] ans = new int[n-k+1];

        int j = 0;
        // O(n)
        while(j < n) {
            // element in
            int[] cur = {nums[j], j};
            // O(logn)
            pq.offer(cur);

            if(j<k-1) {
                j++;
                continue;
            }

            // 滑动窗口 reach size

            // O(logn)
            while(!pq.isEmpty() && pq.peek()[1] < j-k+1) {
                // 最大值不在滑动窗口范围内
                pq.poll();
            }

            // 最大值在范围内
            ans[i++] = pq.peek()[0];
            j++;
        }
        return ans;
    }


    /**
     * Method 4: Monotonic Queue + Sliding window
     * 待加入元素 nums[i], 直接加入滑动窗口
     *
     * 加入 mq, 弹出所有比它小的元素，再加入
     *
     * poll 最大的元素
     *
     * */
    public static int[] maxSlidingWindow_4(int[] nums, int k) {
        int n = nums.length;
        // 存储的是最大值的 index
        Deque<Integer> deque = new ArrayDeque<>();

        int i = 0;
        int[] ans = new int[n-k+1];

        // sliding window
        int j = 0;
        while(j < n) {
            // element in, add element to deque
            while(!deque.isEmpty() && nums[j] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            // 当前元素 <= 队列末尾元素了，或者队列空了
            deque.offerLast(j);

            if(j<k-1) {
                j++;
                continue;
            }

            // 队首已经离开了滑动窗口
            if(deque.peekFirst() < j-k+1) deque.pollFirst();

            // 到此为止，滑动窗口的 size = k
            // mq 和 sliding window 的移动是同步的
            ans[i++] = nums[deque.peekFirst()];
            j++;
        }
        return ans;
    }

}
