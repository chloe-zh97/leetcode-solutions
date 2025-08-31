import java.util.ArrayList;
import java.util.List;

public class IntegertoRoman_12 {
    public static void main(String[] args) {
        int num = 58;
        System.out.println(intToRoman(num));
    }

    static class Node {
        int v;
        String s;
        public Node() {}

        public Node(int _v, String _s) {
            this.v = _v;
            this.s = _s;
        }
    }


    public static String intToRoman(int num) {
        List<Node> syms = new ArrayList<>();
        syms.add(new Node(1, "I"));
        syms.add(new Node(4, "IV"));
        syms.add(new Node(5, "V"));
        syms.add(new Node(9, "IX"));
        syms.add(new Node(10, "X"));
        syms.add(new Node(40, "XL"));
        syms.add(new Node(50, "L"));
        syms.add(new Node(90, "XC"));
        syms.add(new Node(100, "C"));
        syms.add(new Node(400, "CD"));
        syms.add(new Node(500, "D"));
        syms.add(new Node(900, "CM"));
        syms.add(new Node(1000, "M"));

        StringBuilder sb = new StringBuilder();
        while(num > 0) {
            int pos = binarySearch(syms, num);
            String key = syms.get(pos).s;
            int val = syms.get(pos).v;
            int cnt = num / val;
            num = num % val;

            for(int i=0;i<cnt;i++)
                sb.append(key);
        }

        return sb.toString();
    }

    /**
     * 在 syms 数组中找 <= num 的最大值
     * */
    private static int binarySearch(List<Node> syms, int num) {
        int left = 0, right = syms.size() - 1;
        while(left < right) {
            int mid = left + right + 1 >> 1;
            if(syms.get(mid).v > num) right = mid - 1;
            else left = mid;
        }
        return syms.get(left).v <= num ? left : -1;
    }
}
