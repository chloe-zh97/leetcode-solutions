import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class CalculatorLCCI_1626 {
    public static void main(String[] args) {
        String s = " 3+5 / 2 ";
        System.out.println(calculate(s));
    }

    public static int calculate(String s) {
        Deque<Character> queue = new ArrayDeque<>();
        for(char c: s.toCharArray())
            queue.offer(c);

        return dfs(queue);
    }

    private static int dfs(Deque<Character> queue) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char op = '+';
        int res = 0;

        while(!queue.isEmpty()) {
            char c = queue.poll();
            if(Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if(c == '(') {
                num = dfs(queue);
            }
            if(!Character.isDigit(c) && c != ' ' || queue.isEmpty()) {
                // 把上一个操作数加入
                if(op == '+') stack.push(num);
                else if(op == '-') stack.push(-num);
                else if(op == '*') stack.push(stack.pop() * num);
                else if(op == '/') stack.push(stack.pop() / num);

                op = c;
                num = 0;
            }
            if(c == ')') break;
        }

        for(int i: stack)
            res += i;
        return res;
    }
}
