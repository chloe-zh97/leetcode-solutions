public class SingleNumber_136 {
    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(singleNumber(nums));
    }

    public static int singleNumber(int[] nums) {
        int n = nums.length;
        int ans = nums[0];
        for(int i=1;i<n;i++)
            ans ^= nums[i];

        return ans;
    }
}
