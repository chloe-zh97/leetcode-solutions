import java.util.HashMap;

public class IsomorphicStrings_205 {
    public static void main(String[] args) {
        String s = "badc";
        String t = "baba";
        System.out.println(isIsomorphic_2(s,t));
    }
    public static boolean isIsomorphic(String s, String t) {
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        int m = s.length(), n = t.length();
        if(m != n) return false;

        HashMap<Character, Character> smap = new HashMap<>();
        HashMap<Character, Character> tmap = new HashMap<>();

        for(int i=0;i<m;i++) {
            char c1 = ss[i], c2 = tt[i];
            if(smap.containsKey(c1) && smap.get(c1) != c2) return false;
            else if(tmap.containsKey(c2) && tmap.get(c2) != c1) return false;
            else {
                smap.put(c1, c2);
                tmap.put(c2, c1);
            }
        }
        return true;
     }

     /**
      * Method 2: 优化，两次调用函数
      * */
    public static boolean isIsomorphic_2(String s, String t) {
        if(s.length() != t.length()) return false;
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        return isIsomorphic(ss, tt) && isIsomorphic(tt, ss);
    }

    private static boolean isIsomorphic(char[] ss, char[] tt) {
        HashMap<Character, Character> map = new HashMap<>();
        for(int i=0;i<ss.length;i++) {
            char c1 = ss[i], c2 = tt[i];
            if(map.containsKey(c1) && map.get(c1) != c2) return false;
            map.put(c1, c2);
        }
        return true;
    }

    /**
     * Method 3: 给字符编号
     * */
    public static boolean isIsomorphic_3(String s, String t) {
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        return constructString(ss,tt).equals(constructString(tt,ss));
    }

    private static String constructString(char[] ss, char[] tt) {
        int m = ss.length;
        StringBuilder sb = new StringBuilder();
        int[] code = new int[128];

        for(int i=0;i<m;i++) {
            char c = ss[i];
            if(code[c] == 0) {
                code[c] = i + 1;
            }
            sb.append(code[c]);
        }
        return sb.toString();
    }
}
