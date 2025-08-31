import java.util.ArrayDeque;
import java.util.Deque;

public class ValidateStackSequences_946 {
    public static void main(String[] args) {
        int[] pushed = {2,1,0};
        int[] popped = {1,2,0};
        System.out.println(validateStackSequences(pushed, popped));
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;

        Deque<Integer> stack = new ArrayDeque<>();

        int i = 0, j = 0;
        while(i < n && j < n) {
            if(pushed[i] == popped[j]) {
                i++;
                j++;
            } else {
                if(!stack.isEmpty() && stack.peekLast() == popped[j]) {
                    stack.pollLast();
                    j++;
                } else {
                    stack.offerLast(pushed[i++]);
                }
            }
        }

        // j 指向还没 pop 的元素
        while(j < n && !stack.isEmpty()) {
            if(popped[j++] != stack.pollLast()) return false;
        }

        return j == n && stack.isEmpty();
    }

    /**
     * Method 2: 栈模拟
     * */
    public static boolean validateStackSequences_2(int[] pushed, int[] popped) {
        int j = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int num: pushed) {
            stack.offerLast(num);
            while(!stack.isEmpty() && stack.peekLast() == popped[j]) {
                stack.pollLast();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
