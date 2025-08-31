import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumNumberofRobotsWithinBudget_2398 {
    public static void main(String[] args) {
        int[] chargeTimes = {11,12,19};
        int[] runningCosts = {10,8,7};
        long budget = 19;
        System.out.println(maximumRobots(chargeTimes, runningCosts, budget));
    }

    /**
     * Method 1: Sliding window + monotic queue
     * */
    public static int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        Deque<Integer> deque = new ArrayDeque<>();

        int i = 0, j = 0, ans = 0;
        long totalRunningCosts = 0L;

        while(j < n) {
            // element in
            while(!deque.isEmpty() && chargeTimes[j] > chargeTimes[deque.peekLast()]) {
                // 当前充电时间更大，要把 deque 里的东西 pop 出来
                deque.pollLast();
            }
            deque.offerLast(j);

            totalRunningCosts += runningCosts[j];

            while(!deque.isEmpty() && totalRunningCosts*(j-i+1) + chargeTimes[deque.peekFirst()] > budget) {
                // invalid, shrink
                while(deque.peekFirst() < i) deque.pollFirst();

                // 到这里 deque 中的元素都已经在滑动窗口中
                int left = deque.peekFirst();
                if(left == i) {
                    // 删除的元素恰好是最大的充电元素
                    deque.pollFirst();
                }

                totalRunningCosts -= runningCosts[i];
                i++;
            }

            // 退出循环时, 满足条件
            ans = Math.max(ans, j-i+1);
            j++;
        }
        return ans;
    }
}
