import java.util.HashMap;

public class RomantoInteger_13 {
    public static void main(String[] args) {
        String s = "LVIII";
        System.out.println(romanToInt(s));
    }

    public static int romanToInt(String s) {
        int n = s.length();

        HashMap<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

        int ans = 0;

        for(int i=0;i<n;i++) {
            // 是否需要组合
            if(i+1 < n && map.containsKey(s.substring(i,i+2))) {
                ans += map.get(s.substring(i,i+2));
                i += 1;
            } else {
                ans += map.get(s.substring(i,i+1));
            }
        }
        return ans;
    }


    /**
     * Method 2: 优化，观察规律，当前元素比下一个元素小，减去该元素
     * */
    public static int romanToInt_2(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();

        int[] vals = new int[128];
        vals['I'] = 1; vals['V'] = 5;
        vals['X'] = 10; vals['L'] = 50;
        vals['C'] = 100; vals['D'] = 500;
        vals['M'] = 1000;

        int ans = 0;
        for(int i=0;i<n;i++) {
            if(i+1 < n && vals[chs[i]] < vals[chs[i+1]]) ans -= vals[chs[i]];
            else ans += vals[chs[i]];
        }
        return ans;
    }

}
