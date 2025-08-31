import java.util.*;

public class GraphValidTree_86 {
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {
                {0,1},
                {2,3}
        };
        System.out.println(validTree_2(n, edges));
    }


    /**
     * DFS 检测是否有环, 无向图, 需要记录上一个节点 parent
     * */
    public static boolean validTree(int n, int[][] edges) {
        // construct adj table
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, g -> new ArrayList<>());

        for(int[] edge: edges) {
            int a = edge[0], b = edge[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        HashSet<Integer> visit = new HashSet<>();

        // 从任意一个节点出发，都可以访问到所有的节点
        boolean res = dfs(adj, 0, -1, visit);
        return res && visit.size() == n;
    }

    private static boolean dfs(List<Integer>[] adj, int node, int prev, HashSet<Integer> visit) {
        // 以及访问过，检测到了环
        if(visit.contains(node)) return false;

        visit.add(node);

        for(int nei: adj[node]) {
            if(nei == prev) continue;

            boolean res = dfs(adj, nei, node, visit);
            if(!res) return false;
        }
        return true;
    }


    /**
     * Method 2 (WA): BFS, 同样需要记录 parent
     * */
//    public static boolean validTree_2(int n, int[][] edges) {
//        List<Integer>[] adj = new ArrayList[n];
//        Arrays.setAll(adj, g -> new ArrayList<>());
//
//        for(int[] edge: edges) {
//            int a = edge[0], b = edge[1];
//            adj[a].add(b);
//            adj[b].add(a);
//        }
//
//        HashSet<Integer> visit = new HashSet<>();
//        Deque<Integer> deque = new ArrayDeque<>();
//        deque.offer(0);
//        visit.add(0);
//        int prev = -1;
//
//        while(!deque.isEmpty()) {
//            int p = deque.poll();
//
//            for(int nei: adj[p]) {
//                // 跳过 prev
//                if(nei == prev) continue;
//
//                // 之前访问过了，直接返回 false
//                if(visit.contains(nei)) return false;
//
//                deque.offer(nei);
//                visit.add(nei);
//            }
//
//            // 这里不对，把 p 的孩子节点加完以后不能更改 prev
//            prev = p;
//        }
//
//        return visit.size() == n;
//    }


    /**
     * Method 2: BFS
     * */
    public static boolean validTree_2(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, g -> new ArrayList<>());

        for(int[] edge: edges) {
            int a = edge[0], b = edge[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        Set<Integer> visit = new HashSet<>();

        Deque<int[]> deque = new LinkedList<>();
        deque.offer(new int[] {0, -1});
        visit.add(0);

        while(!deque.isEmpty()) {
            int[] cur = deque.poll();

            int prev = cur[1], p = cur[0];

            for(int nei: adj[p]) {
                if(prev == nei) continue;

                if(visit.contains(nei)) return false;

                visit.add(nei);
                deque.offer(new int[]{nei, p});
            }
        }
        return visit.size() == n;
    }

}
