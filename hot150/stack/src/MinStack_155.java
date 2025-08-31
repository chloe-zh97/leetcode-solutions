import java.util.ArrayDeque;
import java.util.Deque;
/**
 * Deque 中存储的是 int[], 纬度为2的数组，val, min(getMin(), val)
 * */
class MinStack {

    Deque<int[]> st;

    public MinStack() {
        st = new ArrayDeque<>();
        st.push(new int[]{0, Integer.MAX_VALUE});
    }

    public void push(int val) {
        st.push(new int[]{val, Math.min(getMin(), val)});
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek()[0];
    }

    public int getMin() {
        return st.peek()[1];
    }
}

public class MinStack_155 {
    public static void main(String[] args) {

    }
}
