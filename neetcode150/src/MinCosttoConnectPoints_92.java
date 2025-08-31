import java.util.*;

public class MinCosttoConnectPoints_92 {
    public static void main(String[] args) {
        int[][] points = {
                {0,0},
                {2,2},
                {3,3},
                {2,4},
                {4,2}
        };
        System.out.println(minCostConnectPoints_prim_adj(points));
    }

    /**
     * Method 1: Prim algorithm
     * 将距离生成树最近的点 v 加入到MST
     * 更新和 v 相邻的所有节点距离 MST 的距离
     * */
//    public static int minCostConnectPoints(int[][] points) {
//        int n = points.length;
//        int path = 0;
//
//        // 每个节点到最小生成树的距离
//        int[] minDist = new int[n];
//        Arrays.fill(minDist, 100000001);
//
//        // 记录节点是否在树里
//        boolean[] isInTree = new boolean[n];
//
//        // prim 算法主循环
//        for(int i=0;i<n;i++) {
//            int cur = -1;
//            int curDis = Integer.MAX_VALUE / 2;
//
//            // 不在 tree 里的节点中选一个距离生成树最近的节点
//            for(int j=0;j<n;j++) {
//                if(!isInTree[j] && minDist[j] < curDis) {
//                    cur = j;
//                    curDis = minDist[j];
//                }
//            }
//
//            // 循环结束后，cur 为待连入 mst 的节点
//            isInTree[cur] = true;
//            minDist[cur] = curDis;
//
//            // 更新 cur 节点的所有邻居节点到 mst 的距离
//            for(int v=0;v<n;v++) {
//                int vDis = calculateDistance(points[cur], points[v]);
//                if(!isInTree[v] && minDist[v] > vDis) {
//                    minDist[v] = vDis;
//                }
//            }
//        }
//
//        // 统计结果
//        for(int i=1;i<n;i++)
//            path += minDist[i];
//
//        return path;
//    }
//
//    private static int calculateDistance(int[] a, int[] b) {
//        return Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);
//    }

    /**
     * Method 2: Kruskal Algorithm
     * 将所有的边从小到达排序，每次选择最小的边加入，确保每次加入边，这两个点不属于 mst 中
     * */
    public static int minCostConnectPoints_2(int[][] points) {
        // 一共 n 个节点
        int n = points.length;
        List<int[]>[] adj = new ArrayList[n];
        Arrays.setAll(adj, g -> new ArrayList<>());

        PriorityQueue<int[]> edges = new PriorityQueue<>((a,b) -> a[0] - b[0]);

        // fill in adj
        for(int i=0;i<n;i++) {
            int[] u = points[i];

            for(int j=i+1;j<n;j++) {
                int[] v = points[j];
                int dis = calculateDistance(u,v);

                adj[i].add(new int[]{j, dis});
                adj[j].add(new int[]{i, dis});

                edges.offer(new int[] {dis, i, j});
            }
        }

        UnionFind unionFind = new UnionFind(n);
        int path = 0;

        while(unionFind.cc != 1) {
            int[] cur = edges.poll();
            int distance = cur[0], u = cur[1], v = cur[2];

            if(unionFind.union(u, v) != 0) {
                path += distance;
            }
        }

        return path;
    }

    private static int calculateDistance(int[] a, int[] b) {
        return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
    }

    static class UnionFind {
        int[] parent;
        int[] rank;
        int cc;

        public UnionFind(int n) {
            parent = new int[n];
            Arrays.setAll(parent, i->i);

            rank = new int[n];
            Arrays.fill(rank, 1);
            cc = n;
        }

        public int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public int union(int x, int y) {
            x = find(x);
            y = find(y);
            if(x == y) return 0;

            if(x < y) {
                // 把 y 连接到 x 上
                parent[y] = x;
                rank[x] += rank[y];
            } else {
                parent[x] = y;
                rank[y] += rank[x];
            }
            cc--;
            return 1;
        }
    }

    /**
     * Prim Algorithm, use adj table
     * */
    public static int minCostConnectPoints_prim_adj(int[][] points) {
        int n = points.length;
        // create adj table
        Map<Integer, PriorityQueue<int[]>> adj = new HashMap<>();

        for(int i=0;i<n;i++) {
            PriorityQueue<int[]> pqI = adj.getOrDefault(i, new PriorityQueue<>((a,b) -> a[0]-b[0]));
            for(int j=i+1;j<n;j++) {
                PriorityQueue<int[]> pqJ = adj.getOrDefault(j, new PriorityQueue<>((a,b) -> a[0] - b[0]));
                int dis = calculateDistance_3(points[i], points[j]);
                pqI.offer(new int[] {dis, j});
                pqJ.offer(new int[] {dis, i});

                adj.put(j, pqJ);
            }
            adj.put(i, pqI);
        }

        HashSet<Integer> visit = new HashSet<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[0]-b[0]);
        // distance, nodeId
        minHeap.offer(new int[]{0, 0});

        int ans = 0;

        while(visit.size() < n) {
            int[] cur = minHeap.poll();
            int u = cur[1], dis = cur[0];
            // 节点 u 已经被访问过，跳过
            if(visit.contains(u)) continue;

            visit.add(u);
            // 更新答案
            ans += dis;

            if(adj.containsKey(u)) {
                for(int[] edge: adj.get(u)) {
                    int v = edge[1];
                    if(!visit.contains(v)) {
                        minHeap.offer(edge);
                    }
                }
            }
        }

        return ans;
    }

    private static int calculateDistance_3(int[] a, int[] b) {
        return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
    }
}
