import java.util.HashMap;

public class CountNumberofNiceSubarrays_1248 {
    public static void main(String[] args) {
        int[] nums = {2,4,6};
        int k = 1;
        System.out.println(numberOfSubarrays_2(nums, k));
    }

    /**
     * Method 1: 前缀和+HashMap
     * */
    public static int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        // odd number
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int cnt = 0, ans = 0;
        for(int i=0;i<n;i++) {
            cnt += nums[i] % 2;
            int c = map.getOrDefault(cnt - k, 0);
            ans += c;
            map.merge(cnt, 1, Integer::sum);
        }
        return ans;
    }

    /**
     * Method 2: 滑动窗口
     * */
    public static int numberOfSubarrays_2(int[] nums, int k) {
        return mostKOddNumber(nums, k) - mostKOddNumber(nums, k-1);
    }

    /**
     * <= k 个 odd 的子串数
     * */
    private static int mostKOddNumber(int[] nums, int k) {
        int n = nums.length;
        int cnt = 0;
        int j = 0, i = 0, ans = 0;
        while(j < n) {
            cnt += nums[j]%2;

            while(cnt > k) {
                cnt -= nums[i]%2;
                i++;
            }

            // 退出循环后，cnt <= k, [i,j] 这个位置 cnt <= k
            // i 可取值 i，i+1, ..., j
            ans += j-i+1;
            j++;
        }

        return ans;
    }
}
