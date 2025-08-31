import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LexicographicallyMinimumStringAfterRemovingStars_3170 {
    public static void main(String[] args) {
        String s = "d*o*";
        System.out.println(clearStars(s));
    }

    public static String clearStars(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();

        Deque<Integer>[] stacks = new ArrayDeque[26];
        Arrays.setAll(stacks, i->new ArrayDeque<>());

        for(int i=0;i<n;i++) {
            char c = chs[i];
            if(c != '*') {
                stacks[c-'a'].offerLast(i);
            } else {
                // 删除最近的最小元素
                int j=0;
                while(stacks[j].isEmpty()) j++;
                // 退出循环时，stack[j] 有值
                int deleteId = stacks[j].pollLast();
                chs[deleteId] = '*';
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c: chs)
            if(c !='*') sb.append(c);
        return sb.toString();
    }
}
