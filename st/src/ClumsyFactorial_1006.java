import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ClumsyFactorial_1006 {
    public static void main(String[] args) {
        System.out.println(clumsy(4));
    }

    /**
     * Method 1: 双栈法，模板题
     * */
    public static int clumsy(int n) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();

        Map<Character, Integer> priorityMap = new HashMap<>();
        priorityMap.put('*', 2);
        priorityMap.put('/', 2);
        priorityMap.put('+', 1);
        priorityMap.put('-', 1);

        char[] ops = {'*', '/', '+', '-'};

        for(int i=n,j=0;i>0;i--,j++) {
            char op = ops[j%4];
            numStack.offerLast(i);

            while(!opStack.isEmpty() && priorityMap.get(opStack.peekLast()) >= priorityMap.get(op)) {
                // 当前的运算符更低级，栈内先算
                int tmp = calculate(opStack, numStack);
                numStack.offerLast(tmp);
            }

            // 最后把可以加入栈的操作符加入进去
            if(i!=1) opStack.offerLast(op);
        }

        while(!opStack.isEmpty()) {
            int tmp = calculate(opStack, numStack);
            numStack.offerLast(tmp);
        }
        return numStack.pollLast();
    }

    private static int calculate(Deque<Character> ops, Deque<Integer> nums) {
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        if(op == '+') return a+b;
        else if(op == '-') return a-b;
        else if(op == '*') return a*b;
        else return a/b;
    }
}
