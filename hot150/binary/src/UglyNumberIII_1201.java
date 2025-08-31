public class UglyNumberIII_1201 {
    public static void main(String[] args) {
        int n = 5, a = 2, b=11,c=13;
        System.out.println(nthUglyNumber(n,a,b,c));
    }

    private static long ab = 0L;
    private static long ac = 0L;
    private static long bc = 0L;
    private static long abc = 0L;

    public static int nthUglyNumber(int n, int a, int b, int c) {
        long left = 1, right = (long) Math.min(Math.min(a,b),c) * n;

        // 求最小公倍数
        ab = lcm(a,b);
        ac = lcm(a,c);
        bc = lcm(b,c);
        abc = lcm(ab, c);

        while(left < right) {
            long mid = left + right >> 1;
            if(check(a,b,c,n,mid)) right = mid;
            else left = mid + 1;
        }
        return (int)left;
    }

    /**
     * 比 val 小的或者等于的丑数是否 >= n 个
     * */
    private static boolean check(int a, int b, int c, int n, long val) {
        long _aa = val / a;
        long _bb = val / b;
        long _cc = val / c;
        long _ab = val / ab;
        long _ac = val / ac;
        long _bc = val / bc;
        long _abc = val / abc;

        return _aa+_bb+_cc-_ab-_ac-_bc+_abc >= n;
    }

    private static long gcd(long a, long b) {
        while(a!=0) {
            long temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }

    private static long lcm(long a, long b) {
        if(a*b == 0) return 0L;
        return a*b/gcd(a,b);
    }
}
