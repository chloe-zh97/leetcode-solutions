import java.util.Arrays;
import java.util.PriorityQueue;

public class FindKthLargestXORCoordinateValue_1738 {
    public static void main(String[] args) {
        int[][] matrix = {
                {8,10,5,8,5,7,6,0,1,4,10,6,4,3,6,8,7,9,4,2}
        };
        int k = 2;
        System.out.println(kthLargestValue(matrix, k));
    }

    public static int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;

        int[][] s = new int[m+1][n+1];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                s[i+1][j+1] = s[i+1][j] ^ s[i][j+1] ^ s[i][j] ^ matrix[i][j];
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                queue.offer(s[i+1][j+1]);
                if(queue.size() > k) queue.poll();
            }
        }

       // System.out.println("queue size:"+queue.size());

        // 由大到小存储
       return queue.peek();
    }

    public static int kthLargestValue_2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m+1][n+1];
        int[] ans = new int[m*n];

        int id = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                s[i+1][j+1] = s[i+1][j] ^ s[i][j+1] ^ s[i][j] ^ matrix[i][j];
                ans[id++] = s[i+1][j+1];
            }
        }

        Arrays.sort(ans);
        return ans[ans.length - k];
    }
}
