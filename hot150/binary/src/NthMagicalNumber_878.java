public class NthMagicalNumber_878 {
    public static void main(String[] args) {
        int n = 1000000000, a = 4000, b = 4000;

        System.out.println(nthMagicalNumber(n,a,b));
    }

    /**
     * Method 1: 二分+数学
     * 求第n个魔法数是多少，二分一个具体的 x, 有多少魔法数 <= x
     * 注意溢出
     * */
    public static int nthMagicalNumber(int n, int a, int b) {
        int mod = (int)(1e9)+7;
        long left = 1, right = (long) n * Math.min(a,b);
        while(left < right) {
            long mid = left + right >> 1;
            if(check(a,b,mid,n)) right = mid;
            else left = mid + 1;
        }
        return (int)(left % mod);
    }

    /**
     * <= val 的魔法数是否有 n 个
     * */
    private static boolean check(int a, int b, long val, int n) {
        long c1 = val / a;
        long c2 = val / b;
        // duplicate
        long c3 = val / lcm(a,b);

        return c1 + c2 - c3 >= n;
    }

    /**
     * 求最小公因数
     * */
    private static int lcm(int a, int b) {
        if(a*b == 0) return 0;
        return a * b / gcd(a,b);
    }

    /**
     * 求最大公倍数
     * */
    private static int gcd(int a, int b) {
        while(a!=0) {
            int temp = a;
            a = b%a;
            b = temp;
        }
        return b;
    }
}
