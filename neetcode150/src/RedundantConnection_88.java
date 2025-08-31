import java.util.*;

public class RedundantConnection_88 {
    public static void main(String[] args) {
        int[][] edges = {
                {1,2},
                {2,3},
                {3,4},
                {1,4},
                {1,5}
        };
        System.out.println(Arrays.toString(findRedundantConnection_3(edges)));

    }

    static class UnionFind {
        int[] fa;
        int[] size;
        int cc;

        public UnionFind(int n) {
            fa = new int[n];
            Arrays.setAll(fa, i -> i);

            size = new int[n];
            Arrays.fill(size, 1);

            cc = n;
        }

        public int find(int x) {
            if(fa[x] != x) fa[x] = find(fa[x]);
            return fa[x];
        }

        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }

        public int union(int x, int y) {
            x = find(x);
            y = find(y);

            if(x == y) return 0;

            if(x < y) {
                // link y to x
                fa[y] = x;
                size[x] += size[y];
            } else {
                fa[x] = y;
                size[y] += size[x];
            }
            cc--;
            return 1;
        }

        public int getSize(int x) {
            return size[find(x)];
        }
    }

    /**
     * 并查集
     * 每加入一条边，cc 的数目就要-1
     * */
    public static int[] findRedundantConnection(int[][] edges) {
        int n = 0;
        for(int[] edge: edges) {
            n = Math.max(Math.max(edge[0], edge[1]), n);
        }

        UnionFind unionFind = new UnionFind(n);

        for(int[] edge: edges) {
            int prev = unionFind.cc;
            unionFind.union(edge[0]-1, edge[1]-1);
            if(prev == unionFind.cc) return edge;
        }

        return new int[]{};
    }

    /**
     * Method 2: DFS detect cycle
     * */
    public static int[] findRedundantConnection_2(int[][] edges) {
        int n = edges.length;
        // construct adj table
        List<Integer>[] adj = new ArrayList[n+1];
        Arrays.setAll(adj, g -> new ArrayList<>());

        for(int[] edge: edges) {
            int a = edge[0], b = edge[1];
            adj[a].add(b);
            adj[b].add(a);

            boolean[] visit = new boolean[n+1];
            // 每次增加一条边就检测是否有环
            if(dfs(adj, visit, a, -1)) return edge;
        }

        return edges[n-1];
    }

    private static boolean dfs(List<Integer>[] adj, boolean[] visit, int node, int prev) {
        // 访问过了，说明有环
        if(visit[node]) return true;

        visit[node] = true;

        // 对邻居节点 dfs
        for(int nei: adj[node]) {
            // 跳过父亲节点
            if(nei == prev) continue;

            if(dfs(adj, visit, nei, node)) return true;
        }
        return false;
    }


    /**
     * Method 3: BFS, 拓扑排序
     * */
    public static int[] findRedundantConnection_3(int[][] edges) {
        int n = edges.length;
        List<Integer>[] adj = new ArrayList[n+1];
        Arrays.setAll(adj, g -> new ArrayList<>());

        int[] indegrees = new int[n+1];

        for(int[] edge: edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
            indegrees[u]++;
            indegrees[v]++;
        }

        Deque<Integer> deque = new LinkedList<>();
        for(int i=1;i<=n;i++) {
            if(indegrees[i] == 1) deque.offer(i);
        }

        while(!deque.isEmpty()) {
            int p = deque.poll();
            // 这时候入度变成 0
            indegrees[p]--;

            for(int nei: adj[p]) {
                indegrees[nei]--;
                // 不会被重复加入到队列中
                if(indegrees[nei] == 1) {
                    deque.offer(nei);
                }
            }
        }

        for(int i=edges.length-1;i>=0;i--) {
            int u = edges[i][0], v = edges[i][1];
            if(indegrees[u] == 2 && indegrees[v] > 0) return edges[i];
        }

        return edges[n-1];
    }
}
