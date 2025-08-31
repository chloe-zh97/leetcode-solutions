import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class AsteroidCollision_735 {
    public static void main(String[] args) {
        int[] asteroids = {10,2,-5};
        asteroids = asteroidCollision_2(asteroids);
        System.out.println(Arrays.toString(asteroids));
    }

    /**
     * Method 1: 栈模拟
     * 如果当前是正数，直接加入栈顶
     * 如果是负数，且栈顶是正数，产生新的元素
     * */
    public static int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i=0;i<n;i++) {
            int cur = asteroids[i];
            if(cur > 0) deque.offerLast(cur);
            else {
                // 当前元素是负数，可能会发生冲撞
                if(!deque.isEmpty() && deque.peekLast() > 0) {
                    int sign = Math.abs(cur) > Math.abs(deque.peekLast()) ? -1: (cur+deque.peekLast() == 0 ? 0 : 1);
                    if(sign == 0) {
                        deque.pollLast();
                    } else if(sign == 1) {
                        // 说明栈顶更大
                        continue;
                    } else {
                        // 说明负数更大，栈顶弹出
                        deque.pollLast();
                        i--;
                    }
                } else {
                    deque.offerLast(cur);
                }
            }
        }

        int[] ans = new int[deque.size()];
        for(int i=0;i<ans.length;i++) {
            ans[i] = deque.pollFirst();
        }
        return ans;
    }

    /**
     * Method 2: 添加变量 boolean 判断是否应该加入
     * */
    public static int[] asteroidCollision_2(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();

        for(int c: asteroids) {
            boolean shouldAdd = true;
            while(shouldAdd && !deque.isEmpty() && deque.peekLast() > 0 && c < 0) {
                int a = deque.peekLast(), b = -c;
                if(a <= b) deque.pollLast();
                if(a >= b) shouldAdd =false;
            }
            if(shouldAdd) deque.offerLast(c);
        }

        int[] ans = new int[deque.size()];
        for(int i=0;i<ans.length;i++) {
            ans[i] = deque.pollFirst();
        }
        return ans;
    }
}
