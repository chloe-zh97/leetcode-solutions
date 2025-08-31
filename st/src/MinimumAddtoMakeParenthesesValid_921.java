import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumAddtoMakeParenthesesValid_921 {
    public static void main(String[] args) {
        String s = "()((()";
        System.out.println(minAddToMakeValid(s));
    }

    /**
     * Method 1: 栈模拟
     * */
    public static int minAddToMakeValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char ch: s.toCharArray()) {
            if(ch == '(') stack.offerLast(ch);
            else {
                if(!stack.isEmpty() && stack.peekLast() == '(') {
                    stack.pollLast();
                } else {
                    stack.offerLast(')');
                }
            }
        }
        return stack.size();
    }

    /**
     * Method 2: 用得分计算差值
     * */
    public static int minAddToMakeValid_2(String s) {
        int ans = 0;
        int score = 0;
        for(char ch: s.toCharArray()) {
            score += ch == '(' ? 1 : -1;
            if(score < 0) {
                score = 0;
                // 每一次不合规，都需要把ans++
                ans++;
            }
        }
        return ans + score;
    }
}
