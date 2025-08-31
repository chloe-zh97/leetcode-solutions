import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishNotation_23 {
    public static void main(String[] args) {
        String[] tokens = {"1","2","+","3","*","4","-"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        Deque<Integer> numStack = new ArrayDeque<>();

        for(String token: tokens) {
            // 区分操作符还是操作数
            if(isOperation(token)) {
                char c = token.charAt(0);
                int b = numStack.pollLast();
                int a = numStack.pollLast();
                int res = 0;
                if(c == '+') res = a+b;
                else if(c == '-') res = a-b;
                else if(c == '*') res = a*b;
                else if(c == '/') res = a/b;

                numStack.offerLast(res);
            } else {
                // 操作数
                int sign = token.charAt(0) == '-' ? -1 : 1;
                int n = token.length();
                int i = sign == 1 ? 0 : 1;
                int num = Integer.parseInt(token.substring(i, n));
                numStack.offerLast(num * sign);
            }
        }

        return numStack.peekLast();
    }

    private static boolean isOperation(String token) {
        return token.length() == 1 && (
                token.equals("+") ||
                        token.equals("-") ||
                        token.equals("*") ||
                        token.equals("/"));
    }
}
