import java.util.PriorityQueue;

public class MinimumNumberofKConsecutiveBitFlips_995 {
    public static void main(String[] args) {

    }

    /**
     * Method 1: 差分数组，d[i]: [i,n) 翻转的次数
     * 经过偶数次翻转，0还是0，1还是1
     * 经过奇数次翻转，0变成1，1变成0
     *
     * */
    public static int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        // construct diff array
        int[] d = new int[n+1];
        int s = 0, ans = 0;
        for(int i=0;i<n;i++) {
            s += d[i];

            if((nums[i]+s)%2 == 0) {
                if(i+k>n) return -1;
                // 翻转一次
                d[i]++;
                d[i+k]--;
                s += 1;
                ans++;
            }
        }
        return ans;
    }
}
