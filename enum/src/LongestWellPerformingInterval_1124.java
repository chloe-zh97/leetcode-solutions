import java.util.ArrayDeque;
import java.util.Deque;

public class LongestWellPerformingInterval_1124 {
    public static void main(String[] args) {
        int[] hours = {6,6,9};
        System.out.println(longestWPI(hours));
    }


    /**
     * Method 1: 单调栈 90.39%
     * tired day 视作 +1， non-tired day 视为 -1，s为前缀和
     * 假设 i < j, 需要满足 s[j] - s[i] > 0
     * 从左遍历 s, 如果 s[i] < s[i-1], 加入栈中，成为合理的左端点, 否则放弃该点
     *
     * 从右遍历 s, 如果 s[j] > 栈顶，更新 ans
     * */
    public static int longestWPI(int[] hours) {
        int n = hours.length;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);

        // 6,6,9
        // 0,-1,-2,-1
        int[] s = new int[n+1];
        // 形成 s
        for(int i=1;i<s.length;i++) {
            s[i] = s[i-1] + (hours[i-1] > 8 ? 1:-1);
            if(s[i] < s[deque.peekLast()]) {
                deque.offerLast(i);
            }
        }
        // deque: 0,-1,-2

        // 0,-1,-2,-1
        int ans = 0;
        for(int i=n;i>0;--i) {
            // s[i] == 栈顶的时候不弹出，等待下一个大于它的位置
            while(!deque.isEmpty() && s[deque.peekLast()] < s[i]) {
                ans = Math.max(ans, i-deque.pollLast());
            }
        }
        return ans;
    }
}
