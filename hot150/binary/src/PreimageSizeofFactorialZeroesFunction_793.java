public class PreimageSizeofFactorialZeroesFunction_793 {
    public static void main(String[] args) {
        int k = 3;
        System.out.println(preimageSizeFZF(k));
    }

    /**
     * Method 1: 找到一个数 x, x的阶乘末尾的0有k个
     * x 的阶乘组成的因数，可以拆分成多少2和多少5，每一对2和5会产生一个0
     * */
    public static int preimageSizeFZF(int k) {
        if(k <= 1) return 5;
        return func(k) - func(k-1);
    }

    /**
     * 找一个数，它的末尾的0的个数是 x, 找的是右端点
     * */
    private static int func(int x) {
        // 枚举某个数，计算它末尾的0的数量
        long left = 0, right = (long)1e10;

        while(left < right) {
            long mid = left + right + 1>> 1;
            if(countFive(mid) <= x) left = mid;
            else right = mid - 1;
        }
        return (int)left;
    }

    /**
     *
     * */
    private static int countFive(long x) {
        int ans = 0;
        while(x > 0) {
            ans += x/5;
            x /= 5;
        }
        return ans;
    }
}
