import java.util.ArrayDeque;
import java.util.Deque;

public class MovingAveragefromDataStream_346 {
    public static void main(String[] args) {
        MovingAverage_2 mv = new MovingAverage_2(3);
        System.out.println(mv.next(1));
        System.out.println(mv.next(10));
        System.out.println(mv.next(3));
        System.out.println(mv.next(5));
    }

    static class MovingAverage {
        private Deque<Integer> deque;
        private int sum = 0;
        private int cnt = 0;
        private int size;

        public MovingAverage(int size) {
            this.size = size;
            deque = new ArrayDeque<>(size);
        }

        public double next(int val) {
            if(cnt < size) {
                // 没满，直接加入
                cnt++;
                sum += val;
                deque.offerLast(val);
            } else {
                int deleted = deque.pollFirst();
                sum -= deleted;
                deque.offerLast(val);
                sum += val;
            }

            return 1.0* sum / cnt;
        }
    }


    /**
     * Method 2: 优化，用循环数组代替双向队列
     * */
    static class MovingAverage_2 {
        private int[] nums;
        private int cnt = 0;
        private int sum = 0;
        public MovingAverage_2(int size) {
            nums = new int[size];
        }

        public double next(int val) {
            int id = cnt % nums.length;
            sum = sum - nums[id] + val;
            nums[id] = val;
            cnt++;
            return 1.0* sum / Math.min(cnt, nums.length);
        }
    }
}
