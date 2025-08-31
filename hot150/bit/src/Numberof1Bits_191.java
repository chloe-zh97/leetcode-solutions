public class Numberof1Bits_191 {
    public static void main(String[] args) {
        int n = 2147483645;
        System.out.println(hammingWeight(n));
    }
    public static int hammingWeight(int n) {
        int cnt = 0;
        while(n > 0) {
            cnt += n&1;
            n >>>= 1;
        }
        return cnt;
    }

}
