import java.util.ArrayDeque;
import java.util.Deque;

public class FindMirrorScoreofaString_3412 {
    public static void main(String[] args) {
        String s = "zadavyayobbgqsexaabk";
        System.out.println(calculateScore(s));
    }


    public static long calculateScore(String s) {
        Deque<Integer>[] deques = new ArrayDeque[26];
        for(int i=0;i<26;i++)
            deques[i] = new ArrayDeque<>();

        long ans = 0L;
        char[] chs = s.toCharArray();
        int n = s.length();

        for(int i=0;i<n;i++) {
            char cur = chs[i];
            char p = (char)('z' - cur + 'a');

            // 找到 p 对应的下标们
            Deque<Integer> queue = deques[p-'a'];
            if(!queue.isEmpty()) {
                ans += i - queue.pollLast();
            } else {
                Deque<Integer> curQueue = deques[cur-'a'];
                curQueue.offerLast(i);
            }
        }
        return ans;
    }

}
