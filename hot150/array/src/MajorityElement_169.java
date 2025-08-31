import java.util.Arrays;

public class MajorityElement_169 {
    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement_2(nums));
    }
    /**
     * Method 1: 排序 O(nlogn)
     * */
    public static int majorityElement(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n/2];
    }

    /**
     * Method 2: 摩尔投票
     * 众数 vote+1，非众数 vote-1
     * */
    public static int majorityElement_2(int[] nums) {
        int n = nums.length;
        int vote = 0, x = nums[0];
        // 3,2,3
        for(int i=0;i<n;i++) {
            if(nums[i] == x) vote += 1;
            else vote -= 1;

            if(vote == 0) {
                x = nums[i];
                vote = 1;
            }
        }
        return x;
    }

}
