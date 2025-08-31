public class MaximumSumofTwoNonOverlappingSubarrays_1031 {
    public static void main(String[] args) {
        int[] nums = {2,1,5,6,0,9,5,0,3,8};
        int firstLen = 4, secondLen = 3;
        System.out.println(maxSumTwoNoOverlap(nums, firstLen, secondLen));
    }

    public static int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        // nums 的前缀和
        int[] s = new int[n+1];
        for(int i=0;i<n;i++) {
            // s[i]: nums[0...i-1]的和
            s[i+1] = s[i]+nums[i];
        }
        return Math.max(func(s, firstLen, secondLen), func(s,secondLen,firstLen));
    }

    private static int func(int[] s, int firstLen, int secondLen) {
        // 枚举第二段的起点
        // s[i]: nums[0...i-1]的和
        int maxA = 0;
        int res = 0;
        for(int i=firstLen+secondLen;i<s.length;i++) {
            // firstLen = 2, secondLen = 3, i=5
            // s[2]-s[0]: nums[0,1]
            maxA = Math.max(maxA, s[i-secondLen]-s[i-secondLen-firstLen]);
            // i=5,i-secondLen = 2
            // s[5]-s[2] = 4,3,2
            res = Math.max(res, maxA + s[i]-s[i-secondLen]);
        }
        return res;
    }
}
