import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumStringLengthAfterRemovingSubstrings_2696 {
    public static void main(String[] args) {
        String s = "ACBBD";
        System.out.println(minLength_2(s));
    }

    /**
     * Method 1: 栈
     * */
    public static int minLength(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();

        for(char c: chs) {
            if(c == 'B' && !stack.isEmpty() && stack.peekLast() == 'A') {
                // A出
                stack.pollLast();
            } else if(c == 'D' && !stack.isEmpty() && stack.peekLast() == 'C') {
                stack.pollLast();
            } else {
                stack.offerLast(c);
            }
        }
        return stack.size();
    }

    /**
     * Method 2: 暴力法
     * */
    public static int minLength_2(String s) {
        while(s.contains("AB") || s.contains("CD")) {
            s.replaceAll("AB", "");
            s.replaceAll("CD", "");
        }
        return s.length();
    }
}
