import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class BasicCalculatorIII_772 {
    public static void main(String[] args) {
        String s = "2*(5+5*2)/3+(6/2+8)";
        System.out.println(calculate(s));
    }

    public static int calculate(String s) {
        Deque<Character> queue = new ArrayDeque<>();

        // 将 s 加入到 queue 中
        for(char c: s.toCharArray())
            queue.offer(c);

        return dfs(queue);
    }

    private static int dfs(Deque<Character> queue) {
        // 存储中间结果
        Stack<Integer> stack = new Stack<>();

        int num = 0;
        char op = '+';

        // 存储最终结果
        int res = 0;

        while(!queue.isEmpty()) {
            char c = queue.poll();

            if(Character.isDigit(c)) {
                // 数字
                num = num * 10 + c - '0';
            }

            if(c == '(') {
                // 左括号递归计算
                num = dfs(queue);
            }

            if(!Character.isDigit(c) || queue.isEmpty()) {
                // 操作符
                if(op == '+') stack.push(num);
                else if(op == '-') stack.push(-num);
                else if(op == '*') stack.push(stack.pop() * num);
                else if(op == '/') stack.push(stack.pop() / num);
                // 重新设置为0
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
