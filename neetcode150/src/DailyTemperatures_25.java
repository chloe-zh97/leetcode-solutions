import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperatures_25 {
    public static void main(String[] args) {
        int[] temperatures = {22,21,20};
        int[] ans = dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * Method 1: 单调栈
     * deque 中降序存储，如果当前 temperature > peek
     * pee 对应的元素更新，然后弹出，当前的 temperature 再入栈
     *
     * stack: 40, 28
     * */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];

        // 存储 index
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i=0;i<n;i++) {
            int cur = temperatures[i];
            while(!deque.isEmpty() && temperatures[deque.peekLast()] < cur) {
                // 当前的温度大于栈顶的元素温度
                ans[deque.peekLast()] = i - deque.pollLast();
            }
            deque.offerLast(i);
        }
        return ans;
    }
}
