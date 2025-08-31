public class SumofTwoIntegers_145 {

    public static void main(String[] args) {
        int a = 4, b = 7;
        System.out.println(getSum(a,b));
    }

    /**
     * 从最低位开始运算，和 + carry
     * 和是 a 和 b 异或的结果，carry 是两数相与然后左移一位
     * */
    public static int getSum(int a, int b) {
        int carry = 0;
        while(b != 0) {
            // 这一步产生的进位会在下一个循环加进来
            carry = (a & b) << 1;
            // 这一步相当于 a+b 但是不带进位
            a = a ^ b;
            b = carry;
        }
        return a;
    }


}
