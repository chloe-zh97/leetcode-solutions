import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestElementinanArray_62 {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,5,5,4};
        int k = 3;
        System.out.println(findKthLargest_2(nums, k));
    }

    /**
     * 最小堆
     * */
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int num: nums) {
            pq.offer(num);
            if(pq.size() > k) pq.poll();
        }

        return pq.poll();
    }

    /**
     * Method 2: Quick Sort
     * 找第 K 大的元素，从小到大排序，从左往右 n-K 为第 K 大的元素，K 为
     *
     * partition() 返回一个 index, 这个数已经在正确的位置上
     * */

    private static Random random = new Random();

    public static int findKthLargest_2(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = n-1;

        while(true) {
            // 0,1,2,3,4,5
            // 假设 k = 3, 要返回3，pivot = 3
            // 假设 k = 2, 要返回4, pivot = 4
            int j = partition(nums, left, right);

            // System.out.println("pivot="+pivot+" n-k="+(n-k)+" left="+left+" right="+right);
            if(j == n-k) return nums[j];
            else if(j < n-k) left = j + 1;
            else right = j - 1;
        }
    }

    /**
     * 每调用一次 partition, 就有一个数字调整到了正确的位置
     * 将 nums[left, right] 的范围调整
     * */
    private static int partition(int[] nums, int left, int right) {
        // 确定一个随机位置的数
        // 取数的范围是 [left, right]
        int randomIndex = left + random.nextInt(right-left+1);

        // 将这个随机数放在最前面
        swap(nums, left, randomIndex);

        int pivot = nums[left];

        // [left+1, pivot-1] 的数 <= nums[pivot], [pivot+1, right] 的数 >= nums[pivot]
        int i = left+1, j = right;

        while(true) {
            // 从左到右找第一个 >= nums[pivot]的数
            while(i<=j && nums[i] < pivot) i++;
            // 退出循环时，要么 i==j, 要么 nums[i] > nums[pivot]

            while(i<=j && nums[j] > pivot) j--;
            // 退出循环时，i==j 或者 nums[j] < nums[pivot]

            if(i >= j) break;

            swap(nums, i, j);
            i++;
            j--;
            // System.out.println("i="+i+" j="+j);
        }

        // 将 left 和 i 的位置交换，放入正确的位置
        swap(nums, left, j);

        return j;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


}
