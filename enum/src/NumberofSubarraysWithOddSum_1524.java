import java.util.HashMap;

public class NumberofSubarraysWithOddSum_1524 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        System.out.println(numOfSubarrays_3(arr));
    }

    /**
     * Method 1: HashMap + 2次遍历
     * */
    public static int numOfSubarrays(int[] arr) {
        final int mod = (int)1e9+7;
        int n = arr.length;
        int[] s = new int[n+1];
        for(int i=1;i<s.length;i++)
            s[i] = s[i-1] + arr[i-1];

        // mod, times
        HashMap<Integer, Integer> map = new HashMap<>();

        long ans = 0;
        for(int i=0;i<s.length;i++) {
            int c = 0;
            if(s[i]%2==0) {
                c = map.getOrDefault(1, 0);
            } else {
                c = map.getOrDefault(0, 0);
            }
            ans += c;
            map.merge(s[i]%2, 1, Integer::sum);
        }

        return (int)(ans % mod);
    }


    /**
     * Method 2: 一次遍历+HashMap
     * */
    public static int numOfSubarrays_2(int[] arr) {
        int n = arr.length;
        final int mod = (int)1e9+7;

        // mod, times
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        int pre = 0;
        long ans = 0L;
        for(int i=0;i<n;i++) {
            pre += arr[i];

            int c = map.getOrDefault(1-pre%2, 0);

            ans += c;

            map.merge(pre%2, 1, Integer::sum);
        }
        return (int)(ans % mod);
    }

    /**
     * Method 3: 用 cnt 替换 HashMap
     * */
    public static int numOfSubarrays_3(int[] arr) {
        int n = arr.length;
        final int mod = (int)1e9+7;

        int[] mm = new int[2];
        mm[0] = 1;

        int pre = 0;
        long ans = 0L;
        for(int i=0;i<n;i++) {
            pre += arr[i];
            ans += mm[1-pre%2];
            mm[pre%2] += 1;
        }
        return (int)(ans % mod);
    }


}
