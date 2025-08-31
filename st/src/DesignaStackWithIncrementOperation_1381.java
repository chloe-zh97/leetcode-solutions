public class DesignaStackWithIncrementOperation_1381 {
    public static void main(String[] args) {
        CustomStack customStack = new CustomStack(3);
        customStack.push(1);
        customStack.push(2);
        int v1 = customStack.pop();
        customStack.push(2);
        customStack.push(3);
        customStack.push(4);

        customStack.increment(5,100);
        customStack.increment(2,100);

        customStack.pop();
        customStack.pop();
        customStack.pop();
        customStack.pop();
    }

    static class CustomStack {

        private int[] stack;
        private int top;

        public CustomStack(int maxSize) {
            stack = new int[maxSize];
            top = -1;
        }

        public void push(int x) {
            if(top == stack.length - 1) return;
            top++;
            stack[top] = x;
        }

        public int pop() {
            if(top < 0) return -1;
            return stack[top--];
        }

        public void increment(int k, int val) {
            int limit = Math.min(k, top+1);
            for(int i=0;i<limit;i++)
                stack[i] += val;
        }
    }


    /**
     * Method 2: 元素的增加值加在最后一个元素上
     * */
    static class CustomStack_2 {
        private int[] stack;
        private int[] add;
        private int top = -1;

        public CustomStack_2(int maxSize) {
            stack = new int[maxSize];
            add = new int[maxSize];
        }

        public void push(int x) {
           if(top == stack.length-1) return;
           top++;
           stack[top] = x;
        }

        public int pop() {
            // 空栈
            if(top == -1) return -1;

            int res = stack[top] + add[top];

            if(top > 0)
                add[top-1] += add[top];

            add[top] = 0;
            top--;

            return res;
        }

        public void increment(int k, int val) {
            // 只加在最后一个元素上
            int limit = Math.min(k-1, top);
            if(limit >= 0)
                add[limit] += val;
        }
    }
}
