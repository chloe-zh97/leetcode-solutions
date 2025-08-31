import java.util.Arrays;

public class LongestConsecutiveSequence_128 {
    public static void main(String[] args) {
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutive(nums));
    }
    // 0,1,1,2
    public static int longestConsecutive(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        int i = 0;
        while(i < n) {
            int start = i;
            i++;
            while(i<n && nums[i] <= nums[i-1]+1) i++;
           // System.out.println("start="+start+" i="+i);
            // 退出循环时，nums[i] != nums[i-1]+1, i 不满足条件
            ans = Math.max(ans, nums[i-1]-nums[start]+1);
        }
        return ans;
    }
}
