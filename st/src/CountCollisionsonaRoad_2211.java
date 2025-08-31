import java.util.ArrayDeque;
import java.util.Deque;

public class CountCollisionsonaRoad_2211 {
    public static void main(String[] args) {
        String directions = "SSRSSRLLRSLLRSRSSRLRRRRLLRRLSSRR";
        System.out.println(countCollisions_2(directions));
    }

    /**
     * Method 1: stack + 模拟
     * 撞车情况
     * RS
     * RL
     * SL
     * */
    public static int countCollisions(String directions) {
        char[] chs = directions.toCharArray();
        int n = directions.length();
        Deque<Character> stack = new ArrayDeque<>();
        int ans = 0;

        for(int i=0;i<n;i++) {
            char c = chs[i];

            if(!stack.isEmpty() && collisionHappen(stack.peekLast(), c)) {
                // 车祸发生
                ans++;
                if(stack.peekLast() == 'R' && c == 'L') ans++;

                // 事故车辆变为 S
                stack.pollLast();
                stack.offerLast('S');
                stack.offerLast('S');
            } else {
                stack.offerLast(c);
            }
        }

        // 统计 RS
        int rcount = 0;
        while(!stack.isEmpty()) {
            char c = stack.pollFirst();
            if(c == 'R') rcount++;
            else {
                ans += rcount;
                rcount = 0;
            }
        }

        return ans;
    }

    private static boolean collisionHappen(char pre, char cur) {
        return (pre == 'R' && cur != 'R') || (pre == 'S' && cur == 'L');
    }

    /**
     * Method 2: 前缀为 L 的车不会被撞，后缀为R的车不会被撞
     * */
    public static int countCollisions_2(String directions) {
        int n = directions.length();
        char[] chs = directions.toCharArray();

        int i = 0, j = n-1;
        while(i < n && chs[i] == 'L') i++;
        // 退出循环 chs[i] != L
        while(j >= 0 && chs[j] == 'R') j--;
        // 退出循环 chs[j] == L

        int sCount = 0;
        for(int k=i;k<=j;k++) {
            if(chs[k] == 'S') sCount += 1;
        }
        return Math.max(j-i+1-sCount, 0);
    }
}
