import java.util.Stack;

public class ConvertBracket {

    public static boolean isCorrect(String str) {
        if(str.charAt(0) == ')') return false;
        Stack<Boolean> stack = new Stack<>();
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') stack.push(true);
            else {
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return true;
    }

    public static String solution(String p) {
        String answer = "";
        if(p.equals("")) return "";
        if(isCorrect(p)) return p;

        Stack<Boolean> stack = new Stack<>();
        char start = p.charAt(0);
        stack.push(true);
        String u = start+"";
        int i;
        for(i = 1; i < p.length() && !stack.isEmpty(); i++) {
            char c = p.charAt(i);
            if(c == start) stack.push(true);
            else stack.pop();
            u += c;
        }
        String v = p.substring(i);
        if(isCorrect(u)) answer = u + solution(v);
        else {
            answer += "(" + solution(v) + ")";
            for(int j = 1; j < u.length()-1; j++) {
                if(u.charAt(j) == '(') answer += ')';
                else answer += '(';
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("(()())()"));
        System.out.println(solution(")("));
        System.out.println(solution("()))((()"));
    }
}
