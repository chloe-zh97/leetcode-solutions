public class ClearDigits_3174 {
    public static void main(String[] args) {
        String s = "cb34";
        System.out.println(clearDigits(s));
    }

    public static String clearDigits(String s) {

        StringBuilder sb = new StringBuilder();
        for(char ch: s.toCharArray()) {
            if(Character.isDigit(ch) && !Character.isDigit(sb.charAt(sb.length()-1))) {
                sb.deleteCharAt(sb.length()-1);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
