import java.util.Arrays;
import java.util.HashMap;

public class FindLongestSubarrayLCCI_17_05 {
    public static void main(String[] args) {
        String[] array = {"A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"};
        array = findLongestSubarray(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * Method 1: 字母和数字的数量差
     * */
    public static String[] findLongestSubarray(String[] array) {
        int n = array.length;
        int[] s = new int[n+1];
        for(int i=1;i<s.length;i++)
            s[i] = s[i-1] + (isNumber(array[i-1]) ? 1:-1);

        // 前缀和为0的最长子串, preSum, index, 存储最小 index
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        int l = 0, r = 0;
        for(int i=0;i<s.length;i++) {
            int preSum = s[i];
            if(map.containsKey(preSum)) {
                int preId = map.get(preSum);
                if(i-preId > r - l) {
                    r = i;
                    l = preId;
                }
            } else {
                map.put(preSum, i);
            }
        }

        // preSum[r] - preSum[l]:
        // preSum[r] = nums[0] + nums[1] + ...+ nums[l] + nums[r-1]
        // preSum[l] = nums[0] + nums[1] + ... + nums[l-1]
        // nums[l] + nums[l+1] + ... + nums[r-1]
        String[] ans = new String[r-l];
        System.arraycopy(array, l, ans, 0, ans.length);
        return ans;
    }

    private static boolean isNumber(String s) {
        return s.charAt(0) >= '0' && s.charAt(0) <= '9';
    }
}
