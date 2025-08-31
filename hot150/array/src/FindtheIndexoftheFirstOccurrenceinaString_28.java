public class FindtheIndexoftheFirstOccurrenceinaString_28 {
    public static void main(String[] args) {
        String haystack = "a";
        String needle = "a";
        System.out.println(strStr_2(haystack, needle));
    }

    /**
     * Method 1: 库函数
     * */
    public static int strStr(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        if(n > m) return -1;
        char[] chs1 = haystack.toCharArray(), chs2 = needle.toCharArray();

        for(int i=0;i<=m-n;i++) {
            if(chs1[i] != chs2[0]) continue;
            if(haystack.substring(i, i+n).equals(needle)) return i;
        }
        return -1;
    }

    /**
     * Method 2: 双指针
     * */
    public static int strStr_2(String haystack, String needle) {
        char[] chs1 = haystack.toCharArray(), chs2 = needle.toCharArray();
        int m = haystack.length(), n = needle.length();

        for(int i=0;i<=m-n;i++) {
            // 开始匹配 needle
            int a = i, b = 0;
            while(b < n && chs1[a] == chs2[b]) {
                a++;
                b++;
            }
            if(b == n) return i;
        }
        return -1;
    }
}
