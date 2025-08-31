import java.util.HashMap;

public class CountNicePairsinanArray_1814 {
    public static void main(String[] args) {
        int[] nums = {13,10,35,24,76};
        //System.out.println(countNicePairs(nums));
        System.out.println(rev(0));
    }

    /**
     * nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
     * */
    public static int countNicePairs(int[] nums) {
        // a[i] = nums[i]-rev(nums[i])
        long ans = 0;
        int mod = (int)1e9+7;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            int v = nums[i] - rev(nums[i]);
            int c = map.getOrDefault(v, 0);
            ans += c;
            map.merge(v, 1, Integer::sum);
        }
       return (int)(ans % mod);
    }

    /**
     * 翻转数字
     * */
    private static int rev(int num) {
        if(num == 0) return 0;
        String s = String.valueOf(num).replaceAll("0+$", "");
        StringBuilder sb = new StringBuilder(s);
        String r = sb.reverse().toString();
        return Integer.parseInt(r);
    }

    /**
     * 优化reverse
     * */
    private static int rev_2(int num) {
        int res = 0;
        while(num > 0) {
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res;
    }
}
