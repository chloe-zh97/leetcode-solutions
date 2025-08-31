import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class KthSmallestPrimeFraction_786 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,5};
        int k = 3;
        arr = kthSmallestPrimeFraction_3(arr, k);
        System.out.println(Arrays.toString(arr));
    }
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;

        // 分子，分母，i，j
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0]*o2[1] - o2[0]*o1[1]);
        HashSet<String> seen = new HashSet<>();

        // initial
        pq.offer(new int[]{arr[0], arr[n-1], 0, n-1});
        seen.add("0-"+(n-1));

        int[] ans = new int[2];
        while(k-- > 0) {
            int[] p = pq.poll();
            ans[0] = p[0]; ans[1] = p[1];
            // System.out.println(Arrays.toString(ans));
            int i = p[2], j = p[3];
            String k1 = (i+1)+"-"+j;
            String k2 = i+"-"+(j-1);

            if(i+1 < n && !seen.contains(k1)) {
                pq.offer(new int[]{arr[i+1], arr[j], i+1, j});
                seen.add(k1);
            }

            if(!seen.contains(k2)) {
                pq.offer(new int[]{arr[i], arr[j-1], i, j-1});
                seen.add(k2);
            }
        }
        return ans;
    }

    /**
     * Method 2: 大根堆，O(n^2)
     * */
    public static int[] kthSmallestPrimeFraction_2(int[] arr, int k) {
        int n = arr.length;
        // 大堆
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Double.compare(o2[0]*1.0/o2[1], o1[0]*1.0/o1[1]));

        // 遍历每一组可能的组合
        for(int i=0;i<n-1;i++) {
            for(int j=i+1;j<n;j++) {
                double t = arr[i]*1.0/arr[j];
                if(pq.size() < k || pq.peek()[0]*1.0/pq.peek()[1] > t) {
                    // 堆不满，或者当前堆最大的元素大于t
                    pq.offer(new int[]{arr[i], arr[j]});
                    if(pq.size() > k) pq.poll();
                }
            }
        }
        return pq.poll();
    }


    /**
     * Method 3: 多路归并
     * */
    public static int[] kthSmallestPrimeFraction_3(int[] arr, int k) {
        int n = arr.length;
        // 存储 i,j
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            double a1 = arr[o1[0]] * 1.0 / arr[o1[1]];
            double b1 = arr[o2[0]] * 1.0 / arr[o2[1]];
            return Double.compare(a1, b1);
        });

        // initial
        for(int i=1;i<n;i++)
            pq.offer(new int[]{0, i});

        int[] ans = new int[2];
        while(k-- > 0) {
            int[] p = pq.poll();
            int i = p[0], j = p[1];

            ans[0] = arr[i];
            ans[1] = arr[j];
            if(i+1 < j) pq.offer(new int[]{i+1, j});
        }
        return ans;
    }
}
