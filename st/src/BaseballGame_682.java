import java.util.ArrayDeque;
import java.util.Deque;

public class BaseballGame_682 {
    public static void main(String[] args) {
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        System.out.println(calPoints(ops));
    }

    public static int calPoints(String[] operations) {
        // nums stack
        Deque<Integer> numStack = new ArrayDeque<>();

        for(String s: operations) {
            if(s.equals("C")) {
                numStack.pollLast();
            } else if(s.equals("D")) {
                int p = numStack.peekLast();
                numStack.offerLast(p*2);
            } else if(s.equals("+")) {
                int b = numStack.pollLast();
                int a = numStack.pollLast();
                int c = a + b;
                numStack.offerLast(a);
                numStack.offerLast(b);
                numStack.offerLast(c);
            } else {
                // number
                int v = Integer.parseInt(s);
                numStack.offerLast(v);
            }
        }

        int res = 0;
        while(!numStack.isEmpty()) {
            int v = numStack.peekLast();
            res += numStack.pollLast();
        }
        return res;
    }
}
