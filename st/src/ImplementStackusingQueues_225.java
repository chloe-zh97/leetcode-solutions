import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class ImplementStackusingQueues_225 {
    public static void main(String[] args) {

    }

    /**
     * Method 1: 不用双端队列
     * */
    static class MyStack {
        private Queue<Integer> queue;

        public MyStack() {
            queue = new ArrayDeque<>();
        }

        public void push(int x) {
            int n = queue.size();
            queue.offer(x);
            for(int i=0;i<n;i++)
                queue.offer(queue.poll());
        }

        public int pop() {
            return queue.isEmpty() ? -1 : queue.poll();
        }

        public int top() {
            return queue.isEmpty() ? -1 : queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
