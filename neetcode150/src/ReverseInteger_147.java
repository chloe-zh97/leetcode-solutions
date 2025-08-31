public class ReverseInteger_147 {

    public static void main(String[] args) {
        int x = 1234236467;
        System.out.println(reverse(x));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }


    public static int reverse(int x) {
        if(x == 0) return 0;
        String num = String.valueOf(x);
        boolean positive = x >= 0;

        StringBuilder res = new StringBuilder(num);
        // 把符号去掉
        if(!positive) res.deleteCharAt(0);

        res.reverse();
        long tmp = Long.parseLong(res.toString());
        if(positive && tmp > Integer.MAX_VALUE || !positive && tmp-1 > Integer.MAX_VALUE)
            return 0;

        int i = 0;
        while(i < res.length() && res.charAt(i) == '0') i++;

        String r = res.substring(i, res.length());

        if(!positive) r = "-"+r;

        return Integer.parseInt(r);

    }

    /**
     * Method 2:trick, 留下最后一位特殊处理
     * */
    public static int reverse_2(int x) {
        int MIN = Integer.MIN_VALUE;
        int MAX = Integer.MAX_VALUE;
        int sum = 0;
        while(x != 0) {
            int d = x % 10;
            x /= 10;

            // 在溢出之前返回
            if((sum > MAX/10 || sum == MAX/10 && d > 7)
            || (sum < MIN/10 || sum == MIN/10 && d < 8)) return 0;

            // 直接加可能会溢出
            sum = sum * 10 + d;
        }
        return sum;
    }


}
