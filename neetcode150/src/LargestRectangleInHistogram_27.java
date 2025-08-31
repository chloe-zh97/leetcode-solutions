import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LargestRectangleInHistogram_27 {
    public static void main(String[] args) {
        int[] heights = {2,1,2};
        System.out.println(largestRectangleArea(heights));
        System.out.println(largestRectangleArea_4_optimal(heights));
    }

    /**
     * Method 1: 暴力法 O(n^2)
     * */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length, ans = 0;
        for(int i=0;i<n;i++) {
            int h = heights[i];
            int start = i, end = i;

            while(start-1 >= 0 && heights[start-1] >= h) start--;
            while(end+1 < n && heights[end+1] >= h) end++;

            // [start, end] 符合条件
            int w = end-start+1;
            ans = Math.max(ans, w*h);
        }
        return ans;
    }

    /**
     * Method 2: 预处理，前缀+后缀 O(n) / O(n)
     * int[] left: left[i] 以 height[i] 为高，能往左延伸多少个宽度，包括 i
     * int[] right: right[i] 以 height[i] 为高，能往右衍生多少个宽度，包括 i
     *
     * 错误：这里的 left 和 right 计算错误，应该计算的是当前位置能向左最远 extend 到的位置
     * 需要借助单调栈，栈内存储比如 1,3,4,6 (2)
     * */
    public static int largestRectangleArea_2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        left[0] = 1;
        for(int i=1;i<n;i++) {
            left[i] = heights[i] <= heights[i-1] ? left[i-1]+1 : 1;
        }

        int[] right = new int[n];
        right[n-1] = 1;
        for(int i=n-2;i>=0;i--) {
            right[i] = heights[i] <= heights[i+1] ? right[i+1]+1: 1;
        }

        int ans = 0;
        // 遍历每一个 height
        for(int i=0;i<n;i++) {
            int h = heights[i];
            int w = left[i]+right[i]-1;
            ans = Math.max(ans, h*w);
        }
        return ans;
    }

    /**
     * 利用 Stack 预处理
     * */
    public static int largestRectangleArea_2_modify(int[] heights) {
        int n = heights.length;

        // 存储的是 index
        Deque<Integer> deque = new ArrayDeque<>();

        // left[i]: height[i] 这个i 位置向左最远延伸到的 index
        int[] left = new int[n];
        for(int i=0;i<n;i++) {
            left[i] = 0;
            int cur = heights[i];
            while(!deque.isEmpty() && heights[deque.peekLast()] >= cur) {
                // 栈内元素都高，cur 可以延伸
                deque.pollLast();
            }
            // 退出循环时，栈要么为空，要么 peekLast < cur
            if(!deque.isEmpty())
                left[i] = deque.peekLast()+1;

            deque.offerLast(i);
        }

        deque.clear();
        int[] right = new int[n];
        for(int i=n-1;i>=0;i--) {
            right[i] = n-1;
            int cur = heights[i];
            while(!deque.isEmpty() && heights[deque.peekLast()] >= cur) {
                deque.pollLast();
            }
            if(!deque.isEmpty())
                right[i] = deque.peekLast()-1;
            deque.offerLast(i);
        }
        // System.out.println("right="+ Arrays.toString(right));

        int ans = 0;
        for(int i=0;i<n;i++) {
            int h = heights[i];
            int w = right[i]-left[i]+1;

            ans = Math.max(ans, h*w);
        }
        return ans;
    }

    /**
     * Method 3: One stack pass
     * */
    public static int largestRectangleArea_3_one_pass(int[] heights) {
        // (index, height)
        Deque<int[]> deque = new ArrayDeque<>();
        int n = heights.length;
        int ans = 0;
        for(int i=0;i<n;i++) {
            int lastId = i;
            while(!deque.isEmpty() && deque.peekLast()[1] > heights[i]) {
                // 说明栈顶的元素现在不能延生了
                int[] prev = deque.pollLast();
                lastId = prev[0];

                // 更新 ans
                ans = Math.max(ans, prev[1] * (i-lastId));
            }

            // 退出循环时，新的元素马上可以加入
            int[] cur = {lastId, heights[i]};
            deque.offerLast(cur);
        }

        while(!deque.isEmpty()) {
            int[] cur = deque.pollLast();
            ans = Math.max(ans, cur[1] * (n-cur[0]));
        }
        return ans;
    }


    /**
     * Optimal
     * Stack 中只记录 index
     * */
    public static int largestRectangleArea_4_optimal(int[] heights) {
        int n = heights.length;
        // store index
        Deque<Integer> deque = new ArrayDeque<>();
        int ans = 0;
        for(int i=0;i<=n;i++) {
            while(!deque.isEmpty() && (i==n|| heights[deque.peekLast()] > heights[i])) {
                // 栈顶的元素无法再延生了，需要出栈
                int height = heights[deque.pollLast()];
                int width = deque.isEmpty() ? i : i-deque.peekLast()-1;

                ans = Math.max(ans, height * width);
            }

            deque.offerLast(i);
        }
        return ans;
    }
}


