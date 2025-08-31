import java.util.*;

public class KClosestPointstoOrigin_61 {
    public static void main(String[] args) {
        int[][] points = {
                {0,2},
                {2,0},
                {2,2}
        };
        int k = 2;

        int[][] ans = kClosest_3(points, k);
        for(int[] a: ans)
            System.out.println(Arrays.toString(a));
    }

    /**
     * Method 1: 最大堆
     * O(nlogk)
     * */

    public static int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0]*o2[0]+o2[1]*o2[1] - (o1[0]*o1[0]+o1[1]*o1[1]);
            }
        });

        for(int[] p: points) {
            pq.offer(p);
            if(pq.size() > k) pq.poll();
        }

        int[][] ans = new int[pq.size()][];
        for(int i=0;i<ans.length;i++)
            ans[i] = pq.poll();
        return ans;
    }

    /**
     * 最小堆
     * */
    public static int[][] kClosest_2(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // O(n)
        for (int[] p : points) {
            pq.offer(new int[]{p[0] * p[0] + p[1] * p[1], p[0], p[1]});
        }

        int[][] ans = new int[k][];
        for (int i = 0; i < k; i++) {
            int[] p = pq.poll();
            ans[i] = new int[]{p[1], p[2]};
        }
        return ans;
    }


    /**
     * Method 3: Quick Sort
     * */
    private static Random random = new Random();

    public static int[][] kClosest_3(int[][] points, int k) {
        quickSort(points, 0, points.length-1);
        int[][] ans = new int[k][];
        System.arraycopy(points, 0, ans, 0, k);
        return ans;
    }

    private static void quickSort(int[][] points, int left, int right) {
        if(left >= right) return;
        int pivot = partition(points, left, right);

        quickSort(points, left, pivot-1);
        quickSort(points, pivot+1, right);
    }

    /**
     * partition
     * */
    private static int partition(int[][] points, int left, int right) {
        int randomIndex = left + random.nextInt(right-left+1);

        swap(points, randomIndex, left);

        // 目标值, <= target 的都在 randomIndex 的左边
        int target = points[left][0] * points[left][0] + points[left][1] * points[left][1];

        int i = left + 1, j = right;

        while(true) {
            // 找到左边第一个 >= target 的位置
            while(i <= j && points[i][0]*points[i][0] + points[i][1] * points[i][1] < target) i++;
            while(i <= j && points[j][0]*points[j][0] + points[j][1] * points[j][1] > target) --j;

            if(i >= j) break;

            swap(points, i, j);

            i++;
            --j;
        }

        // 这里换的是 j
        swap(points, left, j);
        return j;
    }

    private static void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }
}
