import java.util.*;

public class CourseSchedule_84 {
    public static void main(String[] args) {
        int numCourses = 5;
        int[][] prerequisites = {
                {1,4},
                {2,4},
                {3,1},
                {3,2}
        };
        System.out.println(canFinish_3(numCourses, prerequisites));
    }


//    static class GNode {
//        int value;
//        List<Edge> edges;
//
//        public GNode(int _val) {
//            value = _val;
//            edges = new ArrayList<>();
//        }
//
//        public GNode(int _val, List<Edge> _edges) {
//            value = _val;
//            edges = _edges;
//        }
//    }
//
//    static class Edge{
//        GNode from;
//        GNode to;
//
//        public Edge() {}
//
//        public Edge(GNode _from, GNode _to) {
//            from = _from;
//            to = _to;
//        }
//
//        @Override
//        public String toString() {
//            return "from:"+from.value+" to:"+to.value;
//        }
//    }
//
//    /**
//     * Method 1: DFS
//     * */
//    private static Map<Integer, GNode> nodeMap = new HashMap<>();
//
//    private static boolean hasCycle = false;
//
//    public static boolean canFinish(int numCourses, int[][] prerequisites) {
//        constructGraph(prerequisites);
//        // printGraph();
//
//        HashSet<GNode> visit;
//
//        // 遍历图的每个节点
//        for(int i=0;i<numCourses;i++) {
//            if(nodeMap.containsKey(i)) {
//                GNode node = nodeMap.get(i);
//
//                visit = new HashSet<>();
//                dfs(node, visit);
//
//                if(hasCycle) return false;
//            }
//        }
//        return true;
//    }
//
//
//    private static void dfs(GNode node, HashSet<GNode> seen) {
//        if(node == null || node.edges.isEmpty()) return;
//
//        if(seen.contains(node)) {
//            hasCycle = true;
//            return;
//        }
//
//        seen.add(node);
//        // get all neighbors
//        for(Edge edge: node.edges) {
//            GNode to = edge.to;
//            dfs(to, seen);
//        }
//    }
//
//
//    private static void constructGraph(int[][] prerequisites) {
//        for(int[] item: prerequisites) {
//            int from = item[0], to = item[1];
//
//            // 构造节点
//            GNode nfrom = nodeMap.getOrDefault(from, new GNode(from));
//            GNode nto = nodeMap.getOrDefault(to, new GNode(to));
//            nodeMap.put(from, nfrom);
//            nodeMap.put(to, nto);
//
//            Edge edge = new Edge(nfrom, nto);
//            nodeMap.get(from).edges.add(edge);
//        }
//    }
//
//    private static void printGraph() {
//        for(Map.Entry<Integer, GNode> entry: nodeMap.entrySet()) {
//            GNode node = entry.getValue();
//
//            System.out.println("node:"+node.value);
//            for(Edge edge: node.edges) {
//                System.out.println(edge);
//            }
//        }
//    }


    /**
     * Method 2: 三色染色法 DFS
     * */
//    public static boolean canFinish_2(int numCourses, int[][] prerequisites) {
//        List<Integer>[] graph = new ArrayList[numCourses];
//        Arrays.setAll(graph, g-> new ArrayList<>());
//
//        // graph 中存储的是每个节点和它的 to node
//        for(int[] p: prerequisites) {
//            int from = p[1], to = p[0];
//            graph[from].add(to);
//        }
//
//        // color = 0, 表示这个点没有被访问过
//        // color = 1, dfs 还没返回，这个点在路径上
//        // color = 2, dfs 这个点已经被访问过了
//        int[] colors = new int[numCourses];
//        Arrays.fill(colors, 0);
//
//        // 对每个节点进行 dfs
//        for(int i=0;i<numCourses;i++) {
//            if(colors[i] == 0) {
//                boolean hasCycle = dfs(i, colors, graph);
//                if(hasCycle) return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean dfs(int node, int[] colors, List<Integer>[] graph) {
//        if(colors[node] == 1) return true;
//
//        // 标记这个点的状态, 正在 dfs 的路径上
//        colors[node] = 1;
//
//        // 遍历它的邻居节点
//        for(int nei: graph[node]) {
//            // 剪枝条
//            if(colors[nei] == 1) return true;
//            else if(colors[nei] == 0) {
//                boolean res = dfs(nei, colors, graph);
//                if (res) return true;
//            }
//        }
//
//        colors[node] = 2;
//        return false;
//    }
//
//    private static boolean dfs_2(int node, int[] colors, List<Integer>[] graph) {
//        // 标记这个点的状态, 正在 dfs 的路径上
//        colors[node] = 1;
//
//        // 遍历它的邻居节点
//        for(int nei: graph[node]) {
//            if(colors[nei] == 1 || colors[nei] == 0 && dfs(nei, colors, graph)) return true;
//        }
//
//        colors[node] = 2;
//        return false;
//    }

    /**
     * Method 3: 拓扑排序，BFS
     * */
    public static boolean canFinish_3(int numCourses, int[][] prerequisites) {
        // 构建邻接表
        List<Integer>[] adj = new ArrayList[numCourses];
        Arrays.setAll(adj, g->new ArrayList<>());

        // 存储每个节点的入度
        int[] indegrees = new int[numCourses];

        for(int[] p: prerequisites) {
            int from = p[1], to = p[0];
            indegrees[to]++;
            adj[from].add(to);
        }

        // 将 indegree 为0的点加入到队列
        Deque<Integer> deque = new LinkedList<>();
        for(int i=0;i<numCourses;i++) {
            if(indegrees[i] == 0) deque.offer(i);
        }

        int finished = 0;
        while(!deque.isEmpty()) {
            int p = deque.poll();
            finished++;

            // 获取 p 的邻居
            for(int nei: adj[p]) {
                indegrees[nei]--;
                if(indegrees[nei] == 0) deque.offer(nei);
            }
        }
        return finished == numCourses;
    }
}
