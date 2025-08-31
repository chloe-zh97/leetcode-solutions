import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MinimumRemovetoMakeValidParentheses_1249 {
    public static void main(String[] args) {
        String s = "lee(t(c)o)de)";
        System.out.println(minRemoveToMakeValid_2(s));
    }

    /**
     * Method 1: 栈
     * 辅助数组+栈
     * */
    public static String minRemoveToMakeValid(String s) {
        // 存储下标
        Deque<Integer> deque = new ArrayDeque<>();
        int n = s.length();
        int level = 0;

        boolean[] valid = new boolean[n];
        char[] chs = s.toCharArray();

        for(int i=0;i<n;i++) {
            char c = chs[i];
            if(c == '(') {
                level++;
                // 左括号默认是 false, 只有遍历到右括号，才会更新合法的左括号
                valid[i] = false;
                deque.offerLast(i);
            } else if(c == ')') {
                level--;
                if(level < 0) {
                    level = 0;
                    continue;
                }

                while(!deque.isEmpty() && chs[deque.peekLast()] != '(') {
                    int id = deque.pollLast();
                    valid[id] = true;
                }
                // top 是 (
                valid[deque.pollLast()] = true;
                valid[i] = true;
            } else {
                deque.offerLast(i);
                valid[i] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++) {
            if(valid[i]) sb.append(chs[i]);
        }

        return sb.toString();
    }

    /**
     * Method 2: 优化，记录invalidIndex
     * */
    public static String minRemoveToMakeValid_2(String s) {
        int n = s.length();
        // 只存储左括号的下标
        Deque<Integer> indexStack = new ArrayDeque<>();
        boolean[] invalid = new boolean[n];
        char[] chs = s.toCharArray();

        for(int i=0;i<n;i++) {
            char c = chs[i];
            if(c == '(') {
                invalid[i] = true;
                // 最近的左括号下标加入栈
                indexStack.offerLast(i);
            } else if(c == ')') {
                if(indexStack.isEmpty()) invalid[i] = true;
                else {
                    invalid[indexStack.pollLast()] = false;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++)
            if(!invalid[i]) sb.append(chs[i]);
        return sb.toString();
    }
}
