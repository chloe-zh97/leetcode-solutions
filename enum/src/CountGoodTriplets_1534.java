import java.util.Arrays;

public class CountGoodTriplets_1534 {
    public static void main(String[] args) {
        int[] nums = {3,0,1,1,9,7};
        int a = 7, b = 2, c = 3;
        System.out.println(countGoodTriplets_2(nums, a, b, c));
    }

    /**
     * Method 1: 暴力法
     * */
    public static int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length;
        int ans = 0;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                for(int k=j+1;k<n;k++) {
                    if(Math.abs(arr[i]-arr[j]) <= a && Math.abs(arr[j]-arr[k]) <= b &&
                    Math.abs(arr[i]-arr[k]) <= c) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * Method 2: 枚举+前缀和
     * 枚举 j 和 k, 计算 i
     * -a <= arr[i] - arr[j] <= a
     * -c <= arr[i]-arr[k] <= c
     *
     * arr[j]-a <= arr[i] <= a+arr[j]
     * arr[k]-c <= arr[i] <= c+arr[k]
     * 0 <= arr[i] <= a
     * */
    public static int countGoodTriplets_2(int[] arr, int a, int b, int c) {
        int n = arr.length;
        // 枚举 j 和 k, 计算有多少 arr[i]符合条件
        int mx = 0;
        for(int x: arr)
            mx = Math.max(x, mx);

        // 计算前缀和
        int[] cnt = new int[mx+2];
        for(int ar: arr)
            cnt[ar]++;

        // s[i]: <= i的数有多少个
        int[] s = new int[mx+2];
        s[0] = cnt[0];
        for(int i=1;i<s.length;i++)
            s[i] = s[i-1]+cnt[i];

        System.out.println("print s");
        System.out.println(Arrays.toString(s));

        int ans = 0;
        for(int j=1;j<n-1;j++) {
            for(int k=j+1;k<n;k++) {
                if(Math.abs(arr[j]-arr[k]) > b) continue;

                int lb = Math.max(Math.max(arr[j]-a, arr[k]-c),0);
                int rb = Math.min(Math.min(a+arr[j], c+arr[k]), mx);
                if(lb > rb) continue;
                // 计算 [lb, rb] 有多少个 arr[i]
                ans += Math.max(0, s[rb]-s[lb]+cnt[lb]);
            }
        }
        return ans;
    }
}
