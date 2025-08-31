import java.util.ArrayDeque;
import java.util.Deque;

public class RemovingStarsFromaString_2390 {
    public static void main(String[] args) {
        String s = "leet**cod*e";
        System.out.println(removeStars_2(s));
    }

    public static String removeStars(String s) {
        char[] chs = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for(int i=0;i<chs.length;i++) {
            char ch = chs[i];
            if(ch == '*') {
                if(stack.isEmpty()) return "";
                // 不空，删除顶端元素
                stack.pollLast();
            } else {
                stack.offerLast(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        // 遍历stack
        while(!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }
        return sb.toString();
    }

    /**
     * 优化：用 StringBuilder 模拟栈操作
     * */
    public static String removeStars_2(String s) {
        StringBuilder sb = new StringBuilder();
        for(char ch: s.toCharArray()) {
            if(ch == '*') {
                sb.deleteCharAt(sb.length()-1);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
