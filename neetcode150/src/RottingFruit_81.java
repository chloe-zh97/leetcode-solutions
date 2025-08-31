import java.util.ArrayDeque;
import java.util.Deque;

public class RottingFruit_81 {
    public static void main(String[] args) {
        int[][] grid = {
                {1,0,1},
                {0,2,0},
                {1,0,1}
        };
        System.out.println(orangesRotting(grid));
    }

    private static int m;
    private static int n;

    public static int orangesRotting(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        Deque<int[]> deque = new ArrayDeque<>();

        // 腐烂的数目
        int rotten = 0;
        int fresh = 0;

        // start from every rotten
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == 1) fresh++;
                else if(grid[i][j] == 2) {
                    deque.offer(new int[]{i,j});
                    rotten++;
                }
            }
        }

        if(fresh == 0) return 0;

        int[][] dirs = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        // 开始 bfs
        int time = 0;

        while(!deque.isEmpty()) {
            int size = deque.size();
//            System.out.println("size="+size);

            for(int i=0;i<size;i++) {
                int[] cur = deque.poll();
                int x = cur[0], y = cur[1];
//                System.out.println("x="+x+" y="+y + " time="+time);

                for(int k=0;k<dirs.length;k++) {
                    int newX = x+dirs[k][0], newY = y+dirs[k][1];
                    if(inArea(newX, newY) && grid[newX][newY] == 1) {
                        fresh--;
                        // mark to rotten
                        grid[newX][newY] = 2;
                        deque.offer(new int[]{newX, newY});
                    }
                }
            }
            time++;
        }

        return fresh == 0 ? time-1 : -1;
    }

    private static boolean inArea(int row, int col) {
        return row > -1 && row < m && col > -1 && col < n;
    }
}
