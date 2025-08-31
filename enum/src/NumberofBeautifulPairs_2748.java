import java.util.HashMap;

public class NumberofBeautifulPairs_2748 {
    public static void main(String[] args) {
        int[] nums = {11,21,12};
        System.out.println(countBeautifulPairs(nums));
    }

    public static int countBeautifulPairs(int[] nums) {
        int ans = 0;
        // 存储头部数字出现的次数
        int[] cnt = new int[10];

        for(int i=0;i<nums.length;i++) {
            int tail = nums[i] % 10;
            for(int j=1;j<10;j++) {
                // 和头部的数字比较
                int c = gcd(j, tail);
                if(c == 1) ans += cnt[j];
            }
            // 更新头部出现次数
            String a = String.valueOf(nums[i]);
            int head = a.charAt(0) - '0';
            cnt[head]++;
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        while(a!=0) {
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }

}
