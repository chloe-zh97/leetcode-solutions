import java.util.ArrayDeque;
import java.util.Deque;

public class ScoreofParentheses_856 {
    public static void main(String[] args) {
        String s = "(()(()))";
        System.out.println(scoreOfParentheses(s));
    }

    /**
     * Method 1: 栈模拟
     * 栈中存储每一层的分数，在每一层开始前增加标记
     * */
    public static int scoreOfParentheses(String s) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(char c: s.toCharArray()) {
            if(c == '(') {
                // 又有新的一层开始了，增加一个标记
                stack.offerLast(-1);
            } else {
                int v = 0;
                // ), 累计这一组的分数，直到遇到了 -1
                while(!stack.isEmpty() && stack.peekLast() != -1) {
                    v += stack.pollLast();
                }

                // 结束循环 top = -1了
                stack.pollLast();
                stack.offerLast(Math.max(2*v, 1));
            }
        }

        // 统计总分
        while(!stack.isEmpty()) ans += stack.pollLast();
        return ans;
    }
}
