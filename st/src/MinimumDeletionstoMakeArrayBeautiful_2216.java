import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumDeletionstoMakeArrayBeautiful_2216 {
    public static void main(String[] args) {
        int[] nums = {1,1,2,2,3,3};
        System.out.println(minDeletion(nums));
    }

    /**
     * Method 1: 栈
     * 如果 num 入栈之前，栈中元素是奇数，且 num 和栈顶元素一致，不要入栈
     * */
    public static int minDeletion(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=0;i<n;i++) {
            int num = nums[i];
            if(stack.size() % 2 == 1 && num == stack.peekLast()) continue;
            stack.offerLast(num);
        }
        if(stack.size() % 2==1) stack.pollLast();
        return n - stack.size();
    }
}
