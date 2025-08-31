import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LongestConsecutiveSequence_9 {
    public static void main(String[] args) {
        int[] nums = {0,3,2,5,4,6,1,1};
        System.out.println(longestConsecutive_3(nums));
    }

    /**
     * Method 1: Sort O(nlogn)
     * */
    public static int longestConsecutive(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int i = 0, ans = 0;

        while(i < n) {
            int start = i;
            i++;
            while(i<n && (nums[i] == nums[i-1] || nums[i] == nums[i-1]+1)) i++;
            // 退出循环时，i 不满足条件
            ans = Math.max(ans, nums[i-1]-nums[start]+1);
        }
        return ans;
    }

    /**
     * Method 2: HashSet, 遍历两次
     * 第一次将所有的元素统计进入 set
     * 只考虑每个可能的 start 位置
     * */
    public static int longestConsecutive_2(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        for(int num: nums) seen.add(num);

        int ans = 0;
        for(int num: nums) {
            if(!seen.contains(num-1)) {
                // 可能作为 start 位置
                int offset = 1;
                while(seen.contains(num+offset)) {
                    offset += 1;
                }
                // num, num+1, num+2, ..., num+offset-1
                // 没有 num + offset 这个元素
                ans = Math.max(ans, offset);
            }
        }
        return ans;
    }


    /**
     * Method 3: HashMap
     * val, len
     * 以 val 为结尾，最长的连续子串
     * 1,2,x,4,5
     * 需要更新的 key 值为 x, x - map.get(x-1), x+map.get(x+1)
     * */
    public static int longestConsecutive_3(int[] nums) {
        int n = nums.length, ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            int num = nums[i];
            if(!map.containsKey(num)) {
                map.put(num, map.getOrDefault(num-1, 0) +
                        map.getOrDefault(num+1, 0) + 1);

                map.put(num-map.getOrDefault(num-1, 0), map.get(num));
                map.put(num+map.getOrDefault(num+1, 0), map.get(num));
                ans = Math.max(ans, map.get(num));
            }
        }
        return ans;

    }
}
