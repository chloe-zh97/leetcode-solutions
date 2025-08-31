import java.nio.DoubleBuffer;
import java.util.*;

public class CarFleet_26 {
    public static void main(String[] args) {
        /**
         * (3, 7/4)
         * (4, 6/4)
         * */
        int[] position = {8,3,7,4,6,5};
        int[] speed = {4,4,4,4,4,4};
        int target = 10;
        System.out.println(carFleet_2(target, position, speed));
    }

    /**
     * Method 1: 单调栈 + 排序
     * O(nlogn)
     *
     * (4,2)
     * (1,2)
     * (0,1)
     * (7,1)
     *
     * (0,1)
     * (1,2)
     * (4,2)
     * (7,1)
     *
     * (position, remainHour)
     * (0,10)
     * (1, 4.5)
     * (4, 3)
     * (7, 3)
     *
     * */
    public static int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        List<double[]> nodes = new ArrayList<>();

        // (position, remainHour)
        for(int i=0;i<n;i++) {
            double p = position[i];
            double r = (target - p) / speed[i];
            nodes.add(new double[]{p, r});
        }

        // sort, 按照 position 从大到小排列
        nodes.sort(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o1[0], o2[0]);
            }
        });

        // 存储 index
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i=0;i<nodes.size();i++) {
            double curRemain = nodes.get(i)[1];
            while(!deque.isEmpty() && curRemain >= nodes.get(deque.peekLast())[1]) {
                // 栈顶能追上现在这个，栈顶出来
                deque.pollLast();
            }
            // 当前入栈
            deque.offerLast(i);
        }
       return deque.size();
    }


    /**
     * Method 2: double[] time
     * time[i]: position i 位置到 target 需要用的时间
     * */
    public static int carFleet_2(int target, int[] position, int[] speed) {
        double[] time = new double[target+1];
        Arrays.fill(time, -1);

        // 遍历每辆小车
        for(int i = 0; i< position.length;i++) {
            int p = position[i];
            time[p] = 1.0 * (target - p) / speed[i];
        }

        Deque<Double> deque = new ArrayDeque<>();
        for(int i=0;i<time.length;i++) {
            if(time[i] < 0) continue;

            while(!deque.isEmpty() && time[i] >= deque.peekLast()) {
                deque.pollLast();
            }

            deque.offerLast(time[i]);
        }
        return deque.size();
    }
}
