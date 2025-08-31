import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString_394 {
    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        System.out.println(decodeString_2(s));
    }

    // numStack: 3
    // letterSta:[ a cc
    public static String decodeString(String s) {
        // 存储数字前的字符串
       Deque<String> letterStack = new ArrayDeque<>();
       Deque<Integer> numStack = new ArrayDeque<>();

       int num = 0;
       StringBuilder res = new StringBuilder();

       for(char c: s.toCharArray()) {
           if(Character.isDigit(c)) {
               num = num * 10 + c - '0';
           } else if(Character.isLetter(c)) {
               res.append(c);
           } else if(c == '[') {
               // 把数字入栈
               numStack.offerLast(num);
               num = 0;

               // 把【a3[c]】的a入栈
               letterStack.offerLast(res.toString());
               res = new StringBuilder();
           } else {
               // 遇到右括号
               String cur = res.toString();
               int repeat = numStack.pollLast();

               StringBuilder tmp = new StringBuilder();
               while(repeat-- > 0)
                   tmp.append(cur);

               res = new StringBuilder(letterStack.pollLast()+tmp);
           }
       }
       return res.toString();
    }

    /**
     * Method 2: 递归
     * */
    public static String decodeString_2(String s) {
        // 预处理，放入队列中
        Deque<Character> deque = new ArrayDeque<>();
        for(char c: s.toCharArray())
            deque.offerLast(c);

        return dfs(deque);
    }

    private static String dfs(Deque<Character> deque) {
        StringBuilder res = new StringBuilder();

        int num = 0;
        while(!deque.isEmpty()) {
            char c = deque.pollFirst();
            if(Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if(Character.isLetter(c)) {
                res.append(c);
            } else if(c == '[') {
                String next = dfs(deque);
                while(num > 0) {
                    res.append(next);
                    num--;
                }
            } else {
                return res.toString();
            }
        }
        return res.toString();
    }
}
