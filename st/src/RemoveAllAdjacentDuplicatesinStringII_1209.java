import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllAdjacentDuplicatesinStringII_1209 {
    public static void main(String[] args) {
        String s = "pbbcggttciiippooaais";
        int k = 2;
        System.out.println(removeDuplicates(s,k));
    }

    /**
     * Method 1: 一个字符栈，一个计数栈
     * */
    public static String removeDuplicates(String s, int k) {
        int n = s.length();
        char[] chs = s.toCharArray();

        Deque<Character> cStack = new ArrayDeque<>();
        Deque<Integer> nStack = new ArrayDeque<>();

        for(int i=0;i<n;i++) {
            char c = chs[i];

            if(!cStack.isEmpty() && cStack.peekLast() == c) {
                cStack.offerLast(c);

                // 重复元素，修改 nStack
                int p = nStack.pollLast();
                nStack.offerLast(++p);

                if(p == k) {
                    // 弹出 k 个元素
                    while(p > 0) {
                        cStack.pollLast();
                        p--;
                    }
                    nStack.pollLast();
                }
            } else {
                cStack.offerLast(c);
                nStack.offerLast(1);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!cStack.isEmpty()) sb.append(cStack.pollFirst());
        return sb.toString();
    }
}
