import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllAdjacentDuplicatesInString_1047 {
    public static void main(String[] args) {
        String s = "azxxzy";
        System.out.println(removeDuplicates(s));
    }

    public static String removeDuplicates(String s) {
        char[] chs = s.toCharArray();

        Deque<Character> deque = new ArrayDeque<>();
        for(char c: chs) {
            if(!deque.isEmpty() && deque.peekLast() == c) {
                deque.pollLast();
            } else {
                deque.offerLast(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty())
            sb.append(deque.pollFirst());
        return sb.toString();
    }
}
