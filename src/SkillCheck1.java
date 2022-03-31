public class SkillCheck1 {

    public static String solution1(String s, int n) {
        String answer = "";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c != ' ') {
                if(c <= 'Z' && c+n > 'Z') c = (char)(c + n - 26);
                else if(c >= 'a' && c+n > 'z') c = (char)(c + n - 26);
                else c += n;
            }
            answer += c;
        }
        return answer;
    }

    public static String solution(String[] seoul) {
        String answer = "";
        int i;
        for(i = 0; i < seoul.length; i++) {
            if(seoul[i].equals("Kim")) break;
        }
        answer = "김서방은 " + i + "에 있다";
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution1("a B z", 4));
    }
}
