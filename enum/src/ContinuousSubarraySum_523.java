import java.util.HashMap;
import java.util.HashSet;

public class ContinuousSubarraySum_523 {
    public static void main(String[] args) {
        int[] nums = {0,0};
        int k = 1;
        System.out.println(checkSubarraySum_2(nums, k));
    }

    /**
     * Method 1: 前缀和+HashMap
     * sum - pre 是6的倍数，6，12，18，能被6整除即可
     *
     * (a - b) % 6 == 0 等价于 a % 6 == b % 6
     *
     * 从 2 开始遍历
     * */
    public static boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        // 记录出现过的数
        HashSet<Integer> set = new HashSet<>();

        // 前缀和
        int[] s = new int[n+1];
        for(int i=1;i<s.length;i++)
            s[i] = s[i-1] + nums[i-1];

        for(int i=2;i<s.length;i++) {
            set.add(s[i-2] % k);
            if(set.contains(s[i] % k)) return true;
        }
        return false;
    }

    /**
     * Method 2: 优化，增加哨兵
     * */
    public static boolean checkSubarraySum_2(int[] nums, int k) {
        int n = nums.length;
        // mod, index
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);
        long pre = 0L;

        for(int i=0;i<n;i++) {
            pre += nums[i];
            if(map.containsKey((int)(pre % k))) {
                int id = map.get((int)(pre % k));
                if(i-id>=2) return true;
            } else {
                map.put((int) (pre % k), i);
            }
        }
        return false;
    }

    public static boolean checkSubarraySum_3(int[] nums, int k) {
        int m = nums.length;
        // key 为前缀和与 k 的余数， value 为 index
        HashMap<Integer, Integer> map = new HashMap<>(m, 1.05f);
        int rem = 0;
        map.put(0, -1);
        for (int i = 0; i < m; i++) {
            rem = (rem + nums[i]) % k;
            if (map.containsKey(rem)) {
                if (i - map.get(rem) >= 2) {
                    return true;
                }
            } else {
                map.put(rem, i);
            }
        }
        return false;
    }

}
