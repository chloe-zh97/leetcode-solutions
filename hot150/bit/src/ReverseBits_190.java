public class ReverseBits_190 {
    public static void main(String[] args) {
        int n = 43261596;
        System.out.println(reverseBits(n));
    }

    /**
     * Method 1: 对称位构造
     * */
    public static int reverseBits(int n) {
        int ans = 0;
        for(int i=0;i<32;i++) {
            int t = (n >> i) & 1;
            if(t == 1)
                ans |= 1 << (31-i);
        }
        return ans;
    }
}
