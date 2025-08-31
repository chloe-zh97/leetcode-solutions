import java.util.HashMap;

public class ContiguousArray_525 {
    public static void main(String[] args) {
        int[] nums = {0,1,0};
        System.out.println(findMaxLength(nums));
    }

    /**
     * Method 1: 前缀和+hashmap
     * nums 中，将 0 变成 -1
     * 找到前缀和只差为0的最长子串
     * */
    public static int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] s = new int[n+1];
        for(int i=1;i<s.length;i++)
            s[i] = s[i-1] + (nums[i-1] == 0 ? -1 : 1);

        // preSum, index, 维护最小的 preSum 的 index
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int ans = 0;
        for(int i=1;i<s.length;i++) {
            int preSum = s[i];
            if(map.containsKey(preSum)) {
                int preId = map.get(preSum);
                ans = Math.max(ans, i-preId);
            } else {
                map.put(preSum, i);
            }
        }
        return ans;
    }
}
