public class FactorialTrailingZeroes_172 {
    public static void main(String[] args) {
        int n = 0;
        System.out.println(trailingZeroes(n));
    }

    /**
     * 阶乘的尾数0的个数，取决于能分解出多少个10，分解出2的个数一定多于5，
     * 所以只要知道分解出5的个数即可。
     * */
    public static int trailingZeroes(int n) {
        int cnt = 0;
        while(n > 0) {
            // n 能分解出多少个5
            cnt += n/5;
            n /= 5;
        }
        return cnt;
    }
}
