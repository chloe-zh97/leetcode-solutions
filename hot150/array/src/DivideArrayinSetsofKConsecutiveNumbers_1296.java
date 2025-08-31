import java.util.HashMap;
import java.util.PriorityQueue;

public class DivideArrayinSetsofKConsecutiveNumbers_1296 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,3,4,4,5,6};
        int k = 4;
        System.out.println(isPossibleDivide(nums, k));
    }

    public static boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if(n % k != 0) return false;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums)
            map.merge(num, 1, Integer::sum);

        //最小堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a-b);
        for(int key: map.keySet()) pq.offer(key);

        while(!pq.isEmpty()) {
            // start 的位置
            int start = pq.peek();
            for(int i=start;i<start+k;i++) {
                if(!map.containsKey(i)) return false;

                map.merge(i, -1, Integer::sum);
                if(map.get(i) == 0) {
                    if(pq.peek() != i) return false;
                    pq.poll();
                    map.remove(i);
                }
            }
        }
        return true;
    }
}
