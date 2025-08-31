import java.util.ArrayDeque;
import java.util.Deque;

public class DesignFrontMiddleBackQueue_1670 {
    public static void main(String[] args) {

    }

    static class FrontMiddleBackQueue {
        private Deque<Integer> first;
        private Deque<Integer> second;

        public FrontMiddleBackQueue() {
            first = new ArrayDeque<>();
            second = new ArrayDeque<>();
        }

        public void pushFront(int val) {
            first.offerFirst(val);
        }

        public void pushMiddle(int val) {
            int totalSize = first.size() + second.size();
            balance();

            // 退出循环后，firstSize 满足要求
            if(totalSize % 2 == 0) second.offerFirst(val);
            else first.offerLast(val);
        }

        public void pushBack(int val) {
            second.offerLast(val);
        }

        public int popFront() {
            if(!first.isEmpty()) return first.pollFirst();
            else if(!second.isEmpty()) return second.pollFirst();
            return -1;
        }

        public int popMiddle() {
            // 先平衡
            int totalSize = first.size() + second.size();
            if(totalSize == 0) return -1;
            balance();
            if(totalSize % 2 == 0) return first.pollLast();
            else return second.pollFirst();

        }

        public int popBack() {
            if(!second.isEmpty()) return second.pollLast();
            else if(!first.isEmpty()) return first.pollLast();
            return -1;
        }

        private void balance() {
            int totalSize = first.size() + second.size();
            // 如果 totalSize 是偶数 4,4
            // 如果 totalSize 是奇数 4,5
            // 向下取整
            int firstSize = totalSize / 2;
            while(first.size() < firstSize) {
                first.offerLast(second.pollFirst());
            }

            while(first.size() > firstSize) {
                second.offerFirst(first.pollLast());
            }
        }
    }

}
