import java.util.ArrayDeque;
import java.util.Deque;

public class MakeTheStringGreat_1544 {
    public static void main(String[] args) {
        String s = "s";
        System.out.println(makeGood(s));
    }

    public static String makeGood(String s) {
        char[] chs = s.toCharArray();

        Deque<Character> deque = new ArrayDeque<>();

        for(char c: chs) {
            if(Character.isUpperCase(c) &&
                    !deque.isEmpty() &&
                    Character.isLowerCase(deque.peekLast())
            && (c-'A') == (deque.peekLast()-'a')) {
                deque.pollLast();
            } else if(Character.isLowerCase(c) &&
            !deque.isEmpty() &&
            Character.isUpperCase(deque.peekLast()) &&
                    (c-'a') == (deque.peekLast()-'A')) {
                deque.pollLast();
            } else {
                deque.offerLast(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) sb.append(deque.pollFirst());
        return sb.toString();
    }





}
