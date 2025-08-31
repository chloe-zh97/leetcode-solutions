import java.util.Arrays;
import java.util.PriorityQueue;

public class FindMinimumTimetoReachLastRoomII_3342 {
    public static void main(String[] args) {
        int[][] moveTime = {
                {0,4},
                {4,4}
        };
        System.out.println(minTimeToReach(moveTime));
    }
    public static int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length, n = moveTime[0].length;
        int INF = Integer.MAX_VALUE;
        int[][] dirs = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        int[][] distance = new int[m][n];
        for(int i=0;i<m;i++)
            Arrays.fill(distance[i], INF);
        distance[0][0] = 0;

        // minHeap
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        minHeap.offer(new int[]{0,0,0});

        while(!minHeap.isEmpty()) {

            int[] cur = minHeap.poll();
            int x = cur[1], y = cur[2], w = cur[0];

            if(x == m-1 && y == n-1) {
                return w;
            }

            if(distance[x][y] < w) continue;

            // i+j 的奇偶性决定了 time
            int time = (x+y) % 2 + 1;

            // find neighbors
            for(int k=0;k<dirs.length;k++) {
                int newX = x+dirs[k][0], newY = y+dirs[k][1];
                if(newX > -1 && newX < m && newY > -1 && newY < n) {
                    // 合理范围
                    int newDistance = Math.max(w, moveTime[newX][newY]) + time;
                    if(newDistance < distance[newX][newY]) {
                        // System.out.println("x="+x+" y="+y+" newX="+newX+" newY="+newY+" time="+time+" newDis="+newDistance);
                        distance[newX][newY] = newDistance;
                        minHeap.offer(new int[] {newDistance, newX, newY});
                    }
                }
            }

        }
        return 0;
    }
}
