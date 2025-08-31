public class NumberofOneBits_142 {
    public static void main(String[] args) {

    }

    /**
     * 位运算右移
     * */
    public static int hammingWeight(int n) {
        int cnt = 0;
        while(n > 0) {
            if((n & 1) == 1) cnt++;
            n >>= 1;
        }
        return cnt;
    }
}
