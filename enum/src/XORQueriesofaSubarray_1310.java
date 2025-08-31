import java.util.Arrays;

public class XORQueriesofaSubarray_1310 {
    public static void main(String[] args) {
        int[] arr = {16};
        int[][] queries = {
                {0,0},
                {0,0}
        };
        int[] ans = xorQueries(arr, queries);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * Method 1: 前缀和
     * [l,r]的异或结果 = [0,r]的异或结果 ^ [0,l-1]的异或结果
     * 0,1,...,l-1  -> s[l]
     * 0,1,2,..l-1, l, ... r, s[r+1]
     * */
    public static int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int m = queries.length;

        int[] s = new int[n+1];
        for(int i=1;i<s.length;i++)
            s[i] = s[i-1]^arr[i-1];

        int[] ans = new int[m];
        for(int i=0;i<m;i++) {
            int l = queries[i][0], r = queries[i][1];
            ans[i] = s[r+1] ^ s[l];
        }

        return ans;
    }
}
