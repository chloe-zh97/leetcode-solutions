import java.util.ArrayList;
import java.util.List;

public class SummaryRanges_228 {
    public static void main(String[] args) {
        int[] nums = {0,2,3,4,6,8,9};
        List<String> ans = summaryRanges(nums);
        System.out.println(ans);
    }

    /**
     * Method 1: Sliding window
     * */
    public static List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        List<String> ans = new ArrayList<>();
        String s = "";
        int i =0;
        while(i < n) {
            int start = i;
            i++;

            while(i < n && nums[i] == nums[i-1]+1) i++;
            // 退出循环时 nums[i] != nums[i-1]+1

            if(start == i-1) {
                s = ""+nums[start];
            } else {
                s = nums[start] + "->" + (nums[i-1]);
            }
            ans.add(s);

        }
        return ans;
    }
}
