import java.util.PriorityQueue;

public class FindMedianFromDataStream_65 {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());

        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());

        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
    }

    /**
     * Method 1: 最大堆+最小堆
     *
     * 1,2,3 最大堆
     * 5,6 最小堆
     *
     * 1. 每次添加元素，都加入最大堆
     * 2. 如果最大堆的最大的元素 > 最小堆的最小元素，将最大堆的最大的元素不断放入
     * 最小堆中
     * 3. 平衡个数，保持最大堆的元素 = 最小堆中的元素，或者 +1
     *
     * Case 1:
     * 最大堆 1
     * 最小堆
     *
     * 最大堆 1
     * 最小堆 2
     *
     * */
    static class MedianFinder {
        private PriorityQueue<Integer> maxHeap;

        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            // initial
            maxHeap = new PriorityQueue<>((a,b) -> b-a);
            minHeap = new PriorityQueue<>((a,b) -> a-b);
        }

        public void addNum(int num) {
            // 加入最大堆
            maxHeap.offer(num);

            while(!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                minHeap.offer(maxHeap.poll());
            }

            while(maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }

            while(minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            int n = minHeap.size() + maxHeap.size();
            if(n == 0) return 0;

            if(n % 2 == 0) {
                return (minHeap.peek() + maxHeap.peek()) /2.0;
            } else {
                return maxHeap.peek();
            }
        }
    }
}
