import java.util.Arrays;

public class ZeroArrayTransformationI_3355 {
    public static void main(String[] args) {
        int[] nums = {4,3,2,1};
        int[][] queries = {
                {1,3},
                {0,2}
        };
        System.out.println(isZeroArray_2(nums, queries));
    }

    /**
     * Method 1: 暴力法, 超时
     * */
    public static boolean isZeroArray(int[] nums, int[][] queries) {
        int m = queries.length, n = nums.length;

        for(int i=0;i<m;i++) {
            int from = queries[i][0], to = queries[i][1];
            for(int j=from;j<=to;j++)
                nums[j]--;
        }

        for(int i=0;i<n;i++) {
            if(nums[i] > 0) return false;
        }
        return true;
    }

    /**
     * Method 2: 差分数组
     * */
    public static boolean isZeroArray_2(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        // d[i]: nums 数组中 i 位置以后需要加减多少
        int[] d = new int[n+1];
        for(int i=0;i<m;i++) {
            int from = queries[i][0], to = queries[i][1];
            d[from]--;
            d[to+1]++;
        }

        System.out.println(Arrays.toString(d));

        int s = 0;
        for(int i=0;i<n;i++) {
            s += d[i];
            int num = nums[i] + s;
            System.out.println("i="+i+" num="+num);
            if(num > 0) return false;
        }

        return true;
    }
}
