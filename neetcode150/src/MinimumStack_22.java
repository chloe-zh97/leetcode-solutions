import java.util.ArrayList;
import java.util.List;

public class MinimumStack_22 {
    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(10);
        ms.pop();
        ms.push(20);
        System.out.println(ms.top());
        ms.push(-20);
        System.out.println(ms.getMin());

    }
    // 10
    // (10,10)
    static class MinStack {
        // {val, curMin}
        private List<int[]> array;
        // 指向栈顶
        private int top = -1;

        public MinStack() {
            array = new ArrayList<>();
        }

        public void push(int val) {
            if(isEmpty()) {
                array.add(new int[]{val, val});
            } else {
                // 获取栈顶元素
                int[] prev = array.get(top);
                int[] cur = {val, Math.min(val, prev[1])};
                array.add(cur);
            }
            top++;
        }

        public void pop() {
            if(isEmpty()) return;
            array.remove(top);
            top--;
        }

        public int top() {
            return array.get(top)[0];
        }

        public int getMin() {
            return array.get(top)[1];
        }

        private boolean isEmpty() {
            return top == -1;
        }
    }
}
