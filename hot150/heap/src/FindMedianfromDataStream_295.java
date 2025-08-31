import java.util.PriorityQueue;

public class FindMedianfromDataStream_295 {
    public static void main(String[] args) {

    }

    class MedianFinder {

        // left 大堆
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public MedianFinder() {
            left = new PriorityQueue<>((o1, o2) -> o2 - o1);
            right = new PriorityQueue<>((o1, o2) -> o1 - o2);
        }

        /**
         * 时刻保证 left 的元素个数 >= right 的元素个数，left 的最大值 <= right 的最小值。
         * 如果 left 和 right 的元素个数相等，num 加入到 right 中，然后把 right 最小的元素加入到 left 中。
         * 如果 left 比 right 的元素个数多1， num 加入到 left 中，然后把 left 最大的元素加入到 right 中。
         * */
        public void addNum(int num) {
            if(left.size() == right.size()) {
                right.offer(num);
                int p = right.poll();
                left.offer(p);
            } else if(left.size() > right.size()) {
                left.offer(num);
                int p = left.poll();
                right.offer(p);
            }
        }

        public double findMedian() {
            int c = (left.size() + right.size()) % 2;
            if(c == 1) return left.peek();
            return (left.peek() + right.peek())/2.0;
        }
    }
}
