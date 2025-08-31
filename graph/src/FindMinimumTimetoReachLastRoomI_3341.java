import java.util.*;

public class FindMinimumTimetoReachLastRoomI_3341 {
    public static void main(String[] args) {
        int[][] moveTime = {
                {0,0,0},
                {0,0,0}
        };
        System.out.println(minTimeToReach(moveTime));
    }

    public static int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length, n = moveTime[0].length;
        int INF = Integer.MAX_VALUE / 2;
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

        // create minHeap
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        // arrival time, i, j
        minHeap.offer(new int[]{0, 0, 0});

        while(!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            // time arrive at (x, y)
            int w1 = cur[0], x = cur[1], y = cur[2];

            if(x == m-1 && y == n-1) return w1;

            // ignore it
            if(w1 > distance[x][y]) continue;

            // find its neighbors
            for(int k=0;k<dirs.length;k++) {
                int newX = x+dirs[k][0], newY = y+dirs[k][1];
                if(newX > -1 && newX < m && newY > -1 && newY < n) {
                    // 到 x 节点的时间和 newX，newY 开放时间的最晚值，moveTime表示可以开始移向 newX,newY
                    int newDistance = Math.max(w1, moveTime[newX][newY]) + 1;

                    if(newDistance < distance[newX][newY]) {
                        distance[newX][newY] = newDistance;
                        minHeap.offer(new int[]{newDistance, newX, newY});
                    }
                }
            }

        }

        return 0;

    }


}
