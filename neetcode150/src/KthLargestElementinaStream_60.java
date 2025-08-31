import java.util.PriorityQueue;

public class KthLargestElementinaStream_60 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3};
        KthLargest kthLargest = new KthLargest(3, nums);

        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(6));
        System.out.println(kthLargest.add(7));
        System.out.println(kthLargest.add(8));

    }

    static class KthLargest {

        private int capacity;
        private PriorityQueue<Integer> pq;

        /**
         * 容量为 k 的最小堆
         * */
        public KthLargest(int k, int[] nums) {
            pq = new PriorityQueue<>(k);
            capacity = k;

            for(int num: nums) {
                pq.offer(num);
                if(pq.size() > capacity) pq.poll();
            }
        }

        public int add(int val) {
            pq.offer(val);
            if(pq.size() > capacity) pq.poll();
            return pq.peek();
        }
    }
}
