import java.util.*;

public class NumberofConnectedComponentsinanUndirectedGraph_87 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {
                {0,1},
                {1,2},
                {2,3},
                {4,5}
        };
        System.out.println(countComponents_3(n, edges));
    }


    /**
     * Method 1: DFS 无向图，可能有环
     * */
    public static int countComponents(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, g->new ArrayList<>());

        for(int[] edge: edges) {
            int a = edge[0], b = edge[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        int cnt = 0;
        int prev = -1;

        Set<Integer> visit = new HashSet<>();
        for(int i=0;i<n;i++) {
            if(visit.size() == n) break;

            if(visit.contains(i)) continue;

            dfs(adj, i, prev, visit);

            cnt++;
        }

        return cnt;
    }

    private static void dfs(List<Integer>[] adj, int node, int prev, Set<Integer> visit) {
        // 该节点以及被访问过了
        if(visit.contains(node)) return;

        visit.add(node);
        // 获取它的孩子节点
        for(int nei: adj[node]) {
            if(nei == prev) continue;
            dfs(adj, nei, node, visit);
        }
    }

    /**
     * Method 2: BFS 无向图
     * */
    public static int countComponents_2(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, g -> new ArrayList<>());

        for(int[] edge: edges) {
            int a = edge[0], b = edge[1];

            adj[a].add(b);
            adj[b].add(a);
        }

        Set<Integer> visit = new HashSet<>();
        Deque<int[]> deque = new LinkedList<>();

        int ans = 0;
        for(int i=0;i<n;i++) {
            if(visit.size() == n) break;
            if(visit.contains(i)) continue;

            bfs(adj, i, deque, visit);
            ans++;
        }
        return ans;
    }

    private static void bfs(List<Integer>[] adj, int node, Deque<int[]> deque, Set<Integer> visit) {
        deque.offer(new int[] {node, -1});
        visit.add(node);

        while(!deque.isEmpty()) {
            int[] cur = deque.poll();
            int p = cur[0], prev = cur[1];

            // 获取领居
            for(int nei: adj[p]) {
                if(nei == prev || visit.contains(nei)) continue;

                visit.add(nei);
                deque.offer(new int[]{nei, p});
            }
        }
    }

    /**
     * Method 3: Union Find
     * */
    static class UnionFind {
        // parent
        int[] fa;

        // 每个连通图的大小
        int[] size;

        // 连通图的个数
        int cc;

        public UnionFind(int n) {
            fa = new int[n];
            for(int i=0;i<n;i++)
                fa[i] = i;

            size = new int[n];
            Arrays.fill(size, 1);

            cc = n;
        }
        /**
         * Find the parent of x
         * */
        public int find(int x) {
            if(fa[x] != x) {
                fa[x] = find(fa[x]);
            }

            return fa[x];
        }

        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }

        /**
         * Union two graph
         * */
        public int union(int a, int b) {
            int x = find(a);
            int y = find(b);

            // 同属于一块
            if(x == y) return 0;

            if(x < y) {
                // 把 y 链接到 x 上
                fa[y] = x;
                size[x] += size[y];
            } else {
                fa[x] = y;
                size[y] += size[x];
            }
            return 1;
        }

        public int getSize(int x) {
            return size[find(x)];
        }
    }

    /**
     * 并查集
     * */
    public static int countComponents_3(int n, int[][] edges) {
        UnionFind unionFind = new UnionFind(n);

        int res = unionFind.cc;
        for(int[] edge: edges) {
            int a = edge[0], b = edge[1];
            res -= unionFind.union(a, b);
        }
        return res;
    }
}
