import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CheckIfWordIsValidAfterSubstitutions_1003 {
    public static void main(String[] args) {
        String s = "babcc";
        System.out.println(isValid_2(s));
    }

    public static boolean isValid(String s) {
        List<Character> deque = new ArrayList<>();
        for(char c: s.toCharArray()) {
            int size = deque.size();
            if(c == 'c' && size > 1 && deque.get(size-1) == 'b' && deque.get(size-2) == 'a') {
                // 满足要求，需要 pop
                deque.subList(size-2, size).clear();
            } else {
                deque.add(c);
            }
        }

        return deque.isEmpty();
    }

    /**
     * Method 2: 动态修改栈顶元素
     * */
    public static boolean isValid_2(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for(char c: s.toCharArray()) {
            // 先写 false 的情况
            if(c == 'b' && (deque.isEmpty() || deque.peekLast() != 'a')) return false;
            if(c == 'c' && (deque.isEmpty() || deque.peekLast() != 'b')) return false;

            if(c == 'a') deque.offerLast(c);
            else if(c == 'b' && !deque.isEmpty() && deque.peekLast() == 'a') {
                // 修改栈顶元素为 b
                deque.pollLast();
                deque.offerLast(c);
            } else if(c == 'c' && !deque.isEmpty() && deque.peekLast() == 'b') {
                // b 弹出
                deque.pollLast();
            }
        }
        return deque.isEmpty();
    }

}
