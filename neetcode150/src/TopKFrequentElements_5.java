import java.util.*;

public class TopKFrequentElements_5 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3,3,3};
        int k = 2;
        nums = topKFrequent_3(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * Method 1: Hashmap O(nlogn)
     * num, int[]{num, freq}
     * */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, int[]> map = new HashMap<>();

        // O(n)
        for(int num: nums) {
            int[] a = map.getOrDefault(num, new int[]{num, 0});
            a[1] += 1;
            map.put(num, a);
        }

        List<int[]> freqs = new ArrayList<>(map.values());

        // 按照频率从大到小排列, O(nlogn)
        freqs.sort((a, b) -> b[1] - a[1]);

        int[] ans = new int[k];
        for(int i=0;i<ans.length;i++)
            ans[i] = freqs.get(i)[0];
        return ans;
    }


    /**
     * Method 2:Hashtable + PriorityQueue
     *
     * */
    public static int[] topKFrequent_2(int[] nums, int k) {
        // 最小堆
        final int N = 2005;
        final int OFFSET = 1000;

        // 数组范围 -1000，1000
        int[] cnt = new int[N];

        for(int i=0;i<nums.length;i++) {
            int num = nums[i] + OFFSET;
            cnt[num] += 1;
        }

        // 最小堆, 按照频数从小到大出队列
        // O(nlogk)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for(int i=0;i<cnt.length;i++) {
            int num = i-OFFSET;
            int[] item = {num, cnt[i]};
            pq.offer(item);

            // 超过则出堆
            if(pq.size() > k) pq.poll();
        }

        // 留下的是答案
        int[] ans = new int[k];
        for(int i=0;i<k;i++)
            ans[i] = pq.poll()[0];

        return ans;
    }

    /**
     * Method 3: Bucket sort
     * count(i)
     * num
     * */
    public static int[] topKFrequent_3(int[] nums, int k) {
        // 首先用HashMap 统计各个元素出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums) {
            map.merge(num, 1, Integer::sum);
        }

        // 一共有 n 个元素，那么一个元素最多出现的次数是 n 次
        int n = nums.length;

        // freq[i]: 出现 i 次的元素都有哪些
        List<Integer>[] freqs = new ArrayList[n+1];
        for(int i=0;i<freqs.length;i++)
            freqs[i] = new ArrayList<>();


        // 遍历 map
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            freqs[freq].add(num);
        }

        int[] ans = new int[k];

        // 从后向前遍历
        for(int i=freqs.length-1,j=0;i>=0;i--) {
            List<Integer> items = freqs[i];
            for(int t=0;t<items.size() && j<k;t++,j++)
                ans[j] = items.get(t);
        }
        return ans;
    }

}
