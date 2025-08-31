public class IsSubsequence_392 {
    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }

    public static boolean isSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        if(m > n) return false;
        if(m == 0) return true;

        char[] ss = s.toCharArray(), tt = t.toCharArray();

        int j = 0;
        for(int i=0;i<n;i++) {
            if(tt[i] != ss[j]) continue;

            // tt[i] 和 ss[j] 相等
            j++;
            if(j == m) return true;
        }
        return false;
    }
}
