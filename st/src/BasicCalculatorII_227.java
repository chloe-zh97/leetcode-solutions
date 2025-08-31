import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class BasicCalculatorII_227 {
    public static void main(String[] args) {
        String s = " 3+5 / 2 ";
        System.out.println(calculate(s));
    }

    /**
     * Method 1: 双栈法
     * */
    public static int calculate(String s) {
        s = s.replaceAll("\\s+", "");

        int n = s.length();
        char[] chs = s.toCharArray();

        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();

        Map<Character, Integer> pMap = new HashMap<>();
        pMap.put('+', 1);
        pMap.put('-', 1);
        pMap.put('*', 2);
        pMap.put('/', 2);

        numStack.offerLast(0);

        for(int i=0;i<n;i++) {
            char c = chs[i];
            if(Character.isDigit(c)) {
                int j = i;
                int num = 0;
                while(j < n && Character.isDigit(chs[j])) {
                    num = num * 10 + (chs[j] - '0');
                    j++;
                }
                numStack.offerLast(num);
                i = j-1;
            } else {
                // 操作数
                while(!opStack.isEmpty() && pMap.get(c) <= pMap.get(opStack.peekLast())) {
                    int tmp = calculate(opStack, numStack);
                    numStack.offerLast(tmp);
                }

                opStack.offerLast(c);
            }
        }
        while(!opStack.isEmpty())
            numStack.offerLast(calculate(opStack, numStack));
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
