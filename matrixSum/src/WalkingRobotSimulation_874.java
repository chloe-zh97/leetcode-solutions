import java.util.*;

public class WalkingRobotSimulation_874 {
    public static void main(String[] args) {
        int[] commands = {-2,-1,8,9,6};
        int[][] obstacles = {
                {-1,3},
                {0,1},
                {-1,5},
                {-2,-4},
                {5,4},
                {-2,-3},
                {5,-1},
                {1,-1},
                {5,5},
                {5,2}
        };
        System.out.println(robotSim_2(commands, obstacles));
    }

    public static int robotSim(int[] commands, int[][] obstacles) {
        HashMap<String, Character> faceTo = new HashMap<>();
        faceTo.put("N-1", 'E');
        faceTo.put("N-2", 'W');
        faceTo.put("S-1", 'W');
        faceTo.put("S-2", 'E');

        faceTo.put("E-1", 'S');
        faceTo.put("E-2", 'N');
        faceTo.put("W-1", 'N');
        faceTo.put("W-2", 'S');

        HashMap<Character, int[]> dirs = new HashMap<>();
        dirs.put('N', new int[]{0,1});
        dirs.put('S', new int[]{0,-1});
        dirs.put('W', new int[]{-1,0});
        dirs.put('E', new int[]{1,0});

        HashSet<String> points = new HashSet<>();
        for(int[] ob: obstacles) {
            String key = ob[0] + "" + ob[1];
            points.add(key);
        }

        char curFactTo = 'N';
        int i = 0, j = 0, ans =0;
        for(int k=0;k<commands.length;k++) {
            if(commands[k] < 0) {
                // 改变方向
                String cm = curFactTo+""+commands[k];
                curFactTo = faceTo.get(cm);
                ans = Math.max(ans, i*i+j*j);
            } else {
                // 直接行走, 获取方向
                int[] dir = dirs.get(curFactTo);
                int step = commands[k];
                for(int s=0;s<step;s++) {
                    String tmp = i+dir[0] + "" + j+dir[1];
                    if(!points.contains(tmp)) {
                        i += dir[0];
                        j += dir[1];
                        ans = Math.max(ans, i*i+j*j);
                    }

//                    i += dir[0];
//                    j += dir[1];
//                    String tmp = i + "" + j;
//                    if(points.contains(tmp)) {
//                        // 有障碍
//                        i -= dir[0];
//                        j -= dir[1];
//                        ans = Math.max(ans, i*i+j*j);
//                        break;
//                    } else {
//                        ans = Math.max(ans, i*i+j*j);
//                    }
                }
            }
        }
        return ans;
    }

    public static int robotSim_2(int[] commands, int[][] obstacles) {
        HashSet<String> obs = new HashSet<>();
        for(int[] ob: obstacles) {
            String key = ob[0] + "," + ob[1];
            obs.add(key);
        }

        int[][] dirs = {
                {0,1}, // N
                {1,0}, // E
                {0,-1}, // S
                {-1,0} // W
        };
        int d = 0, pi = 0, pj = 0, ans = 0;

        for(int i=0;i<commands.length;i++) {
            int c = commands[i];
            if(c == -1) d = (d + 1) % 4;
            else if(c == -2) d = (d+3) % 4;
            else {
                for(int k=0;k<c;k++) {
                    String key = (pi+dirs[d][0]) + "," + (pj+dirs[d][1]);
                    System.out.println("key="+key);
                    if(!obs.contains(key)) {
                        pi += dirs[d][0];
                        pj += dirs[d][1];
                        ans = Math.max(ans, pi*pi+pj*pj);
                    } else break;
                }
            }
        }
        return ans;
    }
}
