import java.util.HashMap;
import java.util.Map;

public class PowXn_139 {
    public static void main(String[] args) {
        double x = 2.00000;
        int n = -2147483648;
        System.out.println(myPow_2(x, n));
    }

    public static double myPow(double x, int n) {
        double res = helper(x, n);
        return n < 0 ? 1.0/res : res;
    }

    private static double helper(double x, int n) {
        if(x == 0) return 0;
        if(n == 0) return 1;
        // 取正数
        long power = Math.abs((long)n);

        double res = helper(x, (int)(power/2));
        res *= res;

        return n % 2 == 0 ? res : res * x;
    }

    /**
     * Method 2: 位运算
     * 15 = 1111
     * x^8 * x^4 * x^2 * x^1
     * 把 n 的二进制位从右到左取出
     * */
    public static double myPow_2(double x, int n) {
        if(x == 0) return 0;
        if(n == 0) return 1;

        long power = Math.abs((long)n);

        double res = 1;
        while(power > 0) {
            if((power & 1) == 1) res *= x;
            x *= x;
            power >>= 1;
        }
        return n < 0 ? 1.0 / res : res;
    }



}
