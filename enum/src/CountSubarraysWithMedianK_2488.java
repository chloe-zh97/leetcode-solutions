import java.util.HashMap;

public class CountSubarraysWithMedianK_2488 {
    public static void main(String[] args) {
        int[] nums = {2,3,1};
        int k = 3;
        System.out.println(countSubarrays(nums, k));
    }

    /**
     * Method 1: 前缀和
     * 3,2,1,4,5
     * 1.中位数为k, 小于 k 的数 == 大于 k 的数
     * k左边小于k的数+k右边小于k的数 == k左边大于k的数+k右边大于k的数
     * +k左边小于k的数-k左边大于k的数 == +k右边大于k的数-k右边小于k的数
     *
     * 2. k左边小于k的数(1) k左边大于k的数(-1)
     * k右边大于k的数(1) k右边小于k的数(-1)
     *
     * 3,2,1,4,5 从4往右遍历
     * 1 1 1 0 1
     * 0 1 2 3   左侧小于-左侧大于
     *       0 1 右侧大于-右侧小于
     *
     * 3. 对于子数组长是偶数的情况
     * 比如 3, 1, 2, 4 中位数是2， 1 2 3 4
     * 小于 k 的数 == 大于 k 的数-1
     * 左边小于k的数+右边小于k的数 == 左边大于k的数+右边大于k的数-1
     * 左边小于k的数-左边大于k的数 == 右边大于k的数-右边小于k的数-1
     *
     * 设 x = 右边大于k的数 - 右边小于k的数
     * 在遍历左边的数时，map中需要查找 x和x-1的值
     * */
    public static int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        // 找到 pos 的位置
        int pos = 0;
        while(nums[pos] != k) pos++;

        // 前缀和，times
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        // x 表示前缀和
        int x = 0;
        // 统计左边, 从中间扩散
        for(int i=pos-1;i>=0;i--) {
            x += nums[i] < k ? 1 : -1;
            map.merge(x, 1, Integer::sum);
        }

        int ans = map.get(0) + map.getOrDefault(-1,0);

        x = 0;
        // 枚举右端点，从中间扩散
        for(int i=pos+1;i<n;i++) {
            x += nums[i] > k ? 1 : -1;
            // 枚举每一个右端点，判断有多少左端点符合条件，x 为奇数的情况， x-1 为偶数的情况
            ans += map.getOrDefault(x,0) + map.getOrDefault(x-1, 0);
        }

        return ans;
    }

    // 前缀和的范围 [-(n-1), n+1]
}
