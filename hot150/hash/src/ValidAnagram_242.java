public class ValidAnagram_242 {
    public static void main(String[] args) {
        String s = "rat";
        String t = "car";
        System.out.println(isAnagram(s, t));

    }
    public static boolean isAnagram(String s, String t) {
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        int m = ss.length, n = tt.length;
        if(m != n) return false;

        int[] cnt = new int[128];
        for(char c: tt)
            cnt[c]++;

        for(char c: ss) {
            cnt[c]--;
            if(cnt[c] < 0) return false;
        }
        return true;
    }
}
