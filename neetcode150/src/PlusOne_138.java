import java.util.Arrays;

public class PlusOne_138 {
    public static void main(String[] args) {
        int[] digits = {9,9,9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }

    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        int[] ans = new int[n+1];
        // 最高位先空着
        for(int i=0;i<n;i++) {
            ans[i+1] = digits[i];
        }

        int carry = 1;
        for(int i=n;i>=0;i--) {
            int tmp = ans[i] + carry;
            carry = tmp / 10;
            ans[i] = tmp % 10;
        }

        if(ans[0] == 0) {
            int[] res = new int[n];
            res = Arrays.copyOfRange(ans, 1, ans.length);
            return res;
        }
        return ans;
    }

    /**
     * Method 2: 优化
     * digit[i] 不是 9 就+1， return
     * digit[i] 是9就设置成0
     * */
    public static int[] plusOne_2(int[] digits) {
        int n = digits.length;
        for(int i=n-1;i>=0;i--) {
            if(digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }

        digits = new int[n+1];
        digits[0] = 1;
        return digits;
    }
}
