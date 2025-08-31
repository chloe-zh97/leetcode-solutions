public class ShortestSubarraywithSumatLeastK_862 {
    public static void main(String[] args) {
        int[] nums = {84,-37,32,40,95};
        int k = 167;
        System.out.println(shortestSubarray(nums, k));
    }

    /**
     * nums 的最短子序列，保证 sum >= k
     * */
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;

        int ans = Integer.MAX_VALUE;
        int i=0,j=0;
        while(j < n) {
            // element in
            sum += nums[j];

            while(sum >= k) {
                System.out.println("i="+i+" j="+j+" sum="+sum);
                ans = Math.min(ans, j-i+1);
                sum -= nums[i++];
            }


            j++;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
