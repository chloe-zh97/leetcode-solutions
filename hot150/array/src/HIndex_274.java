import java.util.Arrays;

public class HIndex_274 {
    public static void main(String[] args) {
        int[] citations = {1,3,1};
        System.out.println(hIndex(citations));
    }

    /**
     * Method 1: 二分, O(nlogn)
     * */
    public static int hIndex(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);

        int left = 0, right = Math.min(citations[n-1],n);
        while(left < right) {
            int mid = left + right + 1>> 1;
            if(check(citations, mid)) left = mid;
            else right = mid-1;
        }

        return left;
    }

    private static boolean check(int[] nums, int h) {
        int cnt = 0;
        for(int c: nums) {
            if(c >= h) cnt+=1;
            if(cnt >= h) return true;
        }
        return false;
    }

    /**
     * Method 2: 优化
     * */
    public static int hIndex_2(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);
        int left = 0, right = Math.min(citations[n-1], n);
        while(left < right) {
            int mid = left + right + 1>> 1;
            if(citations[n-mid] >= mid) left = mid;
            else right = mid - 1;
        }
        return left;
    }
}
