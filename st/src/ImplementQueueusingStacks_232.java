import java.util.Stack;

public class ImplementQueueusingStacks_232 {
    public static void main(String[] args) {

    }

    /**
     * 1,2,3,4
     * Method 1: 两个栈，stack1 和 stack2
     * push 的时候首先把 stack2 的元素全部倒入 stack1, 然后加入 x 到 stack1
     * pop 的时候如果 stack2 有元素直接 pop, 如果没有，将stack1的全部倒入 stack2再 pop
     * */
    class MyQueue {

        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            while(!stack2.isEmpty())
                stack1.push(stack2.pop());

            stack1.push(x);
        }

        public int pop() {
            if(!stack2.isEmpty()) return stack2.pop();

            while(!stack1.isEmpty())
                stack2.push(stack1.pop());

            return stack2.isEmpty() ? -1 : stack2.pop();
        }

        public int peek() {
            if(!stack2.isEmpty()) return stack2.peek();

            while(!stack1.isEmpty())
                stack2.push(stack1.pop());

            return stack2.isEmpty() ? -1 : stack2.peek();
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.empty();
        }
    }
}
