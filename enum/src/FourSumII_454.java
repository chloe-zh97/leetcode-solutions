import java.util.HashMap;

public class FourSumII_454 {
    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {0};
        int[] nums3 = {0};
        int[] nums4 = {0};
        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
    }

    /**
     * Method 1: nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     * nums1[i] + nums2[j] == - nums3[k] - nums4[l]
     * */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n1 = nums1.length, n2 = nums2.length, n3 = nums3.length, n4 = nums4.length;

        HashMap<Long, Integer> map = new HashMap<>();
        for(int i=0;i<n1;i++) {
            for(int j=0;j<n2;j++) {
                long k = nums1[i] + nums2[j];
                map.merge(k, 1, Integer::sum);
            }
        }

        int ans = 0;
        // 遍历 nums3 和 nums4
        for(int i=0;i<n3;i++) {
            for(int j=0;j<n4;j++) {
                long k = -nums3[i]-nums4[j];
                ans += map.getOrDefault(k, 0);
            }
        }
        return ans;
    }

}
