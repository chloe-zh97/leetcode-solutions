public class KthSmallestNumberinMultiplicationTable_668 {
    public static void main(String[] args) {
        int m = 2, n = 3, k = 6;
        System.out.println(findKthNumber_2(m,n,k));
    }

    /**
     * Method 1: 二分，超时
     * */
    public static int findKthNumber(int m, int n, int k) {
        int left = 1, right = m*n;
        while(left < right) {
            int mid = left + right >> 1;
            if(check(m,n,mid,k)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private static boolean check(int m, int n, int val, int k) {
        int cnt = 0;
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                if(i*j > val) break;

                if(i*j <= val) cnt++;
                if(cnt >= k) return true;
            }
        }
        return false;
    }

    /**
     * Method 2: 二分，找格子中第 k 小的数，等价于求格子中有多少个数 <= x
     * */
    public static int findKthNumber_2(int m, int n, int k) {
        // 二分 x
        int left = 1, right = m*n;
        while(left < right) {
            int mid = left + right >> 1;
            if(check_2(m, n, k, mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    /**
     * 求每一行 <= val 的数有多少，每一行都是 i 的倍数
     * */
    private static boolean check_2(int m, int n, int k, int val) {
        // 遍历每一行
        int cnt = 0;
        for(int i=1;i<=m;i++) {
            cnt += Math.min(val/i, n);
            if(cnt >= k) return true;
        }
        return cnt >= k;
    }
}
