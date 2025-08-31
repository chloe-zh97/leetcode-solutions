import java.util.*;

public class FindKPairswithSmallestSums_373 {
    public static void main(String[] args) {
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        int k = 3;
        List<List<Integer>> res = kSmallestPairs_2(nums1, nums2, k);
        for(List<Integer> a: res) {
            System.out.println(a);
        }
    }

    /**
     * Method 1: 最大堆，超时
     * */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;

        // 最大堆
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o2[0]+o2[1]) - (o1[0]+o1[1]));

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                pq.offer(new int[]{nums1[i], nums2[j]});
                if(pq.size() > k) pq.poll();
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        while(!pq.isEmpty()) {
            int[] a = pq.poll();
            List<Integer> row = Arrays.stream(a).boxed().toList();
            res.add(row);
        }
        return res;
    }

    /**
     * Method 2: 最小堆，记录 (i,j) 可能的最小堆的下标，下一组入堆的元素为 (i+1,j) 或者 (i,j+1)
     * */
    public static List<List<Integer>> kSmallestPairs_2(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        List<List<Integer>> ans = new ArrayList<>(k);

        // 初始化最小堆
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        for(int i=0;i<Math.min(m, k);i++) {
            int[] p = new int[] {nums1[i]+nums2[0], i, 0};
            pq.offer(p);
        }

        while(ans.size() < k) {
            int[] p = pq.poll();
            int i = p[1], j = p[2];
            ans.add(Arrays.asList(nums1[i],nums2[j]));
            // 入堆
            if(j+1 < n) pq.offer(new int[]{nums1[i]+nums2[j+1], i, j+1});
        }
        return ans;
    }
}
