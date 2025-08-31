import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class BasicCalculator_224 {
    public static void main(String[] args) {
        String s = " 2-1 + 2 ";
        System.out.println(calculate(s));
    }

    /**
     * Method 1: 双栈法
     * */
    public static int calculate(String s) {
        s = s.replaceAll("\\s+","");

        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();

        int n = s.length();
        char[] chs = s.toCharArray();

        // 定义优先级
        Map<Character, Integer> pMap = new HashMap<>();
        pMap.put('+', 1);
        pMap.put('-', 1);

        // -3+2
        numStack.offerLast(0);

        for(int i=0;i<n;i++) {
            char c = chs[i];
            if(c == '(') {
                opStack.offerLast(c);
            } else if(c == ')') {
                // 一直计算，直到遇到 (
                while(!opStack.isEmpty() && opStack.peekLast() != '(') {
                    int tmp = calculate(opStack, numStack);
                    numStack.offerLast(tmp);
                }

                opStack.pollLast();
            } else if(Character.isDigit(c)) {
                int num = 0;
                int j=i;
                while(j < n && Character.isDigit(chs[j])) {
                    num = num * 10 + (chs[j]-'0');
                    j++;
                }
                numStack.offerLast(num);
                i = j-1;
            } else {
                // 是 + - * /
                // 防止  (-6 + 3) 这种情况，在-前补0
                if(i != 0 && chs[i-1] == '(') numStack.offerLast(0);

                while(!opStack.isEmpty() && opStack.peekLast() != '(' && pMap.get(c) <= pMap.get(opStack.peekLast())) {
                    int tmp = calculate(opStack, numStack);
                    numStack.offerLast(tmp);
                }

                opStack.offerLast(c);
            }
        }
        while(!opStack.isEmpty()) {
            int tmp = calculate(opStack, numStack);
            numStack.offerLast(tmp);
        }
        return numStack.pollLast();
    }

    private static int calculate(Deque<Character> ops, Deque<Integer> nums) {
        char op = ops.pollLast();
        int b = nums.pollLast(), a = nums.pollLast();
        int res = 0;
        if(op == '+') res = a+b;
        else if(op == '-') res = a-b;
        else if(op == '*') res = a*b;
        else if(op == '/') res = a/b;
        return res;

    }

}
