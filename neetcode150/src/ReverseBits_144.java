public class ReverseBits_144 {
    public static void main(String[] args) {
        int n = 21;
        System.out.println(reverseBits(n));

    }

    /**
     * 0000 1011
     * 1101 0000
     *
     * 按位逐个取出最低位，然后左移到具体位置
     * */
    public static int reverseBits(int n) {
        int ans = 0;
        for(int i=0;i<32;i++) {
            int d = n & 1;
            n >>= 1;
            ans |= d << (31-i);
        }
        return ans;
    }
}
