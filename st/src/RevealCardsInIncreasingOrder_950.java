import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class RevealCardsInIncreasingOrder_950 {
    public static void main(String[] args) {
        int[] nums = {17,13,11,2,3,5,7};
        nums = deckRevealedIncreasing(nums);
        System.out.println(Arrays.toString(nums));
    }
    // 17,13,11,2,3,5,7
    // 倒序排列 17 13 11 7 5 3 2
    // deque 17
    // 13 17
    // 11 17 13
    // 7 13 11 17
    // 放入心的元素之前，将队列末尾的元素加入头部
    public static int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        Deque<Integer> deque = new ArrayDeque<>();
        // sort
        Arrays.sort(deck);

        for(int i=n-1;i>=0;i--) {
            // 插入之前先把 deque 的末尾元素放入头部
            if(!deque.isEmpty()) {
                deque.offerFirst(deque.pollLast());
            }
            deque.offerFirst(deck[i]);
        }

        int[] ans = new int[n];
        int k = 0;
        while(!deque.isEmpty()) ans[k++] = deque.pollFirst();
        return ans;
    }
}
