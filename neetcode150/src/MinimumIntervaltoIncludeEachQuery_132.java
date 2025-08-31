import java.util.*;

public class MinimumIntervaltoIncludeEachQuery_132 {
    public static void main(String[] args) {
        int[][] intervals= {
                {1,3},
                {2,3},
                {3,7},
                {6,6}
        };
        int[] queries = {2,3,1,7,6,8};
        int[] ans = minInterval_5(intervals, queries);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * Method 1: dfs
     * */
    public static int[] minInterval(int[][] intervals, int[] queries) {
        int n = intervals.length;
        // sort
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) return a[1]-b[1];
                return a[0]-b[0];
            }
        });

        int m = queries.length;
        int[] ans = new int[m];
        Arrays.fill(ans, -1);

        for(int i=0;i<m;i++) {
            int r = dfs(intervals, n-1, queries[i]);
            if(r != Integer.MAX_VALUE) ans[i] = r;
        }

        return ans;
    }

    /**
     * 在 intervals[0....i] 中，包含 target 的最短区间长度
     * */
    private static int dfs(int[][] intervals, int i, int target) {
        // interval 为空，不包含 target
        if(i < 0) return Integer.MAX_VALUE;

        int ans = dfs(intervals,i-1,target);
        if(intervals[i][0] <= target && target <= intervals[i][1]) {
            ans = Math.min(ans, intervals[i][1]-intervals[i][0]+1);
        }
        return ans;
    }


    /**
     * Method 2: 改记忆化搜索
     * */
    public static int[] minInterval_2(int[][] intervals, int[] queries) {
        int n = intervals.length;
        // sort
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) return a[1]-b[1];
                return a[0]-b[0];
            }
        });

        int maxQuery = Integer.MIN_VALUE;
        for(int q: queries)
            maxQuery = Math.max(maxQuery, q);

        // memos[i][j]: 在 [0...i]范围的 intervals 中，包含 j 的最短区间长度
        int[][] memos = new int[n][maxQuery+1];
        for(int[] mm: memos) Arrays.fill(mm, -1);

        int[] ans = new int[queries.length];

        for(int i=0;i<queries.length;i++) {
            int r = dfs_2(intervals, n-1, queries[i], memos);
            ans[i] = r == Integer.MAX_VALUE ? -1 : r;
        }
        return ans;
    }

    private static int dfs_2(int[][] intervals, int i, int target, int[][] memos) {
        if(i < 0) return Integer.MAX_VALUE;

        if(memos[i][target] != -1) return memos[i][target];

        int res = dfs_2(intervals,i-1,target, memos);
        if(intervals[i][0] <= target && target <= intervals[i][1]) {
            res = Math.min(res, intervals[i][1]-intervals[i][0]+1);
        }
        memos[i][target] = res;
        return res;

    }

    /**
     * Method 3: 改递推
     * */
    public static int[] minInterval_3(int[][] intervals, int[] queries) {
        int n = intervals.length, m = queries.length;
        // sort
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) return a[1]-b[1];
                return a[0]-b[0];
            }
        });

        int maxQuery = Integer.MIN_VALUE;
        for(int q: queries) maxQuery = Math.max(maxQuery, q);

        int[][] dp = new int[n+1][maxQuery+1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);

        for(int i=0;i<n;i++) {
            for(int j=0;j<dp[0].length;j++) {
//                int res = dfs_2(intervals,i-1,target, memos);
//                if(intervals[i][0] <= target && target <= intervals[i][1]) {
//                    res = Math.min(res, intervals[i][1]-intervals[i][0]+1);
//                }
                int res = dp[i][j];
                if(intervals[i][0] <= j && j <= intervals[i][1])
                    res = Math.min(res, intervals[i][1]-intervals[i][0]+1);

                dp[i+1][j] = res;
            }
        }

        int[] ans = new int[m];
        for(int i=0;i<m;i++) {
            ans[i] = dp[n][queries[i]] == Integer.MAX_VALUE ? -1 : dp[n][queries[i]];
        }

        return ans;
    }


    /**
     * Method 4: 用 hashmap 代替 memos 数组
     * */
    public static int[] minInterval_4(int[][] intervals, int[] queries) {
        int n = intervals.length;
        // sort
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) return a[1]-b[1];
                return a[0]-b[0];
            }
        });

        int m = queries.length;
        Map<int[], Integer> memos = new HashMap<>();
        int[] ans = new int[m];
        for(int i=0;i<m;i++) {
            int r = dfs_4(intervals, n-1, queries[i], memos);
            ans[i] = r == Integer.MAX_VALUE ? -1 : r;
        }
        return ans;
    }

    private static int dfs_4(int[][] intervals, int i, int target, Map<int[], Integer> memos) {
        if(i < 0) return Integer.MAX_VALUE;

        int[] key = {i, target};
        if(memos.containsKey(key)) return memos.get(key);

        int res = dfs_4(intervals,i-1,target, memos);
        if(intervals[i][0] <= target && target <= intervals[i][1]) {
            res = Math.min(res, intervals[i][1]-intervals[i][0]+1);
        }

        memos.put(key, res);
        return res;
    }


    /**
     * Method 5: minHeap + sort
     * 将区间和 queries 都排序，遍历 q
     * 遍历到每个 q, 将所有有关系的区间加入到最小堆，结构为 (size, end)
     * 当所有有关系的区间都加入完毕，开始弹出堆
     * */
    public static int[] minInterval_5(int[][] intervals, int[] queries) {
        int n = intervals.length, m = queries.length;
        int[] copy = Arrays.copyOf(queries, m);

        Arrays.sort(intervals, new Comparator<int[]>(){
           @Override
           public int compare(int[] a, int[] b) {
               if(a[0] == b[0]) return a[1]-b[1];
               return a[0]-b[0];
           }
        });

        Arrays.sort(queries);

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[0]-b[0]);

        Map<Integer, Integer> map = new HashMap<>();

        int i = 0;
        // 遍历每个 q
        for(int q: queries) {
            // 不断将有关系的区间入堆
            while(i < n && intervals[i][0] <= q) {
                minHeap.offer(new int[]{intervals[i][1]- intervals[i][0]+1, intervals[i][1]});
                i++;
            }

            // 将过期的区间弹出
            while(!minHeap.isEmpty() && minHeap.peek()[1] < q) minHeap.poll();

            // 到这里所有过期的区间都已经弹出了
            int res = minHeap.isEmpty() ? -1 : minHeap.peek()[0];

            map.put(q, res);
        }

        int[] ans = new int[m];
        for(int j=0;j<m;j++) {
            int key = copy[j];
            int val = map.get(key);
            ans[j] = val;
        }
        return ans;
    }
}
