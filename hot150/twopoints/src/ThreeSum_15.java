import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_15 {
    public static void main(String[] args) {
        int[] nums = {-1,-1,-1,-1,2};
        List<List<Integer>> ans = threeSum(nums);
        for(List<Integer> row: ans)
            System.out.println(row);
    }

    /**
     * Method 1: 双指针
     * */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> row;

        int n = nums.length;
        Arrays.sort(nums);

        for(int k=0;k<n-2;k++) {
            // 去重
            if(k>0 && nums[k] == nums[k-1]) continue;

            // 优化
            if(nums[k] + nums[k+1] + nums[k+2] > 0) break;
            if(nums[k] + nums[n-1] + nums[n-2] < 0) continue;

            // 双指针
            int i=k+1, j=n-1;

            while(i < j) {
                if(i!=k+1 && nums[i] == nums[i-1]) {
                    i++;
                    continue;
                }
                if(nums[i] + nums[j] + nums[k] == 0) {
                    // 找到一组符合条件
                    row = new ArrayList<>();
                    row.add(nums[k]);
                    row.add(nums[i]);
                    row.add(nums[j]);
                    ans.add(row);
                    i++;
                    j--;
                } else if(nums[i]+nums[j]+nums[k] < 0) {
                    i++;
                } else {
                    j--;
                }
            }
        }

        return ans;
    }
}
