import java.util.ArrayList;
import java.util.List;

public class RobotBoundedInCircle_1041 {
    public static void main(String[] args) {
        String instructions = "GL";
        System.out.println(isRobotBounded(instructions));
    }

    public static boolean isRobotBounded(String instructions) {
        int n = instructions.length();
        char[] chs = instructions.toCharArray();

        List<int[]> dirs = new ArrayList<>();
        dirs.add(new int[]{0,1});  // N
        dirs.add(new int[]{1,0});  // E
        dirs.add(new int[]{0,-1});  // S
        dirs.add(new int[]{-1,0});  // W

        int d = 0, i = 0, j = 0;

        // 执行一遍指令
        for(int k=0;k<n;k++) {
            if(chs[k] != 'G') {
                d += chs[k] == 'L' ? -1 : 1;
                d = (d+4) % 4;
            } else {
                // 前进
                i += dirs.get(d)[0];
                j += dirs.get(d)[1];
            }
        }
        return (i==0 && j== 0) || d != 0;
    }
}
