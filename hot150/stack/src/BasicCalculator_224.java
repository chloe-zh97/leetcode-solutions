import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class BasicCalculator_224 {
    public static void main(String[] args) {
        String s = " 2-1 + 2 ";
        System.out.println(calculate(s));
    }

    // 1-(    -2)
    // 0, 1, 2
    // - ( -
//    public static int calculate(String s) {
//        int n = s.length();
//        char[] chs = s.toCharArray();
//        Stack<Integer> nstack = new Stack<>();
//        Stack<Character> ostack = new Stack<>();
//        nstack.push(0);
//
//        int i = 0;
//        while(i < n) {
//            char ch = chs[i];
//            if(isNumber(ch)) {
//                int start = i;
//                i++;
//                while(i < n && isNumber(chs[i])) i++;
//                // 退出循环后，chs[i] 不是 number
//                String tmp = s.substring(start, i);
//                int a = Integer.parseInt(tmp);
//                nstack.push(a);
//            } else if(ch == ' ') {
//                // 跳过空格，什么也不做
//                i++;
//            } else if(ch == '(') {
//                ostack.push(ch);
//                i++;
//            } else if(ch == ')') {
//                // pop 操作栈，直到 ( 被弹出
//                while(ostack.peek() != '(') {
//                    // o 只可能是 +, -
//                    char o = ostack.pop();
//                    int b = nstack.pop();
//                    int a = nstack.pop();
//
//                    int c;
//                    if(o == '+') c = a + b;
//                    else c = a - b;
//
//                    nstack.push(c);
//                }
//                // 退出循环后，ostack.peek 是 (
//                // 左括号出栈
//                ostack.pop();
//                i++;
//            } else {
//                // + 或者 -
//                while(!ostack.empty() && ostack.peek() != '(') {
//                    // o 一定为 + 或者 -
//                    char o = ostack.pop();
//                    int b = nstack.pop();
//                    int a = nstack.pop();
//
//                    int c;
//                    if(o == '+') c = a + b;
//                    else c = a - b;
//
//                    nstack.push(c);
//                }
//                // 退出循环时，stack 为空或者 ostack 顶端是 (
//                ostack.push(ch);
//                i++;
//            }
//            System.out.println("Operation stack:");
//            printStack(ostack);
//            System.out.println();
//
//            System.out.println("Number stack:");
//            printStack(nstack);
//            System.out.println();
//        }
//
//        while(!ostack.isEmpty()) {
//            char o = ostack.pop();
//            int b = nstack.pop();
//            int a = nstack.pop();
//
//            int c;
//            if(o == '+') c = a + b;
//            else c = a - b;
//
//            nstack.push(c);
//        }
//
//        return nstack.pop();
//    }
//
//    private static boolean isNumber(char ch) {
//        return ch >= '0' && ch <= '9';
//    }
//
//    private static void printStack(Stack<?> st) {
//        for(Object s: st)
//            System.out.print(s+" ");
//    }
    public static int calculate(String s) {
        Deque<Character> queue = new ArrayDeque<>();
        // 将 s 插入 queue 中
        for(char c: s.toCharArray())
            queue.offer(c);

        return dfs(queue);
    }

    private static int dfs(Deque<Character> queue) {
        // 存放数
        Stack<Integer> st = new Stack<>();

        // 初始化 num 和 sign
        char op = '+';
        int num = 0, res = 0;

        while(!queue.isEmpty()) {
            char c = queue.poll();
            if(Character.isDigit(c)) {
                // 更新 num
                num = num * 10 + c - '0';
            }

            if(c == '(') {
                // 左括号递归返回 num
                num = dfs(queue);
            }

            if(!Character.isDigit(c) && c != ' ' || queue.isEmpty()) {
                // 遇到了新的操作数，把上一个 num 放入栈，并且更新 num 和 sign
                if(op == '+') st.push(num);
                else if(op == '-') st.push(-num);
                else if(op == '*') st.push(st.pop() * num);
                else if(op == '/') st.push(st.pop()/num);

                op = c;
                num = 0;
            }

            if(c == ')') break;
        }

        for(int i: st)
            res += i;
        return res;
    }
}
