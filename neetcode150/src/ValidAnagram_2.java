import java.util.Arrays;

public class ValidAnagram_2 {
    public static void main(String[] args) {
       String s = "racecar";
       String t = "carrace";
        System.out.println(isAnagram_2(s,t));
    }

    /**
     * Method 1: 暴力法， sort
     * */
    public static boolean isAnagram(String s, String t) {
        char[] chs1 = s.toCharArray();
        char[] chs2 = t.toCharArray();

        Arrays.sort(chs1);
        Arrays.sort(chs2);

        String s1 = String.valueOf(chs1);
        String s2 = String.valueOf(chs2);
        return s1.equals(s2);
    }

    /**
     * Method 2: map
     * */
    public static boolean isAnagram_2(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        if(n1 != n2) return false;

        int[] cnt = new int[26];
        for(char c: s.toCharArray())
            cnt[c-'a']++;

        for(char c: t.toCharArray()) {
            cnt[c-'a']--;
            if(cnt[c-'a'] < 0) return false;
        }

        for(int c: cnt) {
            if(c != 0) return false;
        }

        return true;
    }
}
