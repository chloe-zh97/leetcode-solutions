import java.util.*;

public class ExclusiveTimeofFunctions_636 {
    public static void main(String[] args) {
        int n = 2;
        String[] logs = {
                "0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"
        };

        List<String> lists = new ArrayList<>();
        for(String t: logs)
            lists.add(t);

        int[] ans = exclusiveTime(n, lists);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * Method 1: 栈模拟 82%
     * */
    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        int cur = -1;

        // 记录上一个线程的编号
        Deque<Integer> stack = new ArrayDeque<>();

        for(String log: logs) {
            String[] messages = log.split(":");
            int tId = Integer.parseInt(messages[0]);
            String command = messages[1];
            int time = Integer.parseInt(messages[2]);

            if(command.equals("start")) {
                // 结余上个线程
                if(!stack.isEmpty())
                    ans[stack.peekLast()] += time - cur;
                cur = time;
                stack.offerLast(tId);
            } else {
                // end
                stack.pollLast();
                ans[tId] += time - cur + 1;
                cur = time + 1;
            }
        }
        return ans;
    }
}
