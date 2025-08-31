import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class TaskScheduler_63 {
    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','C'};
        int n = 3;
        System.out.println(leastInterval_2(tasks, n));
    }

    /**
     * Method 1: 数学方法 O(n)
     * A A A A B B B B C C
     * A 和 B 出现的次数最多，按照 A 来划分桶，假设冷却时间为 n = 3
     * A 出现的次数为 4 次，划分为以下桶结构
     *
     * A _ _ _
     * A _ _ _
     * A _ _ _
     * A
     * 最后一个桶后面的位置空缺，不需要等待。
     * 考虑和 A 出现次数相同的元素 B，插入每个桶的第2个位置
     *
     * A B _ _
     * A B _ _
     * A B _ _
     * A B
     *
     * 结论1: 有多少个出现次数相同的元素，那么最后一个桶的元素个数就是 x,
     * 假设最多出现次数为 a
     * 有空闲时间的情况下，num = (a-1) * (n+1) + x
     *
     * Case 2: 当没有空闲位置，num = num(tasks)
     * */
    public static int leastInterval(char[] tasks, int n) {
        int m = tasks.length;
        // 统计每个元素出现的次数
        int maxF = 0;

        int[] cnt = new int[26];
        for(int i = 0;i<m;i++) {
            cnt[tasks[i]-'A']++;
            maxF = Math.max(maxF, cnt[tasks[i]-'A']);
        }

        int x = 0;
        for(int c: cnt) {
            x += c == maxF ? 1: 0;
        }

        int num1 = (maxF-1)*(n+1) + x;
        return Math.max(num1, m);
    }


    /**
     * Method 2: Max heap
     * */
    public static int leastInterval_2(char[] tasks, int n) {
        // 统计 tasks 的出现次数
        int[] cnt = new int[26];
        for(char task: tasks)
            cnt[task-'A']++;

        // 最大堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        // 将 tasks 的频数加入到 pq
        for(int c: cnt) {
            if(c != 0) pq.offer(c);
        }

        // 待处理的 task, {cnt, idleTime}
        Deque<int[]> deque = new LinkedList<>();
        int time = 0;

        while(!pq.isEmpty() || !deque.isEmpty()) {
            // 当前时间 + 1
            time++;
            if(!pq.isEmpty()) {
                // 如果最大堆不为空, 拿到这个即将要处理的 task 是哪个
                //如果处理完这个task, 剩下的需要处理的task数量, task 并没有真的处理
                int count = pq.poll() - 1;
                // 等到 time+n 时才可以处理这个task
                if(count > 0)
                    deque.offerLast(new int[] {count, time+n});
            }

            // 执行队列的头部可以出队了
            if(!deque.isEmpty() && deque.peek()[1] == time) {
                pq.offer(deque.poll()[0]);
            }
        }
        return time;
    }
}
