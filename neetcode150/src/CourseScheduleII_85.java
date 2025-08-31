import java.util.*;

public class CourseScheduleII_85 {
    public static void main(String[] args) {
        int numCourses = 3;
        int[][] prerequisites = {
                {1,0}
        };

        int[] ans = findOrder_2(numCourses, prerequisites);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * Method 1: 拓扑排序 BFS
     * */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        Arrays.setAll(adj, g -> new ArrayList<>());

        int[] indegrees = new int[numCourses];
        Arrays.fill(indegrees, 0);

        for(int[] p: prerequisites) {
            int from = p[1], to = p[0];
            adj[from].add(to);
            indegrees[to]++;
        }

        // 将入度为0的点加入到队列中
        Deque<Integer> deque = new LinkedList<>();
        for(int i=0;i<numCourses;i++) {
            if(indegrees[i] == 0) deque.offer(i);
        }

        int[] ans = new int[numCourses];
        int k = 0;

        while(!deque.isEmpty()) {
            int p = deque.poll();
            ans[k++] = p;

            for(int nei: adj[p]) {
                indegrees[nei]--;
                if(indegrees[nei] == 0) deque.offer(nei);
            }
        }
        return k == numCourses ? ans : new int[]{};
    }

    /**
     * Method 2: 拓扑排序 DFS
     * */
    private static List<Integer> path = new ArrayList<>();

    public static int[] findOrder_2(int numCourses, int[][] prerequisites) {
        // construct adj list
        List<Integer>[] adj = new ArrayList[numCourses];
        Arrays.setAll(adj, g -> new ArrayList<>());

        int[] indegrees = new int[numCourses];

        for(int[] p: prerequisites) {
            int from = p[1], to = p[0];
            indegrees[to]++;
            adj[from].add(to);
        }

        // 对 indegree 为0的点进行 dfs
        for(int i=0;i<numCourses;i++) {
            if(indegrees[i] == 0) {
                dfs(adj, indegrees, i);
            }
        }

        if(path.size() != numCourses) return new int[]{};

        int[] ans = new int[numCourses];
        for(int i=0;i<numCourses;i++) {
            ans[i] = path.get(i);
        }
        return ans;
    }

    private static void dfs(List<Integer>[] adj, int[] indegrees, int node) {
        path.add(node);
        // 让这个点的 indegree 变为-1，之后不会被访问到
        indegrees[node]--;
        for(int nei: adj[node]) {
            indegrees[nei]--;
            if(indegrees[nei] == 0)
                dfs(adj, indegrees, nei);
        }
    }
}
