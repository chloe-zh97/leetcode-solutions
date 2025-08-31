import java.util.*;

public class CourseScheduleIV_1462 {

    public static void main(String[] args)  {
        int numCourses = 2;
        int[][] prerequisites = {
                {1,0}
        };
        int[][] queries = {
                {0,1},
                {1,0}
        };
        List<Boolean> ans = checkIfPrerequisite(numCourses, prerequisites, queries);
        System.out.println(ans);
    }

    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        boolean[][] dp = new boolean[numCourses][numCourses];

        // construct adj
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int i=0;i<numCourses;i++)
            adj.put(i, new ArrayList<>());

        int[] indegrees = new int[numCourses];

        for(int[] edge: prerequisites) {
            int from = edge[0], to = edge[1];
            List<Integer> neis = adj.getOrDefault(from, new ArrayList<>());
            neis.add(to);
            adj.put(from, neis);

            indegrees[to]++;
        }

        // bfs
        Deque<Integer> deque = new LinkedList<>();
        for(int i=0;i<indegrees.length;i++) {
            if(indegrees[i] == 0) deque.offerLast(i);
        }

        while(!deque.isEmpty()) {
            int cur = deque.pollFirst();
            indegrees[cur]--;

            dp[cur][cur] = true;

            for(int nei: adj.get(cur)) {
                dp[cur][nei] = true;

                // 能到cur的也能到nei
                for(int i=0;i<numCourses;i++)
                    dp[i][nei] |= dp[i][cur];

                indegrees[nei]--;
                if(indegrees[nei] == 0) deque.offerLast(nei);
            }
        }

        for(int[] q: queries) {
            int from = q[0], to = q[1];
            ans.add(dp[from][to]);
        }
        return ans;
    }

     public static List<Boolean> checkIfPrerequisite_2(int numCourses, int[][] prerequisites, int[][] queries) {
         List<Boolean> ans = new ArrayList<>();

         // construct adj
         List<Integer>[] adj = new ArrayList[numCourses];
         Arrays.setAll(adj, g -> new ArrayList());

         // construct adj
         for(int[] edge: prerequisites) {
             int from = edge[0], to = edge[1];
             adj[from].add(to);
         }

         for(int[] q: queries) {
             int from = q[0], to = q[1];
             boolean r = dfs(adj, from, to, new HashSet<>());
             ans.add(r);
         }
         return ans;
     }

     // dfs(adj, from, to, path) 邻接表，当前节点，dfs 上的路径
     private static boolean dfs(List<Integer>[] adj, int from, int to, Set<Integer> path) {
         if(from == to) return true;

         // 以及访问过了这个节点
         if(path.contains(from)) return false;

         // 标记为访问
         path.add(from);

         for(int nei: adj[from]) {
             boolean res = dfs(adj, nei, to, path);
             if(res) return true;
         }
         return false;
     }

}
