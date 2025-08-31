import java.util.*;

public class NetworkDelayTime_743 {

    // Dijkstra
    public static int networkDelayTime(int[][] times, int n, int k) {
        int INF = Integer.MAX_VALUE / 2;
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for(int i=0;i<n+1;i++)
            adj.put(i, new ArrayList<>());

        for(int[] edge: times) {
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        int[] minDist = new int[n+1];
        Arrays.fill(minDist, INF);
        minDist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0]-b[0]);
        pq.offer(new int[]{0, k});
        Set<Integer> visit = new HashSet<>();

        int ans = 0, cnt = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            // 到 u 的距离为 w1
            int w1 = cur[0], u = cur[1];

            if(w1 > minDist[u]) continue;

            // 否则当前到 u 的距离就是到 u 的最小距离
            minDist[u] = w1;
            visit.add(u);
            cnt++;

            ans = Math.max(ans, minDist[u]);
            if(ans == INF) return -1;

            for(int[] nei: adj.get(u)) {
                int v = nei[0], w2 = nei[1];
                if(!visit.contains(v) && minDist[v] > w1+w2) {
                    minDist[v] = w1+w2;
                    pq.offer(new int[]{minDist[v], v});
                }
            }
        }
        return cnt == n ? ans : -1;

    }

}
