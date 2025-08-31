import java.util.HashMap;

public class WordPattern_290 {
    public static void main(String[] args) {
        String pattern = "aaaa";
        String s = "dog cat cat dog";
        System.out.println(wordPattern(pattern, s));
    }

    public static boolean wordPattern(String pattern, String s) {
        char[] pp = pattern.toCharArray();
        String[] words = s.trim().split("\\s+");

        if(pp.length != words.length) return false;

        HashMap<Character, String> map = new HashMap<>();
        HashMap<String, Character> wmap = new HashMap<>();
        for(int i=0;i<pp.length;i++) {
            if(map.containsKey(pp[i]) && !map.get(pp[i]).equals(words[i])) return false;
            else if(!map.containsKey(pp[i]) && wmap.containsKey(words[i])) return false;
            map.put(pp[i], words[i]);
            wmap.put(words[i], pp[i]);
        }
        return true;
    }
}
