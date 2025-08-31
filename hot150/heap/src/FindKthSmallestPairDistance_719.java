import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FindKthSmallestPairDistance_719 {
    public static void main(String[] args) {
        int[] nums = {1,6,1};
        int k = 3;
        System.out.println(smallestDistancePair_4(nums, k));
    }

    /**
     * Method 1: 最小堆，超出内存
     * */
    public static int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        // 最小堆 abs
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        List<Integer> ans = new ArrayList<>();

        // 将 nums[i] - nums[i-1] 的 abs 入堆
        for(int i=1;i<Math.min(n, k+1);i++)
            pq.offer(new int[] {nums[i]-nums[i-1], i-1, i});

        while(ans.size() < k) {
            int[] cur = pq.poll();
            int val = cur[0], i = cur[1], j = cur[2];
            ans.add(val);
            if(j+1 < n) pq.offer(new int[] {nums[j+1]-nums[i], i, j+1});
        }

        return ans.getLast();
    }


    /**
     * Method 2: 优化，去掉 ans list, 超时
     * */
    public static int smallestDistancePair_2(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        // 最小堆 abs
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        // 将 nums[i] - nums[i-1] 的 abs 入堆
        for(int i=1;i<Math.min(n, k+1);i++)
            pq.offer(new int[] {nums[i]-nums[i-1], i-1, i});

        int cnt = 0, ans = 0;
        while(cnt < k) {
            int[] cur = pq.poll();
            cnt++;

            int val = cur[0], i = cur[1], j = cur[2];
            ans = val;
            if(j+1 < n) pq.offer(new int[] {nums[j+1]-nums[i], i, j+1});
        }
        return ans;
    }

    /**
     * Method 3: 二分
     * */
    public static int smallestDistancePair_3(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0, right = nums[n-1]-nums[0];

        // O(logn)
        while(left < right) {
            int mid = left + right >> 1;
            if(check(nums, k, mid)) right = mid;
            else left = mid + 1;
        }

        return left;
    }

    private static boolean check(int[] nums, int k, int threshold) {
        // 统计绝对值差小于等于 threshold 的有多少个
        int cnt = 0;
        for(int i=1;i<nums.length;i++) {
            // nums[i] - x <= threshold
            // x >= nums[i] - threshold
            int j = binarySearch(nums, nums[i]-threshold);

            // 左端点取值 [j,j+1,...,i-1]
            if(j!=-1) cnt += i-j;
            if(cnt >= k) return true;
        }
        return cnt >= k;
    }

    // 在 nums 中查找第一个 >= val 的元素位置
    private static int binarySearch(int[] nums, int val) {
        int left = 0, right = nums.length-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] < val) left = mid + 1;
            else right = mid;
        }
        return nums[left] >= val ? left : -1;
    }

    /**
     * Method 4: 二分+滑动窗口
     * */
    public static int smallestDistancePair_4(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0, right = nums[n-1]-nums[0];

        while(left < right) {
            int mid = left + right >> 1;
            if(check_2(nums, k, mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private static boolean check_2(int[] nums, int k, int val) {
        int n = nums.length;
        int i=0, j=0, cnt = 0;
        while(i < n) {
            while(j < n && nums[j] - nums[i] <= val) j++;
            // 退出循环时, nums[j] - nums[i] > val, j-1 符合要
            // 右端点取值, [i+1,i+2,...,j-1]
            cnt += j-i-1;
            if(cnt >= k) return true;
            i++;
        }
        return cnt >= k;
    }
}
