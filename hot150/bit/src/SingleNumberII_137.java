public class SingleNumberII_137 {
    public static void main(String[] args) {
        int[] nums = {0,1,0,1,0,1,99};
        System.out.println(singleNumber(nums));
    }

    /**
     * Method 1: x 在某位上的数为和 % 3 剩下的值
     * */
    public static int singleNumber(int[] nums) {
        int ans = 0;
        for(int i=0;i<32;i++) {
            // 统计每一个位数1的个数
            int cnt = 0;
            for(int num:nums) {
                cnt += (num >> i) & 1;
            }
            ans |= ((cnt % 3) << i);
        }
        return ans;
    }
}
