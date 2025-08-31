import java.util.Arrays;

public class AddBinary_67 {
    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";
        System.out.println(addBinary_2(a,b));
    }

    public static String addBinary(String a, String b) {
        if(a.length() < b.length()) return addBinary(b, a);
        // a.length >= b.length
        char[] aa = a.toCharArray(), bb = b.toCharArray();
        int m = a.length(), n = b.length();
        char[] cc = new char[m+1];
        Arrays.fill(cc,'0');

        int i = m-1, j = n-1, k = cc.length-1;
        int carry = 0;

        while(i >= 0 && j >= 0) {
            int v = (aa[i]-'0' + bb[j]+'0' + carry) % 2;
            carry = aa[i]-'0' + bb[j]-'0' + carry > 1 ? 1: 0;
            cc[k--] = v == 0 ? '0' : '1';
            i--;
            j--;
        }

        while(i >= 0) {
            int v = (aa[i]-'0'+carry) % 2;
            carry = aa[i] - '0' + carry > 1 ? 1: 0;
            cc[k--] = v == 0 ? '0' : '1';
            i--;
        }

        if(carry == 1) cc[0] = '1';
        String ans = String.valueOf(cc);
        return ans.charAt(0) == '0' ? ans.substring(1) : ans;
    }

    /**
     * Method 2: 优化
     * */
    public static String addBinary_2(String a, String b) {
        int m = a.length(), n = b.length();
        StringBuilder sb = new StringBuilder();

        int carry = 0;
        for(int i=m-1,j=n-1;i>=0 || j >= 0;--i,--j) {
            carry += i >= 0 ? a.charAt(i) - '0' : 0;
            carry += j >= 0 ? b.charAt(j) - '0' : 0;

            sb.append(carry % 2);
            carry /= 2;
        }

        if(carry == 1) sb.append("1");
        return sb.reverse().toString();
    }
}
