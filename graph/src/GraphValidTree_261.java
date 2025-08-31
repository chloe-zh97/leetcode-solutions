import java.util.Arrays;

public class GraphValidTree_261 {
    public static void main(String[] args)  {
        int[][] edges = {
                {0,1},
                {0,2},
                {0,3},
                {1,4}
        };
        int n = 5;
        System.out.println(validTree(n, edges));
    }

    public static boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for(int[] edge: edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.cc == 1 && edges.length == n - 1;
    }

    static class UnionFind {
        // 每个节点的夫节点
        int[] parent;
        // 每个连通分量的大小
        int[] rank;
        int cc;

        public UnionFind(int n) {
            parent = new int[n];
            for(int i=0;i<n;i++) parent[i] = i;
            rank = new int[n];
            Arrays.fill(rank, 1);
            cc = n;
        }

        public int find(int x) {
            if(x != parent[x]) return find(parent[x]);
            return x;
        }

        public boolean isConnect(int x, int y) {
            x = find(x);
            y = find(y);
            return x == y;
        }

        public int union(int x, int y) {
            x = find(x);
            y = find(y);
            if(x == y) return 0;

            if(x < y) {
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


}
