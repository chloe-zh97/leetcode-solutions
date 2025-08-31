public class LengthofLastWord_58 {
    public static void main(String[] args) {

    }

    public static int lengthOfLastWord(String s) {
        String[] ss = s.split(" ");
        return ss[ss.length-1].length();
    }
}
