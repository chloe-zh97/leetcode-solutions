import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseSubstringsBetweenEachPairofParentheses_1190 {
    public static void main(String[] args) {
        String s = "ab(cd)";
        System.out.println(reverseParentheses(s));
    }

    /**
     * Method 1: 栈
     * */
    public static String reverseParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb;

        for(char c: s.toCharArray()) {
            if(c == '(' || Character.isLetter(c)) stack.offerLast(c);
            else if(c == ')') {
                // 原始栈内 abc，理想出栈 cba, 要换成出栈 abc, 在栈内就是 cba
                sb = new StringBuilder();  // cba
                while(!stack.isEmpty() && stack.peekLast() != '(') {
                    sb.append(stack.pollLast());
                }
                // 退出 (
                stack.pollLast();

                // 重新加入栈
                for(int i=0;i<sb.length();i++)
                    stack.offerLast(sb.charAt(i));
            }
        }

        sb = new StringBuilder();
        while(!stack.isEmpty())
            sb.append(stack.pollFirst());

        return sb.toString();
    }
}
