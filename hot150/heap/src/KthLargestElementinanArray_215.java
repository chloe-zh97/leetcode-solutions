import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElementinanArray_215 {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(findKthLargest(nums, k));
    }

    /**
     * Method 1: 大根堆
     * */
    public static int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        // 大根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i=0;i<n;i++)
            pq.offer(nums[i]);
        int ans = 0;
        for(int i=0;i<k;i++) {
            ans = pq.poll();
        }
        return ans;
    }

    /**
     * Method 2: 优化大根堆
     * */
    public static int findKthLargest_2(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for(int i=0;i<n;i++) {
            heap.offer(nums[i]);
            if(heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }


    /**
     * Method 3: QuickSelect
     * */
    public static int findKthLargest_3(int[] nums, int k) {
        List<Integer> array = new ArrayList<>();
        for(int num: nums)
            array.add(num);
        return quickSelect(array, k);
    }

    /**
     * 在 nums 中找倒数第 k 大的元素
     * */
    private static int quickSelect(List<Integer> nums, int k) {
        int pivot = nums.get(0);

        // 将 nums 根据 pivot 的大小关系分成三个序列
        List<Integer> small = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> big = new ArrayList<>();

        for(int num: nums) {
            if(num < pivot) small.add(num);
            else if(num == pivot) equal.add(num);
            else big.add(num);
        }

        if(big.size() >= k) {
            return quickSelect(big, k);
        } else if(equal.size() + big.size() < k) {
            return quickSelect(small, k-(big.size()+equal.size()));
        }
        return pivot;
    }

}
