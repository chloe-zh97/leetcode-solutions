public class MultiplyStrings_139 {
    public static void main(String[] args) {
        String num1 = "111";
        String num2 = "222";
        String res = multiply_2(num1, num2);
        System.out.println(res);
    }

    /**
     * Method 1: 模拟乘法 O(m*n)
     * 单个抽取 num2 的每个数字去和 num1 相乘，结果累加在 res 里
     * */
    public static String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();

        if("0".equals(num1) || "0".equals(num2)) return "0";

        if(m < n) return multiply(num2, num1);

        String res = "";
        int zeros = 0;
        // O(m)
        for(int i=n-1;i>=0;i--) {
            int digit = num2.charAt(i) - '0';

            // O(n)
            String tmp = mul(num1, digit, zeros);
            // 累加
            res = add(tmp, res);
            zeros++;
        }
        return res;
    }

    private static String mul(String num1, int digit, int zeros) {
        int n = num1.length();

        // 存储乘法结果, 逆序
        StringBuilder sb = new StringBuilder();

        int carry = 0, i = n-1;
        // O(n)
        while(i >= 0 || carry != 0) {
            // 注意i是否越界
            int d = i > -1 ? num1.charAt(i) - '0' : 0;
            int product = digit * d + carry;
            sb.append(product % 10);
            carry = product / 10;
            i--;
        }

        return sb.reverse() + "0".repeat(zeros);
    }

    private static String add(String num1, String num2) {
        StringBuilder sb = new StringBuilder();

        int i = num1.length()-1, j = num2.length()-1;
        int carry = 0;

        while(i >= 0 || j >= 0 || carry != 0) {
            int n1 = i > -1 ? num1.charAt(i) - '0' : 0;
            int n2 = j > -1 ? num2.charAt(j) - '0' : 0;

            int tmp = n1 + n2 + carry;

            sb.append(tmp % 10);
            carry = tmp / 10;

            i--;
            j--;
        }
        return sb.reverse().toString();
    }

    /**
     * Method 2: 分配一个长度为 len1+len2 的数组
     * 从低位到高位计算，外层为更短的那个数，内层为更长的那个数
     * product = d1 * d2 + res[i+j+1]
     * */
    public static String multiply_2(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)) return "0";

        if(num1.length() < num2.length()) return multiply_2(num2, num1);
        // reverse

        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();

        int m = num1.length(), n = num2.length();

        int[] res = new int[m+n];

        // 外层是 num2
        for(int i2=0;i2<n;i2++) {
            for(int i1=0;i1<m;i1++) {
                int d1 = num1.charAt(i1) - '0';
                int d2 = num2.charAt(i2) - '0';

                // 这里已经带上了原来的进位
                res[i1+i2] += d1 * d2;
                res[i1+i2+1] += res[i1+i2] / 10;
                res[i1+i2] = res[i1+i2] % 10;
            }
        }

        // res 倒过来，先去掉前导0
        int end = res.length-1;
        while(end >= 0 && res[end] == 0) end--;
        // 退出循环时 res[end] != 0

        StringBuilder ans = new StringBuilder();
        for(int i=end;i>=0;i--) {
            ans.append(res[i]);
        }
        return ans.toString();
    }
}
