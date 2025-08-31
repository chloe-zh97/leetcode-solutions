import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses_21 {
    public static void main(String[] args) {

    }

    /**
     * Method 1: Stack
     * */
    public static boolean isValid(String s) {
        char[] chs = s.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();
        for(char c: chs) {
            if(c == '(' || c == '{' || c == '[') deque.offerLast(c);
            else {
                if(c == ')' && (deque.isEmpty() || deque.peekLast() != '(')) return false;
                if(c == ']' && (deque.isEmpty() || deque.peekLast() != '[')) return false;
                if(c == '}' && (deque.isEmpty() || deque.peekLast() != '{')) return false;

                deque.pollLast();
            }
        }
        return deque.isEmpty();
    }
}
