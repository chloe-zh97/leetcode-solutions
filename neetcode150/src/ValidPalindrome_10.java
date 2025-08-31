public class ValidPalindrome_10 {
    public static void main(String[] args) {
        String s = "Was it a car or a cat I saw?";
        System.out.println(isPalindrome(s));
        System.out.println(isPalindrome_2(s));
    }

    /**
     * Method 1: 双指针 O(n) / O(1)
     * */
    public static boolean isPalindrome(String s) {
        s = s.replaceAll("\\s+", "");
        int n = s.length();
        char[] chs = s.toLowerCase().toCharArray();

        int i = 0, j = n-1;
        while(i < j) {
            while(i < n && !Character.isLetterOrDigit(chs[i])) i++;
            while(j >= 0 && !Character.isLetterOrDigit(chs[j])) j--;
            // 退出循环时，chs[i]，chs[j] 指向字母或者数字
            if(i < j && chs[i] != chs[j]) return false;
            i++;
            j--;
        }
        return true;
    }

    /**
     * Method 2: Stack
     * */
    public static boolean isPalindrome_2(String s) {
        s = s.replaceAll("\\s+", "").toLowerCase();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for(char c: s.toCharArray()) {
            if(Character.isLetterOrDigit(c)) {
                sb.append(c);
                sb2.append(c);
            }
        }


        return sb.reverse().toString().contentEquals(sb2);
    }

}
