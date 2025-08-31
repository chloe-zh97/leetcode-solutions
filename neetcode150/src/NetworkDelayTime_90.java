import java.util.*;

public class NetworkDelayTime_90 {
    public static void main(String[] args) {
        int[][] times={
                {1,2,1}
        };

        int n = 2, k = 1;
        System.out.println(networkDelayTime_template_2(times, n, k));
    }

    /**
     * Method 1: Dijkstra
     * 1. 构建邻接表 List<int[]>[] graph = new ArrayList[n], 有多少个节点，就有多少个 ArrayList
     * 2. 初始化 int[] distance = new int[n], 初始化为 INF, distance[start] = 0
     * 3. HashSet<Integer> visit 标记已经更新完最短路径的节点
     * 4. 用最小堆 minHeap 来筛选最小边
     * */
    public static int networkDelayTime(int[][] times, int n, int k) {
        // construct adj table: (node, weight)
        List<int[]>[] graph = new ArrayList[n+1];
        Arrays.setAll(graph, g -> new ArrayList<>());

        for(int[] t: times) {
            int from = t[0], to = t[1], time = t[2];
            graph[from].add(new int[]{to, time});
        }

        // initialize distance
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE/2);
        distance[k] = 0;

        // initial minHeap
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[0]-b[0]);
        minHeap.offer(new int[]{0, k});

        // 未确定最短路径的节点个数
        int left = n;
        // visit
        HashSet<Integer> visit = new HashSet<>();
        int time = 0;

        while(!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            // w1：start 到当前节点的距离
            int w1 = cur[0], n1 = cur[1];

            // 当节点首次出堆时，dis[x] 就是最小最短路径
            if(w1 > distance[n1]) continue;

            // left -1 必须放在 continue 后面
            left--;

            visit.add(n1);
            time = Math.max(time, w1);

            // 找到邻居节点
            for(int[] edge: graph[n1]) {
                int n2 = edge[0], w2 = edge[1];
                int newDistance = w1 + w2;
                if(distance[n2] > newDistance) {
                    // 更新 distance数组时，再把二元组入堆
                    distance[n2] = newDistance;
                    minHeap.offer(new int[]{newDistance, n2});
                }
            }
        }
        return left == 0 ? time: -1;
    }


    /**
     * Template 2: 稀疏图模板
     * 1. 构建邻接矩阵 int[][] graph, 初始化为 INF, 根据 times 的值填充距离
     * 2. int[] distance, 结果数组，存储 start 到每个节点的最短距离
     * 3. boolean[] visit, 标记是否这个节点访问完毕，更新完了最短路径
     * */
    public static int networkDelayTime_template_1(int[][] times, int n, int k) {
        int INF = Integer.MAX_VALUE / 2;
        // construct adj table
        int[][] graph = new int[n+1][n+1];

        for(int i=0;i<n+1;i++) {
            Arrays.fill(graph[i], INF);
        }

        for(int[] t: times) {
            // from x to y
            int x = t[0], y = t[1], w = t[2];
            graph[x][y] = w;
        }

        // init distance array
        int[] distance = new int[n+1];
        Arrays.fill(distance, INF);
        distance[k] = 0;

        boolean[] visit = new boolean[n+1];
        int maxDis = 0;

        // start algorithm
        while(true) {
            int x = -1;
            // 在剩下的节点中，选一条最短的边
            for(int i=1;i<n+1;i++) {
                if(!visit[i] && (x < 0 || distance[x] > distance[i])) {
                    x = i;
                }
            }

            // 选不出来了
            if(x < 0) return maxDis;

            // 有不可达的点
            if(distance[x] == INF) return -1;

            // 从 start 到 x 节点的最小距离
            maxDis = distance[x];
            // 该节点已经被更新完
            visit[x] = true;

            // 更新 x 节点的邻居
            for(int y=1;y<n+1;y++) {
                distance[y] = Math.min(distance[y], distance[x]+graph[x][y]);
            }
        }
    }

    /**
     * Template: adj table
     * */
    public static int networkDelayTime_template_2(int[][] times, int n, int k) {
        int INF = Integer.MAX_VALUE / 2;

        List<int[]>[] graph = new ArrayList[n+1];
        Arrays.setAll(graph, g->new ArrayList<>());

        for(int[] t: times) {
            int from = t[0], to = t[1], w = t[2];
            graph[from].add(new int[]{to, w});
        }

        // init distance array
        int[] distance = new int[n+1];
        Arrays.fill(distance, INF);
        distance[k] = 0;

        int left = n;

        // init visit array
        boolean[] visit = new boolean[n+1];

        // use minHeap to select edge
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[0]-b[0]);
        // distance, node
        minHeap.offer(new int[]{0, k});
        int time = 0;

        while(!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            // 到 x 节点的最短距离
            int x = cur[1], w1 = cur[0];

            // 这个距离不考虑了，还没有之前更新的小
            if(distance[x] < w1) continue;

            time = Math.max(time, w1);
            visit[x] = true;
            left--;

            // 从和 x 相连的剩余节点中选择一个边最小的
            for(int[] edge: graph[x]) {
                int y = edge[0], w2 = edge[1];
                if(distance[y] > w1+w2) {
                    distance[y] = w1+w2;
                    // 更新完距离后要加入 heap
                    minHeap.offer(new int[]{distance[y], y});
                }
            }
        }
        return left == 0 ? time : -1;
    }

}
