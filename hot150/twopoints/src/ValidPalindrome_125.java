public class ValidPalindrome_125 {
    public static void main(String[] args) {
        String s = "0P";
        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s) {
        int n = s.length();
        char[] chs = s.toLowerCase().toCharArray();
        int i = 0, j = n-1;
        while(i < j) {
            if(!Character.isLetterOrDigit(chs[i])) {
                i++;
                continue;
            }

            if(!Character.isLetterOrDigit(chs[j])) {
                j--;
                continue;
            }

            // i 和 j 位置都指向字母
            if(chs[i] != chs[j]) return false;

            // 指向相同字母
            i++;
            j--;
        }
        return true;
    }
}
