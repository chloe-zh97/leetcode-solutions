import java.util.*;

public class CheapestFlightsWithinKStops_95 {
    public static void main(String[] args) {
        int n = 5;
        int[][] flights = {
                {0,1,5},
                {1,2,5},
                {0,3,2},
                {3,1,2},
                {1,4,1},
                {4,2,1}
        };
        int src = 0, dst = 2, k = 2;
        System.out.println(findCheapestPrice_4(n, flights, src, dst, k));
    }

    /**
     * Method 1: DFS 超时
     * 1. 构建邻接表 List<int[]>[] adj = new ArrayList[n], 存放 (to, w)
     * 2. dfs 从 src 开始出发
     * 2.1 返回条件是 到达了 dst，且 stop 数量 <= k
     * 2.2 boolean[] visit 记录是否访问过
     *
     * n 个节点，编号 [0,n-1]
     * */
//    private static int INF = Integer.MAX_VALUE;
//    private static int ans = INF;
//
//    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
//        List<int[]>[] adj = new ArrayList[n];
//        Arrays.setAll(adj, g->new ArrayList<>());
//
//        // construct adj table
//        for(int[] flight: flights) {
//            int s = flight[0], t = flight[1], w = flight[2];
//            adj[s].add(new int[] {t, w});
//        }
//
//        boolean[] visit = new boolean[n];
//
//        // start dfs
//        dfs(adj, src, dst, k, visit, 0, new ArrayList<>());
//
//        return ans == INF ? -1 : ans;
//    }
//
//    private static int dfs(List<int[]>[] adj, int start, int dst, int k, boolean[] visit, int distance, List<Integer> path) {
//        if(visit[start]) return INF;
//
//        if(start == dst && path.size() <= k+1) {
//            // 找到了一条路径
//            return distance;
//        }
//
//        visit[start] = true;
//        path.add(start);
//
//        for(int[] nei: adj[start]) {
//            int to = nei[0], w = nei[1];
//            int res = dfs(adj, to, dst, k, visit, distance+w, path);
//            ans = Math.min(ans, res);
//        }
//
//        visit[start] = false;
//        path.remove(path.size()-1);
//
//        return INF;
//    }
//
//    /**
//     * Method 2: Bellman Ford Algorithm
//     * */
//
//    public static int findCheapestPrice_2(int n, int[][] flights, int src, int dst, int k) {
//        int INF = Integer.MAX_VALUE;
//        int[] prices = new int[n];
//        Arrays.fill(prices, INF);
//        prices[src] = 0;
//
//        for(int i=0;i<k+1;i++) {
//            int[] copyPrices = Arrays.copyOf(prices, n);
//
//            // 遍历每一条边
//            for(int[] flight: flights) {
//                int from = flight[0], to = flight[1], p = flight[2];
//                // 如果 source 节点不可达，跳过
//                if(prices[from] == INF) continue;
//
//                // 更新 tmp[to]
//                if(prices[from] + p < copyPrices[to]) {
//                    copyPrices[to] = prices[from] + p;
//                }
//            }
//
//            prices = Arrays.copyOf(copyPrices, n);
//        }
//        return prices[dst] == INF ? -1 : prices[dst];
//    }

    /**
     * Method 3: Dijkstra
     * */
    public static int findCheapestPrice_3(int n, int[][] flights, int src, int dst, int k) {
        int INF = Integer.MAX_VALUE;
        List<int[]>[] adj = new ArrayList[n];
        Arrays.setAll(adj, g -> new ArrayList<>());

        for(int[] flight: flights) {
            int from = flight[0], to = flight[1], p = flight[2];
            adj[from].add(new int[] {to, p});
        }

        // minDist[i][j]: 到 i 节点，经过 j 跳的最短路径
        int[][] minDist = new int[n][k+5];
        for(int i=0;i<n;i++)
            Arrays.fill(minDist[i], INF);
        minDist[src][0] = 0;

        // minHeap: price, step, node
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        minHeap.offer(new int[] {0, 0, src});

        while(!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int p1 = cur[0], step1 = cur[1], u = cur[2];

            // 超过跳数，或者之前有维护过 step1 跳数到 u 的距离更小
            if(step1 > k+1 || p1 > minDist[u][step1]) continue;

            if(u == dst) return p1;

            minDist[u][step1] = p1;

            // 遍历它的邻居
            for(int[] nei: adj[u]) {
                int v = nei[0], p2 = nei[1];
                if(minDist[v][step1+1] > minDist[u][step1] + p2) {
                    minDist[v][step1+1] = minDist[u][step1] + p2;
                    minHeap.offer(new int[]{minDist[v][step1+1], step1+1, v});
                }
            }
        }

        return -1;
    }

    /**
     * Method 4: DFS + 记忆化搜索
     * memo[i][j]: 到节点 i, 经过 j 跳的最短路径长度
     * */
    private static int INF = Integer.MAX_VALUE / 2;

    public static int findCheapestPrice_4(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] adj = new ArrayList[n];
        Arrays.setAll(adj, g-> new ArrayList<>());

        for(int[] flight: flights) {
            int u = flight[0], v = flight[1], p = flight[2];
            adj[u].add(new int[] {v, p});
        }
        int[][] memos = new int[n][k+5];

        int res = dfs(adj, src, dst, k+1, memos);

        return res == INF ? -1 : res;

    }

    /**
     * 返回 src 到 dst 在 k 跳数下的最短距离
     * */
    private static int dfs(List<int[]>[] adj, int src, int dst, int k, int[][] memos) {
        // 跳数不够了
        if(k < 0) return INF;

        if(src == dst) return 0;

        if(memos[src][k] != 0) return memos[src][k];

        int min = INF;
        for(int[] nei: adj[src]) {
            int to = nei[0], p = nei[1];
            int price = dfs(adj, to, dst, k-1, memos);
            min = Math.min(min, price+p);
        }

        memos[src][k] = min;

        return memos[src][k];
    }


    /**
     * Method 4: Shortest Path Algorithm
     * */
    public static int findCheapestPrice_5(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] adj = new ArrayList[n];
        Arrays.setAll(adj, g-> new ArrayList<>());

        for(int[] flight: flights) {
            int from = flight[0], to = flight[1], p = flight[2];
            adj[from].add(new int[] {to, p});
        }

        // 到每一个节点的 cost
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        Deque<int[]> deque = new LinkedList<>();
        // distance, step, node
        deque.offer(new int[] {0, 0, src});

        while(!deque.isEmpty()) {
            int[] cur = deque.poll();
            int d = cur[0], step = cur[1], u = cur[2];

            if(step > k+1) continue;

            // 对所有邻居节点
            for(int[] nei: adj[u]) {
                int to = nei[0], d2 = nei[1];

                int nextCost = d + d2;
                if(nextCost < prices[to]) {
                    prices[to] = nextCost;
                    deque.offer(new int[] {prices[to], step+1, to});
                }
            }
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

}
