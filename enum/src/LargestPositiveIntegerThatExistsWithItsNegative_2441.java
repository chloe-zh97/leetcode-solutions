import java.util.HashSet;

public class LargestPositiveIntegerThatExistsWithItsNegative_2441 {
    public static void main(String[] args) {
        int[] nums = {-10,8,6,7,-2,-3};
        System.out.println(findMaxK(nums));
    }

    public static int findMaxK(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int ans = Integer.MIN_VALUE;

        for(int num: nums) {
            // 包含它的相反数，更新 ans
           if(set.contains(-num)) {
               int v = num >= 0 ? num : -num;
               ans = Math.max(ans, v);
           }
           set.add(num);
        }
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }
}
