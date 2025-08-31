public class RansomNote_338 {
    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "aab";
        System.out.println(canConstruct(ransomNote, magazine));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        char[] rr = ransomNote.toCharArray(), mm = magazine.toCharArray();
        int m = ransomNote.length(), n = magazine.length();

        // 用 char[] 存储 magazine 中出现的字符频数
        int[] cnt = new int[128];
        for(char c: mm)
            cnt[c]++;

        for(char c: rr) {
            cnt[c]--;
            if(cnt[c] < 0) return false;
        }
        return true;
    }
}
