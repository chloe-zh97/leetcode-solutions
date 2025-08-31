import java.util.ArrayDeque;
import java.util.Deque;

public class ResultingStringAfterAdjacentRemovals_3561 {
    public static void main(String[] args) {
        String s = "iwioccwvpdtjntmbpsaz";
        System.out.println(resultingString_2(s));
    }

    public static String resultingString(String s) {
        char[] chs = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();

        for(char c: chs) {
            if(!stack.isEmpty() && (isConsecutive(c, stack.peekLast())||
                    isConsecutive(stack.peekLast(), c))) {
                stack.pollLast();
            } else {
                stack.offerLast(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.append(stack.pollFirst());
        return sb.toString();
    }

    private static boolean isConsecutive(char a, char b) {
        return a == (char)('a' + (b - 'a' + 1) % 26) ||
                a == (char)('a' + (b - 'a' - 1) % 26);
    }

    /**
     * Method 2: 优化，只需要考虑 z->a
     * */
    public static String resultingString_2(String s) {
        char[] chs = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();

        for(char c: chs) {
            if(!stack.isEmpty() && isConsecutive_2(c, stack.peekLast())) {
                stack.pollLast();
            } else {
                stack.offerLast(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.append(stack.pollFirst());
        return sb.toString();
    }

    private static boolean isConsecutive_2(char x, char y) {
        int d = Math.abs(x-y);
        return d == 1 || d == 25;
    }
}
